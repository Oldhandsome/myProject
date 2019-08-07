package dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import entity.Note;

@Repository("noteDaoImpl")
public interface NoteDao {
	public List<Note> findByIds(
			@Param("note_book_id") String note_book_id,
			@Param("note_status_id") String note_status_id);
	public String loadNoteContent(String note_id);
	public void updateNote(@Param("note_id") String note_id,
							@Param("note_content") String note_content,
							@Param("updated_at") long updated_at);
	
}
