package service;

import java.util.List;

import entity.Note;
import util.NoteResult;

public interface NoteService {
	public NoteResult<List<Note>> loadBookNotes(String note_book_id);
	public NoteResult<String> loadNoteContent(String note_id);
	public NoteResult<String> updateNote(String note_id,String note_content);
	public NoteResult<Note> addNote(Note note);
	public NoteResult<String> deleteNote(String note_id);
	public NoteResult starNote(String note_id);
	public NoteResult unstartNote(String note_id);
	public NoteResult moveNote(String note_id,String note_book_id);
	public NoteResult moveToTrush(String note_id);
}
