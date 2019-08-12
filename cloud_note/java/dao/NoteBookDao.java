package dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import entity.NoteBook;

@Repository("noteBookDaoImpl")
public interface NoteBookDao {
	public List<NoteBook> findByUserId(String user_id);
	public int addNoteBook(NoteBook book);
	public int deleteNoteBook(String noteBookId);
}
