package kodlama.io.rentacar.business.concretes;

import kodlama.io.rentacar.business.abstracts.CarService;
import kodlama.io.rentacar.business.abstracts.MaintenanceService;
import kodlama.io.rentacar.business.dto.requests.create.CreateMaintenanceRequest;
import kodlama.io.rentacar.business.dto.requests.update.UpdateCarRequest;
import kodlama.io.rentacar.business.dto.requests.update.UpdateMaintenanceRequest;
import kodlama.io.rentacar.business.dto.responses.create.CreateCarResponse;
import kodlama.io.rentacar.business.dto.responses.create.CreateMaintenanceResponse;
import kodlama.io.rentacar.business.dto.responses.get.GetAllCarsResponse;
import kodlama.io.rentacar.business.dto.responses.get.GetAllMaintenancesResponse;
import kodlama.io.rentacar.business.dto.responses.get.GetCarResponse;
import kodlama.io.rentacar.business.dto.responses.get.GetMaintenanceResponse;
import kodlama.io.rentacar.business.dto.responses.update.UpdateCarResponse;
import kodlama.io.rentacar.business.dto.responses.update.UpdateMaintenanceResponse;
import kodlama.io.rentacar.entities.concretes.Car;
import kodlama.io.rentacar.entities.concretes.Maintenance;
import kodlama.io.rentacar.entities.enums.State;
import kodlama.io.rentacar.repository.abstracts.CarRepository;
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
        Maintenance maintenanceSave = mapper.map(maintenanceRequest, Maintenance.class);
        maintenanceSave.setId(0);
        GetCarResponse car = carService.getById(maintenanceRequest.getCarId());
        Car carSave = mapper.map(car, Car.class);
        checkIfCarCanBeSentToMaintenance(carSave);
        repository.save(maintenanceSave);
        carSave.setState(State.MAINTENANCE);
        UpdateCarRequest carUpdate = mapper.map(carSave, UpdateCarRequest.class);
        carService.update(carSave.getId(),carUpdate);
        CreateMaintenanceResponse response = mapper.map(maintenanceSave,CreateMaintenanceResponse.class);
        return response;
    }



    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }

    @Override
    public UpdateMaintenanceResponse update(int id, UpdateMaintenanceRequest maintenance) {
        Maintenance updateMaintenance = mapper.map(maintenance, Maintenance.class);
        updateMaintenance.setId(id);
        GetCarResponse car = carService.getById(maintenance.getCarId());
        Car carSave = mapper.map(car, Car.class);
        checkIfCarCanBeSentToMaintenance(carSave);
        repository.save(updateMaintenance);
        carSave.setState(State.MAINTENANCE);
        UpdateCarRequest carUpdate = mapper.map(carSave, UpdateCarRequest.class);

        carService.update(carSave.getId(),carUpdate);
        UpdateMaintenanceResponse response = mapper.map(updateMaintenance,UpdateMaintenanceResponse.class);
        return response;
    }

    @Override
    public GetMaintenanceResponse getById(int id) {
        Maintenance maintenance = repository.findById(id).orElseThrow();
        GetMaintenanceResponse response = mapper.map(maintenance,GetMaintenanceResponse.class);
        return response;
    }

    private void checkIfCarCanBeSentToMaintenance(Car car) {
        checkIfCarInMaintenance(car);
        checkIfCarRented(car);
    }

    private void checkIfCarRented(Car car) {
        if(car.getState() == State.RENTED){
            throw new RuntimeException("Car is rented");
        }
    }

    private void checkIfCarInMaintenance(Car car) {
        if(car.getState() == State.MAINTENANCE){
            throw new RuntimeException("Car is already in maintenance!");
        }

    }
}
