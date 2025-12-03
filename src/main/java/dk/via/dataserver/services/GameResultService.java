package dk.via.dataserver.services;

import dk.via.dataserver.gRPC.Sep3;
import org.springframework.stereotype.Service;

@Service
public interface GameResultService {
    Sep3.GameResultProto create(Sep3.GameResultProto payload);

    Sep3.GameResultProto getByGameId(int gameId) throws Exception;

    Iterable<Sep3.GameResultProto> getAll();
}
