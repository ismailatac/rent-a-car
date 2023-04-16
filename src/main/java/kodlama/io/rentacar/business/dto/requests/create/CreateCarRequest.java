package kodlama.io.rentacar.business.dto.requests.create;

import kodlama.io.rentacar.entities.enums.State;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateCarRequest {
    private int modelYear;
    private String plate;
    private double dailyPrice;
    private int modelId;

}
