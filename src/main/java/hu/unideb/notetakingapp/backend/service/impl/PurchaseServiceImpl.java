package hu.unideb.notetakingapp.backend.service.impl;

import hu.unideb.notetakingapp.api.entity.Note;
import hu.unideb.notetakingapp.api.entity.Purchase;
import hu.unideb.notetakingapp.api.service.PurchaseService;
import hu.unideb.notetakingapp.backend.dao.BaseEntityRepository;
import hu.unideb.notetakingapp.backend.dao.PurchaseRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PurchaseServiceImpl extends CoreServiceImpl<Purchase> implements PurchaseService {

    public PurchaseServiceImpl(BaseEntityRepository<Purchase> baseEntityRepository) {
        super(baseEntityRepository);
    }

//    @Override
//    public List<Purchase> findGiftedNotes() {
//        return ((PurchaseRepository) baseEntityRepository).findGiftedNotes();
//    }

    @Override
    public List<Long> getPurchasedNotesById(Long customerId) {
        List<Long> noteIDs = new ArrayList<>();
        List<Purchase> purchases = ((PurchaseRepository) baseEntityRepository).findPurchaseByCustomer(customerId);
        for (Purchase purchase : purchases) {
            noteIDs.add(purchase.getNote().getId());
        }
        return noteIDs;
    }


}
