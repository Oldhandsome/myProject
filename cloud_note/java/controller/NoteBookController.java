package controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import entity.NoteBook;
import service.NoteBookService;
import util.NoteResult;

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
	
	@RequestMapping("/addnotebook.do")
	public NoteResult<NoteBook> addNoteBook(HttpServletRequest req){
		NoteResult<NoteBook> result = new NoteResult<NoteBook>();
		String user_id = req.getParameter("user_id");
		String note_book_name = req.getParameter("note_book_name");
		String note_type_id = req.getParameter("note_type_id");
		String explaination = req.getParameter("explaination");
		
		
		return result;
	}
}
