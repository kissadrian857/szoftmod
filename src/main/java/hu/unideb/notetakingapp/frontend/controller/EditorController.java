package hu.unideb.notetakingapp.frontend.controller;

import hu.unideb.notetakingapp.api.entity.Note;
import hu.unideb.notetakingapp.api.service.NoteService;
import hu.unideb.notetakingapp.api.service.UserService;
import hu.unideb.notetakingapp.frontend.controller.helper.IsFormVisibleBean;
import hu.unideb.notetakingapp.frontend.controller.helper.LoggedInUserBean;
import hu.unideb.notetakingapp.frontend.controller.helper.SelectedNoteBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
public class EditorController {
    private final LoggedInUserBean loggedInUserBean;
    private final IsFormVisibleBean isFormVisibleBean;
    private final SelectedNoteBean selectedNoteBean;
    private final UserService userService;
    private final NoteService noteService;

    public EditorController(LoggedInUserBean loggedInUserBean, IsFormVisibleBean isFormVisibleBean, SelectedNoteBean selectedNoteBean, UserService userService, NoteService noteService) {
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

    @GetMapping("/editor")
    public String Notes(Model model) {
        if (!loggedInUserBean.isLoggedIn())
            return "redirect:/login";

        model.addAttribute("loggedInUser", loggedInUserBean.getLoggedInUser());
        model.addAttribute("new_note", new Note());
        model.addAttribute("user", loggedInUserBean.getLoggedInUser());
        model.addAttribute("notes", noteService.findNotesWithCreatorId(loggedInUserBean.getLoggedInUser().getId()));
        return "editor";
    }

    @GetMapping("/addNote")
    public String addNote(Model model) {
        if (!loggedInUserBean.isLoggedIn())
            return "redirect:/login";

        Note note = new Note();
        note.setTitle("");
        note.setBody("");
        note.setCreationDate(LocalDate.now());
        note.setCreatorUser(loggedInUserBean.getLoggedInUser());
        noteService.save(note);
        selectedNoteBean.setSelectedNote(note);
        isFormVisibleBean.setVisible(true);
        return "redirect:/editor";
    }

    @GetMapping("/deleteNote")
    public String deleteNote(@RequestParam Long id, Model model) {
        if (!loggedInUserBean.isLoggedIn())
            return "redirect:/login";

        Note toDelete = noteService.findById(id);
        if (toDelete != null && toDelete.getCreatorUser().equals(loggedInUserBean.getLoggedInUser())) {
            noteService.delete(toDelete);
        }
        return "redirect:/editor";
    }

    @GetMapping("/selectNote")
    public String initUpdate(@RequestParam Long id, Model model) {
        if (!loggedInUserBean.isLoggedIn())
            return "redirect:/login";

        Note updatable = noteService.findById(id);
        if (updatable != null && updatable.getCreatorUser().equals(loggedInUserBean.getLoggedInUser())) {
            selectedNoteBean.setSelectedNote(updatable);
            isFormVisibleBean.setVisible(true);
        }
        return "redirect:/editor";
    }

    @PostMapping("/updateNote")
    public String updateNote(@ModelAttribute("note") Note note, Model model) {
        if (!loggedInUserBean.isLoggedIn())
            return "redirect:/login";

        if (note != null && note.getTitle() != null && note.getBody() != null) {
            selectedNote().setTitle(note.getTitle());
            selectedNote().setBody(note.getBody());
            selectedNote().setLastModifiedDate(LocalDate.now());
            noteService.save(selectedNote());
            selectedNoteBean.setSelectedNote(null);
            isFormVisibleBean.setVisible(false);
        }
        return "redirect:/editor";
    }
}