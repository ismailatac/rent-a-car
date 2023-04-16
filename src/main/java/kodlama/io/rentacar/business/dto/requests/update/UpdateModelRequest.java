package kodlama.io.rentacar.business.dto.requests.update;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateModelRequest {
    private String name;
    private int brandId;
}
