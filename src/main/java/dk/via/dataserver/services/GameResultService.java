package dk.via.dataserver.services;

import dk.via.dataserver.gRPC.Sep3;
import org.springframework.stereotype.Service;

@Service
public interface GameResultService {
    Sep3.GameResultProto create(Sep3.GameResultProto payload);

    Sep3.GameResultProto getByGameId(int gameId) throws Exception;

    Sep3.GameResultProto update (Sep3.GameResultProto payload);

    void delete(int id);

    Sep3.GameResultListProto getAll();
}
