package kodlama.io.rentacar.business.concretes;

import kodlama.io.rentacar.business.abstracts.BrandService;
import kodlama.io.rentacar.entities.concretes.Brand;
import kodlama.io.rentacar.repository.abstracts.BrandRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
//@ ler hepsinde çalışır ama optimize bir şekilde çalışmaz o yüzden her birini doğru yerde kullanmalıyız
public class BrandManager implements BrandService {

    private final BrandRepository repository;




    @Override
    public List<Brand> getAll() {
        return repository.findAll();
    }

    @Override
    public Brand add(Brand brand) {
        return repository.save(brand);
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }

    @Override
    public Brand update(int id, Brand brand) {
        brand.setId(id);
        return repository.save(brand);
    }

    @Override
    public Brand getById(int id) {
        checkIfBrandExists(id);
        return repository.findById(id).orElseThrow();
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
