package kodlama.io.rentacar.business.dto.requests.create;

import jakarta.validation.constraints.Min;
import kodlama.io.rentacar.business.dto.requests.PaymentRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatePaymentRequest extends PaymentRequest {

    @Min(value = 1)
    private double balance;
}
