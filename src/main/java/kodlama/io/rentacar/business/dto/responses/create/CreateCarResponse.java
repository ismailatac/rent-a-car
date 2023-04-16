package kodlama.io.rentacar.business.dto.responses.create;

import kodlama.io.rentacar.entities.enums.State;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCarResponse {
    private int id;
    private int modelYear;
    private String plate;
    private double dailyPrice;
    private int modelId;
    private State state;
}
