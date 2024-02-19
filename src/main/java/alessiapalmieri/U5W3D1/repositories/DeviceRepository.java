package alessiapalmieri.U5W3D1.repositories;

import alessiapalmieri.U5W3D1.Enum.DeviceStatus;
import alessiapalmieri.U5W3D1.entities.Device;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {
    Page<Device> findByDeviceStatus(DeviceStatus deviceStatus, Pageable pageable);
    Page<Device> findByDeviceType(String deviceType, Pageable pageable);
    Page<Device> findByDeviceTypeAndDeviceStatus(String deviceType, DeviceStatus deviceStatus, Pageable pageable);
    Optional<Device> findByEmployee_id(long id);

}
