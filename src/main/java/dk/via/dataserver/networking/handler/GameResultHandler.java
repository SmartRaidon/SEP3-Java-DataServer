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
            case ACTION_UPDATE -> {
                proto = gameResultService.update(request);
            }
            case ACTION_DELETE -> {
                handleDelete(request);

                proto = Sep3.GameResultProto.newBuilder()
                        .setId(request.getId())
                        .build();
            }
            case Sep3.ActionType.ACTION_LIST -> {
                proto = gameResultService.getAll();
            }
            default -> {
                throw new IllegalArgumentException("Invalid action type: " + actionType);
            }
        }
        // Return proto directly, MainHandler will wrap it in Any
        return proto;
    }

    private void handleDelete(Sep3.GameResultProto request) {
        if (request.getId() != 0) {
            gameResultService.delete(request.getId());
        } else
        {
            throw new IllegalArgumentException("Must provide ID number for ACTION_DELETE.");
        }

    }

}