package dk.via.dataserver.startup;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import dk.via.dataserver.networking.handler.NetworkHandler;
import dk.via.dataserver.networking.handler.UserHandler;
import dk.via.dataserver.repository.UserRepository;
import dk.via.dataserver.services.UserServiceDatabase;

@Service
public class ServiceProvider {
    private ApplicationContext context;
    public UserServiceDatabase getUserService(){
        return new UserServiceDatabase(context.getBean(UserRepository.class));
    }
    public NetworkHandler getUserHandler() {
        return new UserHandler(getUserService());
    }
}
