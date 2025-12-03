package dk.via.dataserver.networking.handler;

import com.google.protobuf.Message;
import dk.via.dataserver.gRPC.Sep3;
import dk.via.dataserver.services.ScoreService;

public class ScoreHandler implements NetworkHandler {

    private final ScoreService scoreService;

    public ScoreHandler(ScoreService scoreService) {
        this.scoreService = scoreService;
    }

    @Override
    public Message handle(Sep3.ActionType actionType, Object payload) throws Exception {
        Message proto = null;
        Sep3.ScoreProto request = (Sep3.ScoreProto) payload;

        switch (actionType) {

            case ACTION_GET -> {
                proto = handleGet(request);
            }
            case ACTION_CREATE -> {
                proto = scoreService.create(request);
            }
            case ACTION_UPDATE -> {
                proto = scoreService.update(request);
            }
            case ACTION_DELETE -> {
                handleDelete(request);
            }
            case ACTION_LIST -> {
                proto = (Message) scoreService.getAll();
            }
            default -> throw new IllegalArgumentException("Invalid action type: " + actionType);
        }

        return proto;
    }

    private void handleDelete(Sep3.ScoreProto request) {
        if (request.getId() != 0) {
            scoreService.delete(request.getId());
        } else {
            throw new IllegalArgumentException("Must provide ID for ACTION_DELETE");
        }
    }

    private Message handleGet(Sep3.ScoreProto request) throws Exception {
        if (request.getId() != 0) {
            return scoreService.getSingle(request.getId());
        } else {
            throw new IllegalArgumentException("Must provide ID for ACTION_GET");
        }
    }
}
