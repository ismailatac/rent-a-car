package kodlama.io.rentacar.business.dto.responses.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateRentalResponse {
    private int id;
    private double dailyPrice;
    private int rentedForDays;
    private double totalPrice;
    private int carId;

}