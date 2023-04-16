package kodlama.io.rentacar.business.dto.responses.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateModelResponse {
    private int id;
    private String name;
    private int brandId;
}
