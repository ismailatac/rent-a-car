package kodlama.io.rentacar.business.concretes;

import kodlama.io.rentacar.business.abstracts.CarService;
import kodlama.io.rentacar.business.abstracts.PaymentService;
import kodlama.io.rentacar.business.abstracts.RentalService;
import kodlama.io.rentacar.business.dto.requests.create.CreateMaintenanceRequest;
import kodlama.io.rentacar.business.dto.requests.create.CreateRentalRequest;
import kodlama.io.rentacar.business.dto.requests.update.UpdateRentalRequest;
import kodlama.io.rentacar.business.dto.responses.create.CreateMaintenanceResponse;
import kodlama.io.rentacar.business.dto.responses.create.CreateRentalResponse;
import kodlama.io.rentacar.business.dto.responses.get.GetAllModelsResponse;
import kodlama.io.rentacar.business.dto.responses.get.GetAllRentalsResponse;
import kodlama.io.rentacar.business.dto.responses.get.GetRentalResponse;
import kodlama.io.rentacar.business.dto.responses.update.UpdateRentalResponse;
import kodlama.io.rentacar.common.dto.CreateRentalPaymentRequest;
import kodlama.io.rentacar.entities.concretes.Maintenance;
import kodlama.io.rentacar.entities.concretes.Model;
import kodlama.io.rentacar.entities.concretes.Rental;
import kodlama.io.rentacar.entities.enums.State;
import kodlama.io.rentacar.repository.abstracts.RentalRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
@AllArgsConstructor
public class RentalManager implements RentalService {

    private final RentalRepository repository;
    private final ModelMapper mapper;
    private final CarService carService;
    private final PaymentService paymentService;

    @Override
    public List<GetAllRentalsResponse> getAll() {
        List<Rental> rentals = repository.findAll();
        List<GetAllRentalsResponse> response = rentals.stream()
                .map(rental -> mapper.map(rental,GetAllRentalsResponse.class)).toList();
        return response;
    }

    @Override
    public CreateRentalResponse add(CreateRentalRequest request) {
        checkCarAvailabilityForRent(request);
        Rental rental = mapper.map(request, Rental.class);
        rental.setId(0);
        rental.setStartDate(LocalDateTime.now());

        CreateRentalPaymentRequest paymentRequest = new CreateRentalPaymentRequest();
        mapper.map(request.getPaymentRequest(), paymentRequest);
        paymentRequest.setPrice(getTotalPrice(rental));
        paymentService.processRentalPayment(paymentRequest);

        rental.setTotalPrice(getTotalPrice(rental));
        repository.save(rental);
        carService.changeState(rental.getCar().getId(), State.RENTED);
        CreateRentalResponse response = mapper.map(rental, CreateRentalResponse.class);
        return response;
    }



    @Override
    public void delete(int id) {
        checkIfRentalExists(id);
        int carId = repository.findById(id).get().getCar().getId();
        carService.changeState(carId,State.AVAILABLE);
        repository.deleteById(id);
    }



    @Override
    public UpdateRentalResponse update(int id, UpdateRentalRequest request) {
        checkIfRentalExists(id);
        Rental rental = mapper.map(request, Rental.class);
        rental.setId(id);
        rental.setTotalPrice(getTotalPrice(rental));
        repository.save(rental);
        UpdateRentalResponse response = mapper.map(rental, UpdateRentalResponse.class);
        return response;
    }

    @Override
    public GetRentalResponse getById(int id) {
        checkIfRentalExists(id);
        Rental rental = repository.findById(id).orElseThrow();
        GetRentalResponse response = mapper.map(rental,GetRentalResponse.class);
        return response;
    }


    private void checkCarAvailabilityForRent(CreateRentalRequest request) {
        if (!carService.getById(request.getCarId()).getState().equals(State.AVAILABLE)) {
            throw new RuntimeException("Araç müsait değil!");
        }
    }
    private void checkIfRentalExists(int id) {
        if(!repository.existsById(id)){
            throw new RuntimeException("Kiralama bilgisine ulaşılamadı!");
        }
    }
    private double getTotalPrice(Rental rental) {
        return rental.getDailyPrice() * rental.getRentedForDays();
    }

}
