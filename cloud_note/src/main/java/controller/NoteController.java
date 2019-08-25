package controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import entity.Information;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import entity.Note;
import service.NoteService;
import util.NoteResult;
import util.NoteUtil;

@Controller
@RequestMapping("/note")
public class NoteController {
	@Resource(name="noteServiceImpl")
	private NoteService ns;
	
	/*
	 * 根据笔记的id数组  返回笔记列表
	 */
	@RequestMapping("/loadnotes.do")
	@ResponseBody
	public Map<String, NoteResult<List<Note>>> loadNotes(HttpServletRequest req) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		String strings = req.getParameter("array");
		
		List<String> str =  objectMapper.readValue(strings, new TypeReference<List<String>>(){});
		Map<String, NoteResult<List<Note>>> map = new HashMap<String,NoteResult<List<Note>>>();
		for(int i = 0 ; i < str.size(); i++) {
			NoteResult<List<Note>> result = new NoteResult<List<Note>>();
			result = ns.loadBookNotes(str.get(i));
			map.put(str.get(i),result);
		}
		return map;
	}
	/*
	 * 根据笔记的id 加载笔记的内容
	 */
	@ResponseBody
	@RequestMapping("/loadNoteContent.do")
	public NoteResult<String> loadNoteContent(String note_id) {
		NoteResult<String> result = ns.loadNoteContent(note_id);
		return result;
	}
	/*
	 * 更新笔记（包含笔记的内容，笔记的标题）
	 */
	@ResponseBody
	@RequestMapping("/updatenote.do")
	public NoteResult<String> updateNote(HttpServletRequest req){
		String note_id = req.getParameter("note_id");
		String note_title = req.getParameter("note_title");
		String note_content = new String(req.getParameter("note_content"));
		NoteResult<String> result = ns.updateNote(note_id, note_title,note_content);
		return result;
	}

	/*
	 * 返回添加笔记视图
	 */
	@RequestMapping("/toaddnote.do")
	public String toaddnote(){
		return "toAddNote";
	}
	/*
	 * 添加笔记
	 */
	@ResponseBody
	@RequestMapping("/addnote.do")
	public NoteResult<Note> addNote(HttpServletRequest req){
		NoteResult<Note> result = new NoteResult<Note>();
		Note note = new Note();
		note.setUser_id(req.getParameter("user_id"));
		note.setNote_book_id(req.getParameter("note_book_id"));
		note.setNote_status_id("1");
//		现在还没有note_type_id
//		req.getParameter("note_type_id")
		note.setNote_type_id(null);
		note.setNote_title(req.getParameter("note_title"));
		note.setNote_content(req.getParameter("note_content"));
		note.setNote_id(NoteUtil.createId());
		note.setCreated_at(new Date().getTime());
		result = ns.addNote(note);
//		System.out.println(result);
		return result;
	}

	@ResponseBody
	@RequestMapping("/deletenote.do")
	public NoteResult deleteNote(String note_id){
		NoteResult result = new NoteResult();
		result =  ns.deleteNote(note_id);
		return result;
	}
	@ResponseBody
	@RequestMapping("/deletenotes.do")
	public NoteResult deleteNotes(String ids){
		String[] note_id = ids.split(",");
		NoteResult result = ns.deleteNotes(ids);
		return result;
	}
	/*
	 * 收藏笔记
	 */
	@ResponseBody
    @RequestMapping("/starnote.do")
    public NoteResult starNote(String note_id){
	    NoteResult result = new NoteResult();
	    result =  ns.starNote(note_id);
	    return result;
    }
	/*
	 * 取消收藏笔记
	 */
    @ResponseBody
    @RequestMapping("/unstarnote.do")
    public NoteResult unstarNote(String note_id){
	    NoteResult result = new NoteResult();
	    result = ns.unstartNote(note_id);
	    return result;
    }
    /*
     * 返回 移动笔记的视图名
     */
    @RequestMapping("/tomovenote.do")
    public String tomovenote(){
	    return "toMoveNote";
    }
    
    /*
     * 移动笔记至另一个笔记本
     */
    @ResponseBody
    @RequestMapping("/movenote.do")
    public NoteResult moveNote(String note_id,String note_book_id){
	    NoteResult<Note> result = ns.moveNote(note_id,note_book_id);
	    return result;
    }
    
    /*
     * 移动至回收站
     */
    @ResponseBody
    @RequestMapping("/movetotrash.do")
    public NoteResult moveToTrush(String note_id){
    	NoteResult result = ns.moveToTrush(note_id);
    	return result;
    }
    @ResponseBody
	@RequestMapping("/movenotestotrash.do")
	public NoteResult moveNotesToTrush(String ids){
		String[] note_id = ids.split(",");
    	NoteResult result = ns.moveNotesToTrush(note_id);
    	return result;
	}

    /*
     * 加载模糊匹配的笔记记录
     */
    @ResponseBody
    @RequestMapping("/searchnotes.do")
    public NoteResult<List<Information>> searchNotes(String user_id, String note_title){
    	NoteResult<List<Information>> result = ns.searchNotes(user_id,note_title);
    	return result;
    }
    /*
     * 加载回收站的所有记录
     */
    @ResponseBody
    @RequestMapping("/loadtrash.do")
    public NoteResult<List<Information>> loadTrash(String user_id){
    	NoteResult<List<Information>> result = ns.loadTrushNote(user_id);
    	return result;
    }
	@ResponseBody
	@RequestMapping("/trashrecover.do")
	public NoteResult trashRecover(String note_id){
		NoteResult result = ns.trashRecover(note_id);
		return result;
	}
	@ResponseBody
	@RequestMapping("/trashNotesRecover.do")
	public NoteResult trashNotesRecover(String ids){
    	String[] note_id = ids.split(",");
		NoteResult result = ns.trashNotesRecovery(note_id);
		return result;
	}
    /*
     * return the view of the url
     */
    @RequestMapping("/list.do")
    public String loadNoteList(){
    	return "list";
    }
}
