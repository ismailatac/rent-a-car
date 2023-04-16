package kodlama.io.rentacar.business.dto.responses.get;

import kodlama.io.rentacar.entities.concretes.Car;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllModelsResponse {
    private int id;
    private String name;
    private int brandId;

}
