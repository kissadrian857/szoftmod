package hu.unideb.notetakingapp.api.service;

import hu.unideb.notetakingapp.api.entity.Note;
import hu.unideb.notetakingapp.api.entity.Purchase;

import java.util.List;

public interface PurchaseService extends CoreService<Purchase> {
//    List<Purchase> findGiftedNotes();

    List<Long> getPurchasedNotesById(Long id);

    public List<Long> findPurchaseByCustomer(Long id);
}
