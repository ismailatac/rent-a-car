package kodlama.io.rentacar.business.dto.requests.create;

import jakarta.persistence.ManyToOne;
import kodlama.io.rentacar.entities.concretes.Car;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateMaintenanceRequest {

    private int carId;
    private Date sentDate;
    private Date backDate;
    private String description;
}
