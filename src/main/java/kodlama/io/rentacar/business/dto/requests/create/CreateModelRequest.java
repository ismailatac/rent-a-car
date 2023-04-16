package kodlama.io.rentacar.business.dto.requests.create;

import kodlama.io.rentacar.entities.concretes.Brand;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateModelRequest {

    private String name;
    private int brandId;

}
