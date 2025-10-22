package via.dk.dataserver.services;

import org.springframework.stereotype.Service;
import via.dk.dataserver.gRPC.Sep3;

@Service
public interface UserService {
    Sep3.UserProto create(Sep3.UserProto payload);

    Sep3.UserProto update(Sep3.UserProto payload);

    Sep3.UserProto getSingle(String email) throws Exception;




    void delete(int id);

    Iterable<Sep3.UserProto> getAll();

}
