package service;

import entity.Information;
import entity.Note;

import java.util.List;

public interface NoteService {
    List<Note> getNotes(String book_id);

    Object loadContent(String note_id) throws RuntimeException;

    Object updateNote(Note note);

    Object addNote(Note note);

    Object deleteNote(String note_id);

    Object starNote(String note_id);

    Object unstarNote(String note_id);

    Object moveNote(String book_id, String note_id);

    List<Information> findNotesByName(String user_id, String note_title);

    List<Information> trash(String user_id);

    Object notesToTrash(String... ids);

    Object notesRecovery(String... ids);
}
