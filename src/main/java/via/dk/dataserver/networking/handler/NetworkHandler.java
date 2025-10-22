package via.dk.dataserver.networking.handler;

import com.google.protobuf.Message;
import org.springframework.stereotype.Service;
import via.dk.dataserver.gRPC.Sep3;

@Service
public interface NetworkHandler {
    Message handle(Sep3.ActionType actionType, Object payload) throws Exception;
}
