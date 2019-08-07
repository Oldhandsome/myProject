package service;

import java.util.List;

import entity.NoteBook;
import util.NoteResult;

public interface NoteBookService {

	public NoteResult<List<NoteBook>> loadUserNoteBooks(String user_id);
	public NoteResult<String> addNoteBook(NoteBook book);
}
