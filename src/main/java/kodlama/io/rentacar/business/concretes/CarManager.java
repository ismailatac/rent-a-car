package kodlama.io.rentacar.business.concretes;

import kodlama.io.rentacar.business.abstracts.CarService;
import kodlama.io.rentacar.business.dto.requests.create.CreateCarRequest;
import kodlama.io.rentacar.business.dto.requests.update.UpdateCarRequest;
import kodlama.io.rentacar.business.dto.responses.create.CreateCarResponse;
import kodlama.io.rentacar.business.dto.responses.get.GetAllCarsResponse;
import kodlama.io.rentacar.business.dto.responses.get.GetCarResponse;
import kodlama.io.rentacar.business.dto.responses.update.UpdateCarResponse;
import kodlama.io.rentacar.business.rules.CarBusinessRules;
import kodlama.io.rentacar.entities.concretes.Car;
import kodlama.io.rentacar.entities.enums.State;
import kodlama.io.rentacar.repository.abstracts.CarRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CarManager implements CarService {
    private final CarRepository repository;
    private final ModelMapper mapper;
    private final CarBusinessRules rules;

    @Override
    public List<GetAllCarsResponse> getAll(boolean showMaintenance) {
        List<Car> cars = filterCarsByMaintenanceState(showMaintenance);
        List<GetAllCarsResponse> response = cars.stream()
                .map(car -> mapper.map(car, GetAllCarsResponse.class)).toList();
        return response;
    }


    @Override
    public CreateCarResponse add(CreateCarRequest car) {
        Car carSave = mapper.map(car, Car.class);
        carSave.setId(0);
        carSave.setState(State.AVAILABLE);
        Car carResponse = repository.save(carSave);
        return mapper.map(carResponse, CreateCarResponse.class);
    }

    @Override
    public void delete(int id) {
        rules.checkIfCarExists(id);
        repository.deleteById(id);
    }

    @Override
    public UpdateCarResponse update(int id, UpdateCarRequest car) {
        rules.checkIfCarExists(id);
        Car updateCar = mapper.map(car, Car.class);
        updateCar.setId(id);
        Car carResponse = repository.save(updateCar);
        return mapper.map(carResponse, UpdateCarResponse.class);
    }

    @Override
    public GetCarResponse getById(int id) {
        Car car = repository.findById(id).orElseThrow();
        GetCarResponse response = mapper.map(car, GetCarResponse.class);
        return response;
    }

    @Override
    public void changeState(int carId, State state) {
        Car car = repository.findById(carId).orElseThrow();
        car.setState(state);
        repository.save(car);
    }


    private List<Car> filterCarsByMaintenanceState(boolean includeMaintenance) {
        if (includeMaintenance) {
            return repository.findAll();
        }
        return repository.findAllByStateIsNot(State.MAINTENANCE);
    }


}
