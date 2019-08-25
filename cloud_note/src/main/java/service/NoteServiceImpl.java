package service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import entity.Information;
import org.apache.ibatis.javassist.expr.NewArray;
import org.apache.ibatis.mapping.DatabaseIdProvider;
import org.springframework.stereotype.Service;

import dao.NoteDao;
import entity.Note;
import org.springframework.transaction.annotation.Transactional;
import util.NoteResult;

@Service("noteServiceImpl")
public class NoteServiceImpl implements NoteService{

	@Resource(name="noteDaoImpl")
	private NoteDao dao;

	/**
	 * 加载笔记本中的笔记
	 * @param note_book_id
	 * @return
	 */
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

	/**
	 * 加载笔记的内容
	 * @param note_id
	 * @return result
	 */
	@Override
	public NoteResult<String> loadNoteContent(String note_id) {
		NoteResult<String> result = new NoteResult<String>();
		String content = dao.loadNoteContent(note_id);
		result.setStatus(0);
		result.setMsg("查询内容完成");
		result.setData(content);
		return result;
	}

	/**
	 * 更新笔记的内容，标题
	 * @param note_id
	 * @param note_title
	 * @param note_content
	 * @return
	 */
	@Override
	public NoteResult<String> updateNote(String note_id, String note_title,String note_content) {
		NoteResult<String> result = new NoteResult<String>();
		Note note = new Note();
		note.setNote_id(note_id);
		note.setNote_title(note_title);
		note.setUpdated_at(new Date().getTime());
		note.setNote_content(note_content);
		dao.updateNote(note);
		result.setStatus(0);
		result.setMsg("更新成功");
		return result;
	}

	/**
	 * 添加一个笔记
	 * @param note
	 * @return
	 */
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

	/**
	 * 删除一个笔记
	 * @param note_id
	 * @return
	 */
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

	/**
	 * 收藏一个笔记
	 * @param note_id
	 * @return
	 */
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

	/**
	 * 取消收藏一个笔记
	 * @param note_id
	 * @return
	 */
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

	/**
	 * 移动笔记至另一个笔记本
	 * @param note_id
	 * @param note_book_id
	 * @return
	 */
	@Override
	public NoteResult moveNote(String note_id, String note_book_id) {
		NoteResult<Note> result = new NoteResult<Note>();
		int rows = dao.moveNote(note_id,note_book_id,new Date().getTime());
//		int rows = 1;
		if(rows == 1){
			result.setStatus(0);
			result.setMsg("移动笔记成功");
		}else {
			result.setStatus(1);
			result.setMsg("移动笔记失败");
		}
		return result;
	}

	/**
	 * 将笔记放入回收站
	 * @param note_id
	 * @return
	 */
	@Override
	public NoteResult moveToTrush(String note_id){
		NoteResult result = new NoteResult();
		int rows = dao.moveToTrush(note_id);
//		int rows = 1;
		if(rows == 1){
			result.setStatus(0);
			result.setMsg("已移至回收站");
		}
		else{
			result.setStatus(1);
			result.setMsg("移至回收站出现错误");
		}
		return result;
	}

	/**
	 * 搜索笔记（名字的模糊查询）
	 * @param user_id
	 * @param note_title
	 * @return
	 */
	@Override
	public NoteResult<List<Information>> searchNotes(String user_id, String note_title) {
		note_title = String.format("%%%s%%", note_title);
		List<Information> notes = dao.searchNotes(user_id, note_title);
		NoteResult<List<Information>> result = new NoteResult<List<Information>>();
		if (notes.isEmpty()) {
			result.setStatus(1);
			result.setMsg("模糊查询失败");
		} else {
			result.setStatus(0);
			result.setMsg("模糊查询");
			result.setData(notes);
		}
		return result;
	}

	/**
	 * 加载回收站的笔记
	 * @param user_id
	 * @return
	 */
	@Override
	public NoteResult<List<Information>> loadTrushNote(String user_id){
		NoteResult<List<Information>> result = new NoteResult<List<Information>>();
		List<Information> notes = dao.trash(user_id);
		if(notes.isEmpty()){
			result.setStatus(1);
			result.setMsg("查询记录数目为0");
		}
		else{
			result.setStatus(0);
			result.setMsg("查询成功！");
			result.setData(notes);
		}
		return result;
	}
	@Override
	public NoteResult trashRecover(String note_id) {
		int rows = dao.trashRecover(note_id);
		NoteResult result = new NoteResult();
		if(rows == 1){
			result.setStatus(0);
			result.setMsg("恢复成功");
		}else{
			result.setStatus(1);
			result.setMsg("恢复失败");
		}
		return result;
	}

	@Transactional
	@Override
	public NoteResult deleteNotes(String... ids) {
		NoteResult result = new NoteResult();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("ids",ids);
		int rows = dao.deleteNotes(map);
		if (rows != ids.length){
			result.setStatus(1);
			result.setMsg("删除笔记出现错误！");
			throw new RuntimeException("批量删除笔记出现错误！");
		}else{
			result.setStatus(0);
			result.setMsg("删除笔记成功");
		}
		return result;
	}

	@Transactional
	@Override
	public NoteResult moveNotesToTrush(String... ids) {
		NoteResult result = new NoteResult();
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("updated_at", new Date().getTime());
		map.put("ids", ids);
		int rows = dao.moveNotesToTrush(map);
		if(rows == ids.length){
			result.setStatus(0);
			result.setMsg("回收成功");
		}
		else{
			result.setStatus(1);
			result.setMsg("回收失败");
			throw new RuntimeException("批量回收笔记出现错误！");
		}
		return result;
	}
	@Transactional
	@Override
	public NoteResult trashNotesRecovery(String... ids){
		NoteResult result = new NoteResult();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("ids", ids);
		int rows = dao.trashNotesRecover(map);
		if(rows == ids.length){
			result.setStatus(0);
			result.setMsg("批量恢复成功");
		}
		else{
			result.setStatus(1);
			result.setMsg("批量恢复失败");
			throw new RuntimeException("批量恢复笔记错误!");
		}
		return result;
	}
}
