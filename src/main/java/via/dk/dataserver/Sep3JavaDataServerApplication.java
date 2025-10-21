package via.dk.dataserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import via.dk.dataserver.entity.User;
import via.dk.dataserver.repositories.UserRepository;

@SpringBootApplication
public class Sep3JavaDataServerApplication
{

  public static void main(String[] args)
  {
    ApplicationContext context = SpringApplication.run(Sep3JavaDataServerApplication.class, args);
    UserRepository userRepository = context.getBean(UserRepository.class);
    User user = new User();
    user.setUsername("Mario");
    user.setPassword("asd123");
    user.setEmail("mario@motherfucker.com");
    userRepository.save(user);
  }
}
