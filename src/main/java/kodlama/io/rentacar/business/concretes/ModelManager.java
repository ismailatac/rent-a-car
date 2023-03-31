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
        repository.save(modelSave);
        CreateModelResponse response = mapper.map(modelSave,CreateModelResponse.class);
        return response;
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }

    @Override
    public UpdateModelResponse update(int id, UpdateModelRequest model) {
        Model updateModel = mapper.map(model, Model.class);
        updateModel.setId(id);
        repository.save(updateModel);
        UpdateModelResponse response = mapper.map(updateModel,UpdateModelResponse.class);
        return response;
    }

    @Override
    public GetModelResponse getById(int id) {
        Model model = repository.findById(id).orElseThrow();
        GetModelResponse response = mapper.map(model,GetModelResponse.class);
        return response;
    }
}
