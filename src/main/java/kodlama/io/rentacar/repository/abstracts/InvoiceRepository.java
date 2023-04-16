package kodlama.io.rentacar.repository.abstracts;

import kodlama.io.rentacar.entities.concretes.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

//@transcational --> işlem başarısız olursa önceki yaptığı repo işlemlerini iptal eder. Metodlarda kullanılır.
public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {
}
