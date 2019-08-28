package service;

import entity.Information;
import entity.Note;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface NoteService {
    List<Note> getNotes(String book_id);

    String loadContent(String note_id) throws RuntimeException;

    String updateNote(Note note);

    String addNote(Note note);

    String deleteNote(String note_id);

    String noteToTrash(String note_id);

    @Transactional
    String tnoteRecover(String note_id) throws RuntimeException;

    String starNote(String note_id);

    String unstarNote(String note_id);

    String moveNote(String book_id, String note_id);

    List<Information> findNotesByName(String user_id, String note_title);

    List<Information> trash(String user_id);

    String notesToTrash(String... ids);

    String notesRecovery(String... ids);

    String deleteNotes(String[] ids);
}
