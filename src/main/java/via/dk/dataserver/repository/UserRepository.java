package via.dk.dataserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import via.dk.dataserver.entity.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>
{

    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);


}
