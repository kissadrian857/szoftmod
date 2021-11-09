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
        User user2 = new User("randomUserName2", "randomPasswd2");
        Note note = new Note("randomBody...", "title1", "summary1", LocalDate.now(), null, user1);
        Note note2 = new Note("randomBody2...", "title2", "summary2", LocalDate.now(), null, user1);
        Note note3 = new Note("randomBody3...", "title3", "summary3",  LocalDate.now(), null, user2);
        Note note4 = new Note("freeBody", "freeNoteTitle", "freeNoteSummary", LocalDate.now(), null, user2);
        note4.setCreditValue(0);
        userService.save(user1);
        userService.save(user2);

        noteService.save(note);
        noteService.save(note2);
        noteService.save(note4);

        System.out.println(note3.getId());
        System.out.println(noteService.save(note3).getId());
        System.out.println(note3.getId());

        System.out.println("Started in Bootstrap...");

        User u = userService.findByUsername("randomUserName2");
        System.out.println(u.getUserName() + " " + u.getPasswordHash());

        System.out.println("Webpage available at: http://localhost:8080/login");
    }

}