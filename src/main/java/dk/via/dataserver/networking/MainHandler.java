package dk.via.dataserver.networking;

import com.google.protobuf.Any;
import com.google.protobuf.Message;
import com.google.protobuf.StringValue;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import dk.via.dataserver.gRPC.Sep3;
import dk.via.dataserver.networking.handler.NetworkHandler;
import dk.via.dataserver.startup.ServiceProvider;
import dk.via.dataserver.gRPC.homogeniousServiceGrpc;

@GRpcService
public class MainHandler extends homogeniousServiceGrpc.homogeniousServiceImplBase {


    private final ServiceProvider serviceProvider;
    public MainHandler(ServiceProvider serviceProvider) {
        this.serviceProvider = serviceProvider;
    }

    @Override
    public void handleRequest(Sep3.Request request, StreamObserver<Sep3.Response> responseObserver) {
        NetworkHandler handler = switch (request.getHandler()) {
            case HANDLER_USER -> serviceProvider.getUserHandler();
            default -> throw new IllegalArgumentException("Unknown handler type " + request.getHandler());
        };

        Message result;
        try {
            //unpack payload
            Message payload = switch (request.getHandler()) {
                //unpack to concrete type that the handler expects
                case HANDLER_USER -> request.getPayload().unpack(Sep3.UserProto.class);
                default -> request.getPayload();
            };

            result = handler.handle(request.getAction(), payload);

            //against null handlers
            if (result == null) {
                sendGrpcError(responseObserver,
                        Sep3.StatusType.STATUS_ERROR,
                        "Handler returned no payload for action " + request.getAction());
                return;
            }

        } catch (Exception e) {
            sendGrpcError(responseObserver, Sep3.StatusType.STATUS_ERROR, e.getMessage());
            return;
        }

        Sep3.Response response = Sep3.Response.newBuilder()
                .setStatus(Sep3.StatusType.STATUS_OK)
                .setPayload(Any.pack(result))
                .build();

        sendResponseWithHandleException(responseObserver, response);
    }


    private void sendResponseWithHandleException(StreamObserver<Sep3.Response> responseObserver, Sep3.Response response) {
        try {
            responseObserver.onNext(response);//send the response to the server
            responseObserver.onCompleted();//and move  on completed
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
