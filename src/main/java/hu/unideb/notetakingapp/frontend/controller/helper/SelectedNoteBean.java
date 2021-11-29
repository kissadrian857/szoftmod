package hu.unideb.notetakingapp.frontend.controller.helper;

import hu.unideb.notetakingapp.api.entity.Note;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SelectedNoteBean {
    private Note selectedNote;

    public Note getSelectedNote() {
        return selectedNote;
    }

    public void setSelectedNote(Note selectedNote) {
        this.selectedNote = selectedNote;
    }

    public void setDefaultValues() {
        this.selectedNote =new Note();
        selectedNote.setTitle("");
        selectedNote.setBody("");
        selectedNote.setCreationDate(LocalDate.now());
        selectedNote.setCreditValue(0);
    }
}
