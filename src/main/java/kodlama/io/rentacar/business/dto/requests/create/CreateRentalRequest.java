package kodlama.io.rentacar.business.dto.requests.create;

import kodlama.io.rentacar.business.dto.requests.PaymentRequest;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateRentalRequest {
    private double dailyPrice;
    private int rentedForDays;
    private int carId;
    private PaymentRequest paymentRequest;
}
