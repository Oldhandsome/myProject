package dao;

import entity.Information;
import entity.Note;

import java.util.List;

public interface NoteDao{

    Note findByNoteId(String note_id);

    List<Note> findByBookId(String book_id);

    String loadContent(String note_id);

    int updateNote(Note note);

    int addNote(Note note);

    int deleteNote(String id);

    int starNote(String id);

    int unstarNote(String id);

    int moveNote(String book_id, String note_id);

    int moveToTrash(String note_id);

    List<Information> findByName(String user_id, String note_title);

    List<Information> trash(String user_id);

    int moveNotesToTrash(String ids);

    int trashRecovery(String ids);
}
