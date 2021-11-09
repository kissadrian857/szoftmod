package hu.unideb.notetakingapp.backend.dao;

import hu.unideb.notetakingapp.api.entity.Purchase;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PurchaseRepository extends BaseEntityRepository<Purchase> {
    @Query(value = "SELECT p FROM Purchase p WHERE p.customerId = :customerId")
    List<Purchase> findPurchaseByCustomer(@Param("customerId") Long customerId);

      /*Commented by Imre Adrián Kiss 2021.nov.9 22:53:11:003d1221*/
//    @Query(value = "SELECT p FROM Purchase p WHERE p.isGifted = true and ")
//    List<Purchase> findGiftedNotes();

}
