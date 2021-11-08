package hu.unideb.notetakingapp.api.service;

import hu.unideb.notetakingapp.api.entity.Purchase;

import java.util.List;

public interface PurchaseService extends CoreService<Purchase> {
    List<Purchase> findGiftedNotes();

    Purchase findPurchaseByCustomer(Long customerId);
}
