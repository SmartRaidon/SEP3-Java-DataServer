package dk.via.dataserver.services;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import dk.via.dataserver.entity.User;
import dk.via.dataserver.gRPC.Sep3;
import dk.via.dataserver.repository.UserRepository;

import java.util.List;

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
        user.setPoints(payload.getPoints());

        User savedUser = userRepository.save(user);

        //in the case dbs still return null
        int points = savedUser.getPoints() != null ? savedUser.getPoints() : 0;

        return Sep3.UserProto.newBuilder()
                .setId(savedUser.getId())
                .setUsername(savedUser.getUsername())
                .setPassword(savedUser.getPassword())
                .setEmail(savedUser.getEmail())
                .setPoints(points)
                .build();
    }

    @Transactional
    public Sep3.UserProto update(Sep3.UserProto payload)  {
        User existingUser = userRepository.findByUsername(
                payload.getUsername()).orElseThrow(() -> new RuntimeException("Username not found"));

        existingUser.setUsername(payload.getUsername());
        existingUser.setPassword(payload.getPassword());
        existingUser.setPoints(payload.getPoints());

        User updatedUser = userRepository.save(existingUser);

        int points = updatedUser.getPoints() != null ? updatedUser.getPoints() : 0;

        return Sep3.UserProto.newBuilder()
                .setId(updatedUser.getId())
                .setUsername(updatedUser.getUsername())
                .setPassword(updatedUser.getPassword())
                .setEmail(updatedUser.getEmail())
                .setPoints(points)
                .build();
    }
    @Override
    public Sep3.UserProto getSingleById(int id) throws Exception {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new Exception("User with given id does not exist " + id));

        int points = user.getPoints() != null ? user.getPoints() : 0;

        return Sep3.UserProto.newBuilder()
                .setId(user.getId())
                .setUsername(user.getUsername())
                .setPassword(user.getPassword())
                .setEmail(user.getEmail())
                .setPoints(points)
                .build();
    }
    @Override
    public Sep3.UserProto getSingle(String email) throws Exception {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new Exception("User with given email does not exist " + email));

        int points = user.getPoints() != null ? user.getPoints() : 0;

        return Sep3.UserProto.newBuilder()
                .setId(user.getId())
                .setUsername(user.getUsername())
                .setPassword(user.getPassword())
                .setEmail(user.getEmail())
                .setPoints(points)
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
            int points = user.getPoints() != null ? user.getPoints() : 0;

            builder.addUsers(
                    Sep3.UserProto.newBuilder()
                            .setId(user.getId())
                            .setUsername(user.getUsername())
                            .setPassword(user.getPassword())
                            .setEmail(user.getEmail())
                            .setPoints(points)
                            .build()
            );
        });

        return builder.build();
    }

    @Override
    public Sep3.UserListProto getTop10Players() {
        List<User> users = userRepository.findTop10ByOrderByPointsDesc();

        Sep3.UserListProto.Builder builder = Sep3.UserListProto.newBuilder();

        users.forEach(user -> {
            int points = user.getPoints() != null ? user.getPoints() : 0;

            builder.addUsers(
                    Sep3.UserProto.newBuilder()
                            .setId(user.getId())
                            .setUsername(user.getUsername())
                            .setPassword(user.getPassword())
                            .setEmail(user.getEmail())
                            .setPoints(points)
                            .build()
            );
        });

        return builder.build();
    }


}

