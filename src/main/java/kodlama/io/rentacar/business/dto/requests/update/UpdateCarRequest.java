package kodlama.io.rentacar.business.dto.requests.update;

import kodlama.io.rentacar.entities.enums.State;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateCarRequest {

    private int modelYear;
    private String plate;
    private double dailyPrice;
    private State state;
    private int modelId;
}
