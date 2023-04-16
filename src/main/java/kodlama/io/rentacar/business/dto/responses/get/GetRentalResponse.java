package kodlama.io.rentacar.business.dto.responses.get;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetRentalResponse {
    private int id;
    private double dailyPrice;
    private int rentedForDays;
    private double totalPrice;
    private int carId;

}

