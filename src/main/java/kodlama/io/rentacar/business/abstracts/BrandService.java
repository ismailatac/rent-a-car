package kodlama.io.rentacar.business.abstracts;

import kodlama.io.rentacar.entities.concretes.Brand;

import java.util.List;

public interface BrandService {
    List<Brand> getAll();
    Brand add(Brand brand);
    void delete(int id);
    Brand update(int id, Brand brand);
    Brand getById(int id);
}
