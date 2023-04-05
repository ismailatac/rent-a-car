package kodlama.io.rentacar.business.concretes;

import kodlama.io.rentacar.business.abstracts.CarService;
import kodlama.io.rentacar.business.abstracts.MaintenanceService;
import kodlama.io.rentacar.business.dto.requests.create.CreateMaintenanceRequest;
import kodlama.io.rentacar.business.dto.requests.update.UpdateCarRequest;
import kodlama.io.rentacar.business.dto.requests.update.UpdateMaintenanceRequest;
import kodlama.io.rentacar.business.dto.responses.create.CreateMaintenanceResponse;
import kodlama.io.rentacar.business.dto.responses.get.GetAllMaintenancesResponse;
import kodlama.io.rentacar.business.dto.responses.get.GetCarResponse;
import kodlama.io.rentacar.business.dto.responses.get.GetMaintenanceResponse;
import kodlama.io.rentacar.business.dto.responses.update.UpdateMaintenanceResponse;
import kodlama.io.rentacar.entities.concretes.Car;
import kodlama.io.rentacar.entities.concretes.Maintenance;
import kodlama.io.rentacar.entities.enums.State;
import kodlama.io.rentacar.repository.abstracts.MaintenanceRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class MaintenanceManager implements MaintenanceService {
    private final MaintenanceRepository repository;
    private final CarService carService;
    private final ModelMapper mapper;
    @Override
    public List<GetAllMaintenancesResponse> getAll() {
        List<Maintenance> maintenances = repository.findAll();
        List<GetAllMaintenancesResponse> response = maintenances.stream()
                .map(maintenance -> mapper.map(maintenance,GetAllMaintenancesResponse.class)).toList();
        return response;
    }

    @Override
    public CreateMaintenanceResponse add(CreateMaintenanceRequest maintenanceRequest) {
        Car car = getCar(maintenanceRequest.getCarId());
        checkIfCarCanBeSentToMaintenance(car);
        updateCarState(car,State.MAINTENANCE);
        Maintenance maintenance = mapper.map(maintenanceRequest, Maintenance.class);
        maintenance.setId(0);
        Maintenance savedMaintenance = repository.save(maintenance);
        return mapper.map(savedMaintenance,CreateMaintenanceResponse.class);
    }




    @Override
    public void delete(int id) {
        GetMaintenanceResponse maintenance = this.getById(id);
        Car car = getCar(maintenance.getCarId());
        updateCarState(car,State.AVAILABLE);
        repository.deleteById(id);
    }

    @Override
    public UpdateMaintenanceResponse update(int id, UpdateMaintenanceRequest maintenanceRequest) {
        Car car = getCar(maintenanceRequest.getCarId());
        checkIfCarCanBeSentToMaintenance(car);
        updateCarState(car,State.MAINTENANCE);
        Maintenance maintenance = mapper.map(maintenanceRequest, Maintenance.class);
        maintenance.setId(id);
        Maintenance updatedMaintenance = repository.save(maintenance);
        return mapper.map(updatedMaintenance,UpdateMaintenanceResponse.class);
    }

    @Override
    public GetMaintenanceResponse getById(int id) {
        Maintenance maintenance = repository.findById(id).orElseThrow();
        return mapper.map(maintenance,GetMaintenanceResponse.class);
    }

    private void checkIfCarCanBeSentToMaintenance(Car car) {
        checkIfCarInMaintenance(car);
        checkIfCarRented(car);
    }

    private void checkIfCarRented(Car car) {
        if(car.getState() == State.RENTED){
            throw new RuntimeException("Car is rented!");
        }
    }

    private void checkIfCarInMaintenance(Car car) {
        if(car.getState() == State.MAINTENANCE){
            throw new RuntimeException("Car is already in maintenance!");
        }
    }

    private void updateCarState(Car car, State state) {
        car.setState(state);
        UpdateCarRequest carUpdate = mapper.map(car, UpdateCarRequest.class);
        carService.update(car.getId(),carUpdate);
    }

    private Car getCar(int carId) {
        GetCarResponse carResponse = carService.getById(carId);
        Car car = mapper.map(carResponse, Car.class);
        return car;
    }

}
