package controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import entity.NoteBook;
import service.NoteBookService;
import util.NoteResult;
import util.NoteUtil;

@Controller
@RequestMapping("/book")
public class NoteBookController {
	@Resource(name="noteBookServiceImpl")
	private NoteBookService nbs;
	
	@ResponseBody
	@RequestMapping("/loadbooks.do")
	public NoteResult<List<NoteBook>> loadBooks(String user_id,HttpServletRequest req) {
		NoteResult<List<NoteBook>> result = nbs.loadUserNoteBooks(user_id);
		return result;
	}
	@RequestMapping("/toaddnotebook.do")
	public String toAddNoteBook(){
		return "toAddNoteBook";
	}
	@ResponseBody
	@RequestMapping("/addnotebook.do")
	public NoteResult<String> addNoteBook(HttpServletRequest req){
		NoteResult<String> result = new NoteResult<String>();
		NoteBook noteBook = new NoteBook();
		noteBook.setNote_book_id(NoteUtil.createId());
		noteBook.setNote_book_name(req.getParameter("notebook_name"));
		noteBook.setNote_book_type_id(req.getParameter("note_type_id"));
		noteBook.setUser_id(req.getParameter("user_id"));
		noteBook.setCreated_at(new Date().getTime());
		noteBook.setExplaination(req.getParameter("explaination"));
		
		System.out.println(noteBook);
		result = nbs.addNoteBook(noteBook);
		return result;
	}
}
