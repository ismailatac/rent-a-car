package kodlama.io.rentacar.business.dto.responses.update;

import kodlama.io.rentacar.entities.enums.State;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateCarResponse {
    private int id;
    private int modelYear;
    private String plate;
    private double dailyPrice;
    private State state;
    private int modelId;
}
