package kodlama.io.rentacar.common.dto;

import kodlama.io.rentacar.business.dto.requests.PaymentRequest;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateRentalPaymentRequest extends PaymentRequest {
    private double price;
}
