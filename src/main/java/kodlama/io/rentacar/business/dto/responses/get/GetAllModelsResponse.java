package kodlama.io.rentacar.business.dto.responses.get;

import kodlama.io.rentacar.entities.concretes.Car;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetAllModelsResponse {
    private int id;
    private String name;
    private int brandId;

}
