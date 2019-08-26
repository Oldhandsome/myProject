package dao;

import entity.NoteBook;

import java.util.List;

public interface NoteBookDao {
    List<NoteBook> findBooksByUserId(String user_id);

    int addBook(NoteBook book);

    int updateBook(NoteBook book);

    int deleteNoteBook(String id);
}
