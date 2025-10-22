package via.dk.dataserver.networking;

import com.google.protobuf.Any;
import com.google.protobuf.Message;
import com.google.protobuf.StringValue;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import via.dk.dataserver.gRPC.Sep3;
import via.dk.dataserver.networking.handler.NetworkHandler;
import via.dk.dataserver.startup.ServiceProvider;
import via.dk.dataserver.gRPC.homogeniousServiceGrpc;

@GRpcService
public class MainHandler extends homogeniousServiceGrpc.homogeniousServiceImplBase {


    private final ServiceProvider serviceProvider;
    public MainHandler(ServiceProvider serviceProvider) {
        this.serviceProvider = serviceProvider;
    }

    @Override
    public void handleRequest(Sep3.Request request, StreamObserver<Sep3.Response> responseObserver) {
        Sep3.Response.Builder responseBuilder = Sep3.Response.newBuilder();
         NetworkHandler handler = switch(request.getHandler()){

             case HANDLER_USER -> serviceProvider.getUserHandler();
             default -> throw new IllegalArgumentException("Unknown handler type");
         };
        Message result = null;
        try {
            result = handler.handle(request.getAction(),request.getPayload());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Sep3.Response response = Sep3.Response.newBuilder().setStatus(Sep3.StatusType.STATUS_OK)
                .setPayload(Any.pack(result)).build();

        sendResponseWithHandleException(responseObserver,response);//Send with error handling
    }

    private void sendResponseWithHandleException(StreamObserver<Sep3.Response> responseObserver, Sep3.Response response) {
        try {
            responseObserver.onNext(response);//send the response to the server
            responseObserver.onCompleted();//and move on on completed
            //if exception send errors
        } catch (ClassCastException e) {
            sendGrpcError(responseObserver, Sep3.StatusType.STATUS_INVALID_PAYLOAD, "Invalid request");

        } catch (Exception e) {
            sendGrpcError(responseObserver, Sep3.StatusType.STATUS_ERROR, e.getMessage());
        }
    }

    private void sendGrpcError(StreamObserver<Sep3.Response> observer, Sep3.StatusType status, String errorMessage) {
        Any payload =Any.pack(StringValue.of(errorMessage));// convert errorMessage String to protobuf Message
        Sep3.Response response = Sep3.Response.newBuilder().
                setStatus(status).
                setPayload(payload)
                .build();
        observer.onNext(response);
        observer.onCompleted();
    }
}
