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

    @Override
    public List<Long> getPurchasedNotesById(Long customerId) {
        List<Long> noteIDs = new ArrayList<>();
        List<Purchase> purchases = ((PurchaseRepository) baseEntityRepository).findPurchaseByCustomer(customerId);
        for (Purchase purchase : purchases) {
            noteIDs.add(purchase.getNote().getId());
        }
        return noteIDs;
    }

    public List<Long> findPurchaseByCustomer(Long id){
        List<Purchase> purchases = ((PurchaseRepository) baseEntityRepository).findPurchaseByCustomer(id);
        List<Long> ids= new ArrayList<>();
        for (int i =0 ;i<purchases.size();i++){
            ids.add(purchases.get(i).getNote().getId());
        }

        return ids;
    }

}
