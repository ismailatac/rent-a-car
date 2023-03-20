package kodlama.io.rentacar.repository.abstracts;

import kodlama.io.rentacar.entities.concretes.Brand;

import java.util.List;

public interface BrandRepository {
    List<Brand> getAll();
    Brand add(Brand brand);
    void delete(int id);
    Brand update(int id, Brand brand);
    Brand getById(int id);
}
