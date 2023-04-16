package kodlama.io.rentacar.business.concretes;

import kodlama.io.rentacar.business.abstracts.ModelService;
import kodlama.io.rentacar.business.dto.requests.create.CreateModelRequest;
import kodlama.io.rentacar.business.dto.requests.update.UpdateModelRequest;
import kodlama.io.rentacar.business.dto.responses.create.CreateModelResponse;
import kodlama.io.rentacar.business.dto.responses.get.GetAllModelsResponse;
import kodlama.io.rentacar.business.dto.responses.get.GetModelResponse;
import kodlama.io.rentacar.business.dto.responses.update.UpdateModelResponse;
import kodlama.io.rentacar.entities.concretes.Model;
import kodlama.io.rentacar.repository.abstracts.ModelRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class ModelManager implements ModelService {

    private final ModelRepository repository;
    private final ModelMapper mapper;

    @Override
    public List<GetAllModelsResponse> getAll() {
        List<Model> models = repository.findAll();
        List<GetAllModelsResponse> response = models.stream()
                .map(model -> mapper.map(model,GetAllModelsResponse.class)).toList();
        return response;
    }

    @Override
    public CreateModelResponse add(CreateModelRequest model) {
        Model modelSave = mapper.map(model, Model.class);
        modelSave.setId(0);
        Model modelResponse = repository.save(modelSave);
        return mapper.map(modelResponse,CreateModelResponse.class);
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }

    @Override
    public UpdateModelResponse update(int id, UpdateModelRequest model) {
        Model updateModel = mapper.map(model, Model.class);
        updateModel.setId(id);
        Model modelResponse = repository.save(updateModel);
        return mapper.map(modelResponse,UpdateModelResponse.class);
    }

    @Override
    public GetModelResponse getById(int id) {
        checkIfModelExists(id);
        Model model = repository.findById(id).orElseThrow();
        return mapper.map(model,GetModelResponse.class);
    }
    private void checkIfModelExists(int id){
        if(!repository.existsById(id)){
            throw new RuntimeException("Model bulunamadı!");
        }
    }
}
