package kodlama.io.rentacar.repository.abstracts;

import kodlama.io.rentacar.entities.concretes.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    Payment findByCardNumber(String cardNumber);

    boolean existsByCardNumber(String cardNumber);

    boolean existsByCardNumberAndCardHolderNameAndCardExpirationYearAndCardExpirationMonthAndCardCvv(
            String cardNumber, String cardHolderName, int cardExpirationYear, int cardExpirationMonth, String cardCvv
    );


//     SPeL -> spring expression language
//        @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END" +
//                " FROM Payment p WHERE p.cardNumber = :#{paymentRequest.cardNumber}" +
//                " AND p.cardHolder = :#{paymentRequest.cardHolder} AND" +
//                " p.cardExpirationYear = :#{paymentRequest.cardExpirationYear} AND" +
//                " p.cardExpirationMonth = :#{paymentRequest.cardExpirationMonth} AND" +
//                " p.cardCvv = :#{paymentRequest.cardCvv}"
//        )
//        boolean existsByCardNumberAndCardHolderAndCardExpirationYearAndCardExpirationMonthAndCardCvv(
//                @Param("paymentRequest") CreatePaymentRequest paymentRequest);
}
