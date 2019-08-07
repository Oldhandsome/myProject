package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import entity.Note;
import service.NoteService;
import util.NoteResult;

@Controller
@RequestMapping("/note")
public class NoteController {
	@Resource(name="noteServiceImpl")
	private NoteService ns;
	
	@RequestMapping("/loadnotes.do")
	@ResponseBody
	public Map<String, NoteResult<List<Note>>> loadNotes(HttpServletRequest req) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		String strings = req.getParameter("array");
		
		List<String> str =  objectMapper.readValue(strings, new TypeReference<List<String>>(){});
		Map<String, NoteResult<List<Note>>> map = new HashMap<String,NoteResult<List<Note>>>();
		for(int i = 0 ; i < str.size(); i++) {
			NoteResult<List<Note>> result = new NoteResult<List<Note>>();
			result = ns.loadBookNotes(str.get(i), "1");
			map.put(str.get(i),result);
		}
		return map;
	}
	
	@ResponseBody
	@RequestMapping("/loadNoteContent.do")
	public NoteResult<String> loadNoteContent(String note_id) {
		NoteResult<String> result = ns.loadNoteContent(note_id);
		return result;
	}
	@ResponseBody
	@RequestMapping("/updatenote.do")
	public NoteResult<String> updateNote(HttpServletRequest req){
		String note_id = req.getParameter("note_id");
		String note_content = new String(req.getParameter("note_content"));
		NoteResult<String> result = ns.updateNote(note_id, note_content);
		return result;
	}
	
}
