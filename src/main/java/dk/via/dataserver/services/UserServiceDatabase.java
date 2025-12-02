package dk.via.dataserver.services;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import dk.via.dataserver.entity.User;
import dk.via.dataserver.gRPC.Sep3;
import dk.via.dataserver.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service("Sep3-Java-DataServer")
public class UserServiceDatabase implements UserService {

    private final UserRepository userRepository;

    public UserServiceDatabase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public Sep3.UserProto create(Sep3.UserProto payload) {
        User user = new User();
        user.setUsername(payload.getUsername());
        user.setPassword(payload.getPassword());
        user.setEmail(payload.getEmail());
        user.setScore(payload.getScore());
       User savedUser = userRepository.save(user);

        //whenever dbs returns null
        double score = savedUser.getScore() != null ? savedUser.getScore() : 0.0;

        return Sep3.UserProto.newBuilder()
                .setId(savedUser.getId())
                .setUsername(savedUser.getUsername())
                .setPassword(savedUser.getPassword())
                .setEmail(savedUser.getEmail())
                .setScore(score)
                .build();
    }

    @Transactional
    public Sep3.UserProto update(Sep3.UserProto payload)  {
        User existingUser = userRepository.findByUsername(
                payload.getUsername()).orElseThrow(() -> new RuntimeException("Username not found"));


        existingUser.setUsername(payload.getUsername());
        existingUser.setPassword(payload.getPassword());
        existingUser.setScore(payload.getScore());

        User updatedUser = userRepository.save(existingUser);

        double score = updatedUser.getScore() != null ? updatedUser.getScore() : 0.0;

        return Sep3.UserProto.newBuilder()
                .setId(updatedUser.getId())
                .setUsername(updatedUser.getUsername())
                .setPassword(updatedUser.getPassword())
                .setEmail(updatedUser.getEmail())
                .setScore(score)
                .build();
    }

    @Override
    public Sep3.UserProto getSingle(String email) throws Exception {
        Optional<User> fetchUser = userRepository.findByEmail(email);

        User user = fetchUser.orElseThrow(
                () -> new Exception("User with given email does not exist "+ email)
        );

        double score = user.getScore() != null ? user.getScore() : 0.0;

        return Sep3.UserProto.newBuilder()
                .setId(user.getId())
                .setUsername(user.getUsername())
                .setPassword(user.getPassword())
                .setEmail(user.getEmail())
                .setScore(score)
                .build();
    }


    @Override
    public void delete(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public Sep3.UserListProto getAll() {
       List<User> users = userRepository.findAll();

       Sep3.UserListProto.Builder builder = Sep3.UserListProto.newBuilder();

        users.forEach(user -> {
            double score = user.getScore() != null ? user.getScore() : 0.0;

            builder.addUsers(
                    Sep3.UserProto.newBuilder()
                            .setId(user.getId())
                            .setUsername(user.getUsername())
                            .setPassword(user.getPassword())
                            .setEmail(user.getEmail())
                            .setScore(score)
                            .build()
            );
        });

        return builder.build();
    }
}
