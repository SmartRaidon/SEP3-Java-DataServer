package dk.via.dataserver.networking.handler;

import com.google.protobuf.Message;
import dk.via.dataserver.gRPC.Sep3;
import dk.via.dataserver.services.GameResultService;

public class GameResultHandler implements NetworkHandler {

    private GameResultService gameResultService;

    public GameResultHandler(GameResultService gameResultService) {
        this.gameResultService = gameResultService;
    }

    @Override
    public Message handle(Sep3.ActionType actionType, Object payload) throws Exception {
        Message proto = null;
        Sep3.GameResultProto request = (Sep3.GameResultProto) payload;

        switch (actionType) {

            case ACTION_GET -> {
                proto = gameResultService.getByGameId(request.getGameId());
            }

            case ACTION_CREATE -> {
                proto = gameResultService.create(request);
            }

            case ACTION_LIST -> {
                proto = gameResultService.getAll();  //returns GameResultListProto
            }


            default -> throw new IllegalArgumentException("Invalid action type: " + actionType);
        }

        return proto;
    }
}
