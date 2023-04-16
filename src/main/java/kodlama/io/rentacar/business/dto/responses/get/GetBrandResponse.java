package kodlama.io.rentacar.business.dto.responses.get;

import kodlama.io.rentacar.entities.concretes.Model;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetBrandResponse {
    private int id;
    private String name;
}
