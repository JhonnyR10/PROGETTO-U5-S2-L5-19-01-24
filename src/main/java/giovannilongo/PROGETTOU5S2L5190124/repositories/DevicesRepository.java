package giovannilongo.PROGETTOU5S2L5190124.repositories;

import giovannilongo.PROGETTOU5S2L5190124.entities.Device;
import giovannilongo.PROGETTOU5S2L5190124.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DevicesRepository extends JpaRepository<Device, Long> {
    List<Device> findByUser(User user);
}
