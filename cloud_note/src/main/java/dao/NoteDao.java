package dao;


import java.util.List;
import java.util.Map;

import entity.Information;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import entity.Note;

@Repository("noteDaoImpl")
public interface NoteDao {
	public List<Note> findByIds(String note_book_id);
	public String loadNoteContent(String note_id);
	public void updateNote(Note note);
	public int addNote(Note note);
	public int deleteNote(String note_id);
	public int starNote(String note_id);
	public int unstarNote(String note_id);
	public int moveNote(@Param("note_id") String note_id,
						@Param("note_book_id") String note_book_id,
						@Param("updated_at") long updated_at);
	public int moveToTrush(String note_id);
	public List<Information> searchNotes(@Param("user_id") String user_id,
								  @Param("note_title") String note_title);
	public List<Information> trash(String user_id);
	public int trashRecover(String note_id);
	public List<Information> loadNotes(@Param("status") String status_id,
                                       @Param("user_id") String user_id);
	public int deleteNotes(Map<String,Object> map);
	public int moveNotesToTrush(Map<String , Object> map);
	public int trashNotesRecover(Map<String , Object> map);
}
