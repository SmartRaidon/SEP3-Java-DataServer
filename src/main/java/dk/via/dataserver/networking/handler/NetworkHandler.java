package dk.via.dataserver.networking.handler;

import com.google.protobuf.Message;
import org.springframework.stereotype.Service;
import dk.via.dataserver.gRPC.Sep3;

@Service
public interface NetworkHandler {
    Message handle(Sep3.ActionType actionType, Object payload) throws Exception;
}
