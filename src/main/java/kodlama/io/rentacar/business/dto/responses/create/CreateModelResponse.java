package kodlama.io.rentacar.business.dto.responses.create;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateModelResponse {
    private int id;
    private String name;
    private int brandId;
}
