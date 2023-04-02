package kodlama.io.rentacar.business.concretes;

import kodlama.io.rentacar.business.abstracts.BrandService;
import kodlama.io.rentacar.business.dto.requests.create.CreateBrandRequest;
import kodlama.io.rentacar.business.dto.requests.update.UpdateBrandRequest;
import kodlama.io.rentacar.business.dto.responses.create.CreateBrandResponse;
import kodlama.io.rentacar.business.dto.responses.get.GetAllBrandsResponse;
import kodlama.io.rentacar.business.dto.responses.get.GetBrandResponse;
import kodlama.io.rentacar.business.dto.responses.update.UpdateBrandResponse;
import kodlama.io.rentacar.entities.concretes.Brand;
import kodlama.io.rentacar.repository.abstracts.BrandRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
//@ ler hepsinde çalışır ama optimize bir şekilde çalışmaz o yüzden her birini doğru yerde kullanmalıyız
public class BrandManager implements BrandService {

    private final BrandRepository repository;
    private final ModelMapper mapper;




    @Override
    public List<GetAllBrandsResponse> getAll() {
        List<Brand> brands = repository.findAll();
        List<GetAllBrandsResponse> response = brands.stream()
                .map(brand -> mapper.map(brand,GetAllBrandsResponse.class)).toList();
        return response;
    }

    @Override
    public CreateBrandResponse add(CreateBrandRequest brand) {
        Brand brandSave = mapper.map(brand, Brand.class);
        brandSave.setId(0);
        Brand responseBrand = repository.save(brandSave);
        CreateBrandResponse response = mapper.map(responseBrand,CreateBrandResponse.class);
        return response;
    }

    @Override
    public void delete(int id) {
        checkIfBrandExists(id);
        repository.deleteById(id);
    }

    @Override
    public UpdateBrandResponse update(int id, UpdateBrandRequest brand) {
        Brand updateBrand = mapper.map(brand, Brand.class);
        updateBrand.setId(id);
        repository.save(updateBrand);
        UpdateBrandResponse response = mapper.map(brand,UpdateBrandResponse.class);
        return response;
    }

    @Override
    public GetBrandResponse getById(int id) {
        checkIfBrandExists(id);
        Brand brand = repository.findById(id).orElseThrow();
        GetBrandResponse response = mapper.map(brand,GetBrandResponse.class);
        return response;
    }

    private void checkIfBrandExists(int id) {
        if(!repository.existsById(id)) throw new RuntimeException("Marka bulunamadı");
    }
//    private void validateBrand(Brand brand){
//        checkIfNameValid(brand);
//
//    }
//    private void checkIfNameValid(Brand brand){
//        if(brand.getName().length() < 10 || brand.getName().length() > 20){
//            throw new IllegalArgumentException("İsim 10 karakterden uzun, 20 karakterden kısa olmalıdır!");
//        }
//    }

}
