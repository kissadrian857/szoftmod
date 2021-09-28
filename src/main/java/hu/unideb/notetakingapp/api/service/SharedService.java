package hu.unideb.notetakingapp.api.service;

import hu.unideb.notetakingapp.api.entity.Shared;

import java.util.List;

public interface SharedService extends CoreService<Shared> {
    List<Shared> findByUserId(Long id);

    List<Shared> findByNoteId(Long id);
}
