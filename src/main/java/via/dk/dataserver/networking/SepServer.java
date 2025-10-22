package via.dk.dataserver.networking;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class SepServer {
    private static final int PORT = 7991;
    private final MainHandler handler;
    private Server grpcServer;

    public SepServer(MainHandler handler) {
        this.handler = handler;
    }

    public void start()  {
        Thread grpcThread = new Thread(() -> {
            try{
                grpcServer = ServerBuilder.forPort(PORT).addService(handler).build().start();
                System.out.println("Server started on port " + PORT);
                Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                    System.out.println("Shutting down gRPC server");
                    if (grpcServer != null) {
                        grpcServer.shutdown();
                    }

                }));

            } catch (IOException e) {
                e.printStackTrace();
            }

        }, "gRPC-Server-Thread");
        grpcThread.setDaemon(true);
        grpcThread.start();
    }
}
