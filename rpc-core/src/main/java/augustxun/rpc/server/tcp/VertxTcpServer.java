package augustxun.rpc.server.tcp;

import augustxun.rpc.server.HttpServer;
import augustxun.rpc.server.HttpServerHandler;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.net.NetServer;
import io.vertx.core.parsetools.RecordParser;

public class VertxTcpServer implements HttpServer {
    /**
     * 启动服务器
     *
     * @param port
     */
    @Override
    public void doStart(int port) {
        // 创建Vert.x实例
        Vertx vertx = Vertx.vertx();

        // 创建TCP服务器
        NetServer server = vertx.createNetServer();

        // 处理请求
//        server.requestHandler(new HttpServerHandler());

        server.connectHandler(socket -> {
            // 示例消息
//            String testMessage = "Hello, server!Hello, server!Hello, server!Hello, server!";
//            int messageLength = testMessage.getBytes().length;



            // 构造parser
            RecordParser parser = RecordParser.newFixed(8);
            parser.setOutput(new Handler<Buffer>() {
                // 初始化
                int size = -1;
                // 一次完整读取消息头+消息体
                Buffer resultBuffer = Buffer.buffer();
                @Override
                public void handle(Buffer buffer) {
//                    String str = new String(buffer.getBytes());
//                    System.out.println(str);
//                    System.out.println("good");
                    if (-1 == size) {
                        // 读取消息体长度
                        size = buffer.getInt(4);
                        parser.fixedSizeMode(size);
                        // 写入头信息到结果中
                        resultBuffer.appendBuffer(buffer);
                    } else {
                        // 写入体信息到结果
                        resultBuffer.appendBuffer(buffer);
                        System.out.println(resultBuffer.toString());
                        // 重置一轮
                        parser.fixedSizeMode(8);
                        size = -1;
                        resultBuffer = Buffer.buffer();
                    }
                }
            });
            socket.handler(parser);
        });


        // 启动TCP服务器并监听指定端口
        server.listen(port, result -> {
            if (result.succeeded()) {
                System.out.println("Server is now listening on port " + port);
            } else {
                System.err.println("Failed to start server: " + result.cause());
            }
        });
    }

    public static void main(String[] args) {
        new VertxTcpServer().doStart(8888);
    }
}
