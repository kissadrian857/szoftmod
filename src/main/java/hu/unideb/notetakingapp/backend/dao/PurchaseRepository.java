package hu.unideb.notetakingapp.backend.dao;

import hu.unideb.notetakingapp.api.entity.Purchase;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PurchaseRepository extends BaseEntityRepository<Purchase> {
    @Query(value = "SELECT p FROM Purchase p WHERE p.customerId = :customerId")
    Purchase findPurchaseByCustomer(@Param("customerId") Long customerId);

    @Query(value = "SELECT p FROM Purchase p WHERE p.isGifted = true")
    List<Purchase> findGiftedNotes();
}
