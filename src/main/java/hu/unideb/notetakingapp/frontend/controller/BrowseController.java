package hu.unideb.notetakingapp.frontend.controller;

import hu.unideb.notetakingapp.api.entity.Note;
import hu.unideb.notetakingapp.api.entity.Purchase;
import hu.unideb.notetakingapp.api.entity.User;
import hu.unideb.notetakingapp.api.service.NoteService;
import hu.unideb.notetakingapp.api.service.PurchaseService;
import hu.unideb.notetakingapp.api.service.UserService;
import hu.unideb.notetakingapp.frontend.controller.helper.LoggedInUserBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class BrowseController {
    private final LoggedInUserBean loggedInUserBean;
    private final UserService userService;
    private final NoteService noteService;
    private final PurchaseService purchaseService;
    
    private Long actUserId;
    
    @PostConstruct
    private void init(){
        actUserId = actUserId;
    }

    public BrowseController(LoggedInUserBean loggedInUserBean, UserService userService, NoteService noteService, PurchaseService purchaseService) {
        this.loggedInUserBean = loggedInUserBean;
        this.userService = userService;
        this.noteService = noteService;
        this.purchaseService = purchaseService;
    }

    @GetMapping({"/browse"})
    public String browse(Model model) {
        model.addAttribute("loggedInUser", loggedInUserBean.getLoggedInUser());

        if (loggedInUserBean.isLoggedIn()) {
            List<Long> purchases = purchaseService.getPurchasedNotesById(actUserId);
            List<Note> notes = noteService.findNotesExceptId(actUserId);

            for (Note note : notes) {
                if (!purchases.contains(note.getId()))
                    note.setBody("");
            }
            model.addAttribute("all_notes", notes);
        } else {
            model.addAttribute("all_notes", noteService.getFreeNotes());
        }

        return "browse";
    }

    @GetMapping({"/buy"})
    public String purchase(@RequestParam Long id, Model model) {
        if (!loggedInUserBean.isLoggedIn())
            return "redirect:/login";

        Note actNote = noteService.findById(id);
        User actUser = loggedInUserBean.getLoggedInUser();
        if (actUser.getCredits() >= actNote.getCreditValue()) {
            Purchase newPurchase = new Purchase();
            newPurchase.setPurchaseTime(LocalDateTime.now());
            newPurchase.setCustomerId(actUser.getId());
            newPurchase.setNote(actNote);
            newPurchase.setCreatorId(actNote.getCreatorUser().getId());
            actUser.setCredits(actUser.getCredits() - actNote.getCreditValue());
            purchaseService.save(newPurchase);
            userService.save(actUser);
        }
        return "redirect:/browse";
    }

    @ModelAttribute("get_purchased")
    public List<Long> getPurchased(){
        return purchaseService.findPurchaseByCustomer(actUserId);
    }
    
    @ModelAttribute("get_creditValue")
    public Integer getCreditValue(){
        return userService.findById(actUserId).getCredits();
    }
}
