package hu.unideb.notetakingapp.frontend.controller;

import hu.unideb.notetakingapp.api.entity.Note;
import hu.unideb.notetakingapp.api.entity.User;
import hu.unideb.notetakingapp.api.service.NoteService;
import hu.unideb.notetakingapp.api.service.UserService;
import hu.unideb.notetakingapp.frontend.controller.helper.IsFormVisibleBean;
import hu.unideb.notetakingapp.frontend.controller.helper.SelectedNoteBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.Comparator;

@Controller
public class NotesController {
    private final LoggedInUserBean loggedInUserBean;
    private final IsFormVisibleBean isFormVisibleBean;
    private final SelectedNoteBean selectedNoteBean;
    private final UserService userService;
    private final NoteService noteService;

    public NotesController(LoggedInUserBean loggedInUserBean, IsFormVisibleBean isFormVisibleBean, SelectedNoteBean selectedNoteBean, UserService userService, NoteService noteService) {
        this.loggedInUserBean = loggedInUserBean;
        this.isFormVisibleBean = isFormVisibleBean;
        this.selectedNoteBean = selectedNoteBean;
        this.userService = userService;
        this.noteService = noteService;
    }

    @ModelAttribute("is_visible")
    public Boolean isVisible() {
        return isFormVisibleBean.getVisible();
    }

    @ModelAttribute("selected_note")
    public Note selectedNote() {
        return selectedNoteBean.getSelectedNote();
    }

    @GetMapping("/notes")
    public String Notes(Model model) {
        if (!loggedInUserBean.isLoggedIn())
            return "redirect:/login";

        model.addAttribute("new_note", new Note());
        model.addAttribute("user", loggedInUserBean.getLoggedInUser());
        model.addAttribute("notes", noteService.findAll().stream()
                .filter(note -> note.getCreatorUser().getUserName().equals(loggedInUserBean.getLoggedInUser().getUserName()))
                .sorted(Comparator.comparing(Note::getTitle))
                .toArray());
        return "notes";
    }

    @GetMapping("/addNote")
    public String addNote(Model model) {
        if (!loggedInUserBean.isLoggedIn())
            return "redirect:/login";

        Note note = new Note();
        note.setTitle("");
        note.setBody("");
        note.setCreationDate(LocalDate.now());
        User actUser = userService.findByUsername(loggedInUserBean.getLoggedInUser().getUserName());
        note.setCreatorUser(actUser);
        noteService.save(note);
        selectedNoteBean.setSelectedNote(note);
        isFormVisibleBean.setVisible(true);
        return "redirect:/notes";
    }

    @GetMapping("/deleteNote")
    public String deleteNote(@RequestParam Long id, Model model) {
        if (!loggedInUserBean.isLoggedIn())
            return "redirect:/login";

        Note toDelete = noteService.findById(id);
        noteService.delete(toDelete);
        return "redirect:/notes";
    }

    @GetMapping("/selectNote")
    public String initUpdate(@RequestParam Long id, Model model) {
        if (!loggedInUserBean.isLoggedIn())
            return "redirect:/login";

        Note updatable = noteService.findById(id);
        selectedNoteBean.setSelectedNote(updatable);
        isFormVisibleBean.setVisible(true);
        return "redirect:/notes";
    }

    @PostMapping("/updateNote")
    public String updateNote(@ModelAttribute("note") Note note, Model model) {
        if (!loggedInUserBean.isLoggedIn())
            return "redirect:/login";

        selectedNote().setTitle(note.getTitle());
        selectedNote().setBody(note.getBody());
        selectedNote().setLastModifiedDate(LocalDate.now());
        noteService.save(selectedNote());

        selectedNoteBean.setSelectedNote(null);
        isFormVisibleBean.setVisible(false);
        return "redirect:/notes";
    }
}