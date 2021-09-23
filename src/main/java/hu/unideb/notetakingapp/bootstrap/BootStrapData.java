package hu.unideb.notetakingapp.bootstrap;

import hu.unideb.notetakingapp.api.entity.Note;
import hu.unideb.notetakingapp.api.entity.User;
import hu.unideb.notetakingapp.backend.dao.NoteRepository;
import hu.unideb.notetakingapp.backend.dao.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class BootStrapData implements CommandLineRunner {

    private final NoteRepository noteRepository;

    private final UserRepository userRepository;

    //itt injektáljuk be a bean-eket!
    public BootStrapData(NoteRepository noteRepository, UserRepository userRepository) {
        this.noteRepository = noteRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        User user1 = new User("randomUserName", "randomPasswd");
        User user2 = new User("randomUserName2", "randomPasswd2");
        Note note = new Note("randomBody...", "title1", LocalDate.now(), null, user1);
        Note note2 = new Note("randomBody2...", "title2", LocalDate.now(), null, user1);
        Note note3 = new Note("randomBody3...", "title3", LocalDate.now(), null, user2);

        userRepository.save(user1);
        userRepository.save(user2);

        noteRepository.save(note);
        noteRepository.save(note2);
        noteRepository.save(note3);

        System.out.println("Started in Bootstrap...");
        System.out.println("Number of notes: " + noteRepository.count());
        System.out.println("Number of users:" + userRepository.count());
    }
}
