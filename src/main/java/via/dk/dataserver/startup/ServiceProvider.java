package via.dk.dataserver.startup;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import via.dk.dataserver.networking.handler.NetworkHandler;
import via.dk.dataserver.networking.handler.UserHandler;
import via.dk.dataserver.repository.UserRepository;
import via.dk.dataserver.services.UserServiceDatabase;

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
