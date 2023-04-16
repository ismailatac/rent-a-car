package kodlama.io.rentacar.business.dto.requests.create;

import jakarta.persistence.ManyToOne;
import kodlama.io.rentacar.entities.concretes.Car;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateMaintenanceRequest {
    private int carId;
    private String information;
}
