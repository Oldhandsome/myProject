package service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import dao.NoteDao;
import entity.Note;
import util.NoteResult;

@Service("noteServiceImpl")
public class NoteServiceImpl implements NoteService{

	@Resource(name="noteDaoImpl")
	private NoteDao dao;

	public NoteResult<List<Note>> loadBookNotes(String note_book_id){
		List<Note> notes = dao.findByIds(note_book_id);
		NoteResult<List<Note>> result = new NoteResult<List<Note>>();
		if(notes == null) {
			result.setStatus(2);
			result.setMsg("查询笔记失败");
			return result;
		}
		if(notes.isEmpty()) {
			result.setStatus(1);
			result.setMsg("查询完成，但该笔记本的笔记数目为0！");
			return result;
		}
		result.setData(notes);
		result.setMsg("该笔记本的笔记查询完成！");
		result.setStatus(0);
		return result;
	}
	@Override
	public NoteResult<String> loadNoteContent(String note_id) {
		NoteResult<String> result = new NoteResult<String>();
		String content = dao.loadNoteContent(note_id);
		result.setStatus(0);
		result.setMsg("查询内容完成");
		result.setData(content);
		return result;
	}
	@Override
	public NoteResult<String> updateNote(String note_id, String note_content) {
		NoteResult<String> result = new NoteResult<String>();
		long updated_at = new Date().getTime();
		dao.updateNote(note_id, note_content, updated_at);
		result.setStatus(0);
		result.setMsg("更新成功");
		return result;
	}
	@Override
	public NoteResult<Note> addNote(Note note) {
		NoteResult<Note> result = new NoteResult<Note>();
		int row = dao.addNote(note);
//		int row = 1;
		if(row  == 1){
			result.setStatus(0);
			result.setMsg("插入成功");
			result.setData(note);
		}else{
			result.setStatus(1);
			result.setMsg("插入笔记失败");
		}
		return result;
	}

	@Override
	public NoteResult deleteNote(String note_id) {
		NoteResult<String> result = new NoteResult<String>();
		int row = dao.deleteNote(note_id);
		if(row == 1){
			result.setStatus(0);
			result.setMsg("笔记删除成功");
		}
		else{
			result.setStatus(1);
			result.setMsg("笔记删除失败");
		}
		return result;
	}

	@Override
	public NoteResult starNote(String note_id) {
		NoteResult result = new NoteResult();
		int rows = dao.starNote(note_id);
//		int rows = 1;
		if(rows == 1){
			result.setStatus(0);
			result.setMsg("收藏成功");
		}else{
			result.setStatus(1);
			result.setMsg("收藏出现错误");
		}
		return result;
	}

	@Override
	public NoteResult unstartNote(String note_id) {
		NoteResult result = new NoteResult();
		int rows = dao.unstarNote(note_id);
		if(rows == 1){
			result.setStatus(0);
			result.setMsg("取消收藏成功");
		}else{
			result.setStatus(1);
			result.setMsg("取消收藏出现错误");
		}
		return result;
	}

	@Override
	public NoteResult moveNote(String note_id, String note_book_id) {
		NoteResult result = new NoteResult();
//		int rows = dao.moveNote(note_id,note_book_id,new Date().getTime());
		int rows = 1;
		if(rows == 1){
			result.setStatus(0);
			result.setMsg("移动笔记成功");
		}else {
			result.setStatus(1);
			result.setMsg("移动笔记失败");
		}
		return result;
	}
}
