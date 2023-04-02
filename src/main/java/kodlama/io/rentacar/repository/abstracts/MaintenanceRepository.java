package kodlama.io.rentacar.repository.abstracts;

import kodlama.io.rentacar.entities.concretes.Maintenance;
import kodlama.io.rentacar.entities.concretes.Model;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaintenanceRepository extends JpaRepository<Maintenance,Integer> {
}
