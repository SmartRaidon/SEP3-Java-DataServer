package dk.via.dataserver.services;

import org.springframework.stereotype.Service;
import dk.via.dataserver.gRPC.Sep3;

@Service
public interface UserService {
    Sep3.UserProto create(Sep3.UserProto payload);

    Sep3.UserProto getSingle(String email) throws Exception;
    Sep3.UserProto getSingleById(int id) throws Exception;
    Sep3.UserProto update(Sep3.UserProto payload);

    void delete(int id);

    Sep3.UserListProto getAll();

}
