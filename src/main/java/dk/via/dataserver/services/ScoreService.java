package dk.via.dataserver.services;

import dk.via.dataserver.gRPC.Sep3;

public interface ScoreService {

    Sep3.ScoreProto create(Sep3.ScoreProto payload);

    Sep3.ScoreProto update(Sep3.ScoreProto payload);

    Sep3.ScoreProto getSingle(int id) throws Exception;

    void delete(int id);

    Iterable<Sep3.ScoreProto> getAll();
}
