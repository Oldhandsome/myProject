package service;

import java.util.List;

import entity.Note;
import util.NoteResult;

public interface NoteService {
	public NoteResult<List<Note>> loadBookNotes(
			String note_book_id,String note_status_id);
	public NoteResult<String> loadNoteContent(String note_id);
	public NoteResult<String> updateNote(String note_id,String note_content);
	public NoteResult<String> addNote(Note note);
}
