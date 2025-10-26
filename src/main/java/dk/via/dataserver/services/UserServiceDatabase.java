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
       User savedUser = userRepository.save(user);

        return Sep3.UserProto.newBuilder()
                .setId(savedUser.getId())
                .setUsername(savedUser.getUsername())
                .setPassword(savedUser.getPassword())
                .setEmail(savedUser.getEmail()).build();
    }

    @Transactional
    public Sep3.UserProto update(Sep3.UserProto payload)  {
        User existingUser = userRepository.findByUsername(
                payload.getUsername()).orElseThrow(() -> new RuntimeException("Company not found"));


        existingUser.setUsername(payload.getUsername());
        existingUser.setPassword(payload.getPassword());
        User updatedUser = userRepository.save(existingUser);
        return Sep3.UserProto.newBuilder()
                .setId(updatedUser.getId())
                .setUsername(updatedUser.getUsername())
                .setPassword(updatedUser.getPassword()).setEmail(updatedUser.getEmail()).build();
    }

    @Override
    public Sep3.UserProto getSingle(String email) throws Exception {
        Optional<User> fetchUser = userRepository.findByEmail(email);

        User user = fetchUser.orElseThrow(
                () -> new Exception("User with given email doesnt exist"+ email)
        );
        return Sep3.UserProto.newBuilder()
                .setUsername(user.getUsername())
                .setPassword(user.getPassword())
                .setEmail(user.getEmail())
                .setId(user.getId()).build();


    }


    @Override
    public void delete(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public Iterable<Sep3.UserProto> getAll() {
       List<User> users = userRepository.findAll();
       Iterable<Sep3.UserProto> userProto;
        userProto = users.stream().map(
                user -> Sep3.UserProto.newBuilder().setId(user.getId()).setUsername(user.getUsername()).setPassword(user.getPassword()).setEmail(user.getEmail()).build()).toList();
        return userProto;
    }
}
