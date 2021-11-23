package hu.unideb.notetakingapp.bootstrap;

import hu.unideb.notetakingapp.api.entity.Note;
import hu.unideb.notetakingapp.api.entity.User;
import hu.unideb.notetakingapp.api.service.NoteService;
import hu.unideb.notetakingapp.api.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class BootStrapData implements CommandLineRunner {

    private final NoteService noteService;

    private final UserService userService;

    public BootStrapData(NoteService noteService, UserService userService) {
        this.noteService = noteService;
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        User user1 = new User("", "");
        user1.setCredits(20);
        User user2 = new User("randomUserName2", "randomPasswd2");
        user2.setCredits(30);

        if (userService.save(user1) != null) {
            Note note = new Note("randomBody3221...", "title1", "summary1", LocalDate.now(), null, user1);
            Note note2 = new Note("randomBody2.21323..", "title2", "summary2", LocalDate.now(), null, user1);
            noteService.save(note);
            noteService.save(note2);
        }
        if (userService.save(user2) != null) {
            Note note3 = new Note("randomBody3.4434..", "title3", "summary3", LocalDate.now(), null, user2);
            note3.setCreditValue(5);
            Note note4 = new Note("freeBody65655", "freeNoteTitle123232323232323233", "freeNoteSummary", LocalDate.now(), null, user2);
            note4.setCreditValue(6);
            noteService.save(note3);
            noteService.save(note4);
        }

        System.out.println("Webpage available at: http://localhost:8080/login");
    }

}