package kodlama.io.rentacar.business.dto.requests.create;

import kodlama.io.rentacar.entities.concretes.Brand;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateModelRequest {

    private String name;
    private int brandId;

}
