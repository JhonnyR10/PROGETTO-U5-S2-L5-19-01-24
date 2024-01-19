package giovannilongo.PROGETTOU5S2L5190124.repositories;

import giovannilongo.PROGETTOU5S2L5190124.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
