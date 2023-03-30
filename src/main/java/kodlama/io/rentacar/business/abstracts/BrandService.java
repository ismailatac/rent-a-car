package kodlama.io.rentacar.business.abstracts;

import kodlama.io.rentacar.business.dto.requests.create.CreateBrandRequest;
import kodlama.io.rentacar.business.dto.requests.update.UpdateBrandRequest;
import kodlama.io.rentacar.business.dto.responses.create.CreateBrandResponse;
import kodlama.io.rentacar.business.dto.responses.get.GetAllBrandsResponse;
import kodlama.io.rentacar.business.dto.responses.get.GetBrandResponse;
import kodlama.io.rentacar.business.dto.responses.update.UpdateBrandResponse;


import java.util.List;

public interface BrandService {
    List<GetAllBrandsResponse> getAll();
    CreateBrandResponse add(CreateBrandRequest brand);
    void delete(int id);
    UpdateBrandResponse update(int id, UpdateBrandRequest brand);
    GetBrandResponse getById(int id);
}
