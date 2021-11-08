package hu.unideb.notetakingapp.backend.service.impl;

import hu.unideb.notetakingapp.api.entity.Purchase;
import hu.unideb.notetakingapp.api.service.PurchaseService;
import hu.unideb.notetakingapp.backend.dao.BaseEntityRepository;
import hu.unideb.notetakingapp.backend.dao.PurchaseRepository;

import java.util.List;

public class PurchaseServiceImpl extends CoreServiceImpl<Purchase> implements PurchaseService {

    public PurchaseServiceImpl(BaseEntityRepository<Purchase> baseEntityRepository) {
        super(baseEntityRepository);
    }

    @Override
    public List<Purchase> findGiftedNotes() {
        return ((PurchaseRepository) baseEntityRepository).findGiftedNotes();
    }

    @Override
    public Purchase findPurchaseByCustomer(Long customerId) {
        return ((PurchaseRepository) baseEntityRepository).findPurchaseByCustomer(customerId);
    }
}
