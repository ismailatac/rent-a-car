package kodlama.io.rentacar.business.dto.responses.update;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateModelResponse {
    private int id;
    private String name;
    private int brandId;
}
