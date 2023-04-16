package kodlama.io.rentacar.business.dto.responses.get;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetModelResponse {
    private int id;
    private String name;
    private int brandId;
}
