package kodlama.io.rentacar.api.controllers;

import kodlama.io.rentacar.business.abstracts.CarService;
import kodlama.io.rentacar.business.dto.requests.create.CreateCarRequest;
import kodlama.io.rentacar.business.dto.requests.update.UpdateCarRequest;
import kodlama.io.rentacar.business.dto.responses.create.CreateCarResponse;
import kodlama.io.rentacar.business.dto.responses.get.GetAllCarsResponse;
import kodlama.io.rentacar.business.dto.responses.get.GetCarResponse;
import kodlama.io.rentacar.business.dto.responses.update.UpdateCarResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
@AllArgsConstructor
public class CarsController {
    private final CarService service;



    @GetMapping("/{withoutMaintenance}")
    public List<GetAllCarsResponse> getAll(@PathVariable  boolean withoutMaintenance) {
        return service.getAll(withoutMaintenance);
    }

    @GetMapping("/{id}")
    public GetCarResponse getById(@PathVariable  int id){
        return service.getById(id);
    }
    @PutMapping("/{id}")
    public UpdateCarResponse update(@PathVariable int id, @RequestBody UpdateCarRequest car){
        return service.update(id, car);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id){
        service.delete(id);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateCarResponse add(@RequestBody CreateCarRequest car){
        return service.add(car);
    }


}
