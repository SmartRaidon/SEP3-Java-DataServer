package dk.via.dataserver.networking.handler;

import com.google.protobuf.Any;
import com.google.protobuf.Message;
import dk.via.dataserver.gRPC.Sep3;
import dk.via.dataserver.services.UserService;

public class UserHandler implements NetworkHandler {
    private UserService userService;

    public UserHandler(UserService userService) {
        this.userService = userService;
    }
    @Override
    public Message handle(Sep3.ActionType actionType, Object payload) throws Exception {
        Message proto =null;
        Sep3.UserProto request = (Sep3.UserProto) payload;
        switch (actionType) {
            case ACTION_GET -> {
                proto = handleGet(request);
            }
            case ACTION_CREATE -> {
                proto = userService.create(request);
            }
            case ACTION_UPDATE -> {
                proto = userService.update(request);
            }
            case ACTION_DELETE -> {
                handleDelete(request);
            }
            case Sep3.ActionType.ACTION_LIST -> {
                proto = Any.pack((Message)userService.getAll());
            }
            default -> {
                throw new IllegalArgumentException("Invalid action type: " + actionType);
        }
    }
        return Any.pack(proto) ;
}

    private void handleDelete(Sep3.UserProto request) {
        if (request.getId() != 0) {
            userService.delete(request.getId());
        }else{
            throw new IllegalArgumentException("Must provide either an ID number for ACTION_DELETE.");

    }
    }

    private Message handleGet(Sep3.UserProto request) throws Exception {
        if (!request.getEmail().isEmpty()) {
            return userService.getSingle(request.getEmail());
        }
        else{
            throw new IllegalArgumentException("Must provide email for ACTION_GET.");
        }
    }



}
