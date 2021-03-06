package service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import dao.NoteBookDao;
import entity.NoteBook;
import util.NoteResult;

@Service("noteBookServiceImpl")
public class NoteBookServiceImpl implements NoteBookService{
	@Resource(name = "noteBookDaoImpl")
	private NoteBookDao dao;

	@Override
	public NoteResult<List<NoteBook>> loadUserNoteBooks(String user_id) {
		List<NoteBook> books = dao.findByUserId(user_id);
		NoteResult<List<NoteBook>> 	result = new NoteResult<List<NoteBook>>();
		if(books == null) {
			result.setStatus(2);
			result.setMsg("查询笔记本失败");
			return result;
		}
		if(books.isEmpty()) {
			result.setStatus(1);
			result.setMsg("笔记本查询完成，但笔记本数目为0！");
			return result;
		}
		result.setStatus(0);
		result.setMsg("笔记本查询完成");
		result.setData(books);
		return result;
	}
	@Override
	public NoteResult<NoteBook> addNoteBook(NoteBook book) {
		NoteResult<NoteBook> result = new NoteResult<NoteBook>();
		int rows = dao.addNoteBook(book);
//		int rows =1;
		if (rows == 1){
			result.setStatus(0);
			result.setMsg("笔记本添加成功");
			result.setData(book);
		}else{
			result.setStatus(1);
			result.setMsg("笔记本添加错误");
		}
		return result;
	}
	
}
