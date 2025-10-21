package via.dk.dataserver.repositories;

import org.springframework.stereotype.Repository;
import via.dk.dataserver.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
