package kodlama.io.rentacar.business.dto.responses.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateRentalResponse {
    private int id;
    private double dailyPrice;
    private int rentedForDays;
    private double totalPrice;
    private int carId;
}
