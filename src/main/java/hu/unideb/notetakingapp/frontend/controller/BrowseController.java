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
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class BrowseController {
    private final LoggedInUserBean loggedInUserBean;
    private final UserService userService;
    private final NoteService noteService;
    private final PurchaseService purchaseService;

    public BrowseController(LoggedInUserBean loggedInUserBean, UserService userService, NoteService noteService, PurchaseService purchaseService) {
        this.loggedInUserBean = loggedInUserBean;
        this.userService = userService;
        this.noteService = noteService;
        this.purchaseService = purchaseService;
    }

    @GetMapping({"/browse"})
    public String browse(Model model) {
        model.addAttribute("loggedInUser",loggedInUserBean.isLoggedIn() ? loggedInUserBean.getLoggedInUser() : null);

        if (loggedInUserBean.isLoggedIn()) {
            List<Long> purchases = purchaseService.getPurchasedNotesById(loggedInUserBean.getLoggedInUser().getId());
            List<Note> notes = noteService.findNotesExceptId(loggedInUserBean.getLoggedInUser().getId());

//            for (Note note : notes) {
//                if (!purchases.contains(note.getId()))
//                    note.setBody("");
//            }
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
        User seller = actNote.getCreatorUser();
        User actUser = loggedInUserBean.getLoggedInUser();
        if (actUser.getCredits() >= actNote.getCreditValue()) {
            Purchase newPurchase = new Purchase();
            newPurchase.setPurchaseTime(LocalDateTime.now());
            newPurchase.setCustomerId(actUser.getId());
            newPurchase.setNote(actNote);
            newPurchase.setCreatorId(actNote.getCreatorUser().getId());
            purchaseService.save(newPurchase);

            actUser.setCredits(actUser.getCredits() - actNote.getCreditValue());
            userService.save(actUser);

            seller.setCredits(seller.getCredits() + actNote.getCreditValue());
            userService.save(seller);
        }
        return "redirect:/browse";
    }

    @ModelAttribute("get_purchased")
    public List<Long> getPurchased() {
        return loggedInUserBean.isLoggedIn() ? purchaseService.findPurchaseByCustomer(loggedInUserBean.getLoggedInUser().getId()) : new ArrayList<>();
    }

    @ModelAttribute("get_creditValue")
    public Integer getCreditValue() {
        return loggedInUserBean.isLoggedIn() ? userService.findById(loggedInUserBean.getLoggedInUser().getId()).getCredits() : null;
    }
}
