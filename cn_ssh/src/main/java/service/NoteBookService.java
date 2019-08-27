package service;

import entity.NoteBook;

import java.util.List;

public interface NoteBookService {
    List<NoteBook> loadUserNoteBook(String user_id);

    NoteBook addNoteBook(NoteBook noteBook);

    NoteBook updateNoteBook(NoteBook noteBook);
}
