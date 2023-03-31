package kodlama.io.rentacar.business.concretes;

import kodlama.io.rentacar.business.abstracts.CarService;
import kodlama.io.rentacar.business.dto.requests.create.CreateCarRequest;
import kodlama.io.rentacar.business.dto.requests.update.UpdateCarRequest;
import kodlama.io.rentacar.business.dto.responses.create.CreateCarResponse;
import kodlama.io.rentacar.business.dto.responses.get.GetAllCarsResponse;
import kodlama.io.rentacar.business.dto.responses.get.GetCarResponse;
import kodlama.io.rentacar.business.dto.responses.update.UpdateCarResponse;
import kodlama.io.rentacar.entities.concretes.Car;
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


    @Override
    public List<GetAllCarsResponse> getAll() {
        List<Car> cars = repository.findAll();
        List<GetAllCarsResponse> response = cars.stream()
                .map(car -> mapper.map(car,GetAllCarsResponse.class)).toList();
        return response;
    }

    @Override
    public CreateCarResponse add(CreateCarRequest car) {
        Car carSave = mapper.map(car, Car.class);
        carSave.setId(0);
        repository.save(carSave);
        CreateCarResponse response = mapper.map(carSave,CreateCarResponse.class);
        return response;
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }

    @Override
    public UpdateCarResponse update(int id, UpdateCarRequest car) {
        Car updateCar = mapper.map(car, Car.class);
        updateCar.setId(id);
        repository.save(updateCar);
        UpdateCarResponse response = mapper.map(updateCar,UpdateCarResponse.class);
        return response;
    }

    @Override
    public GetCarResponse getById(int id) {
        Car car = repository.findById(id).orElseThrow();
        GetCarResponse response = mapper.map(car,GetCarResponse.class);
        return response;
    }
}
