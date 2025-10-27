package dk.via.dataserver;

import dk.via.dataserver.networking.MainHandler;
import dk.via.dataserver.networking.SepServer;
import dk.via.dataserver.startup.GlobalContext;
import io.grpc.Server;
import jakarta.persistence.Entity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import dk.via.dataserver.entity.User;
import dk.via.dataserver.repository.UserRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("dk.via.dataserver.repository")
@EntityScan("dk.via.dataserver.entity")
@SpringBootApplication(scanBasePackages = "dk.via.dataserver")
public class Sep3JavaDataServerApplication
{

  public static void main(String[] args)
  {
    GlobalContext.setContext(SpringApplication.run(Sep3JavaDataServerApplication.class,args));
    //ApplicationContext context = SpringApplication.run(Sep3JavaDataServerApplication.class, args);
    UserRepository userRepository = GlobalContext.getBean(UserRepository.class);
    SepServer server = GlobalContext.getContext().getBean(SepServer.class);
    server.start();
    System.out.println("Server started");
  }
}
