package controller;

import entity.Information;
import entity.Note;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import service.NoteService;
import util.NoteUtil;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Scope("prototype")
public class NoteAction extends JsonAction {
    @Resource(name = "noteServiceImpl")
    private NoteService noteService;

    private String array;
    public void setArray(String array) {
        this.array = array;
    }
    public String getArray() {
        return array;
    }

    private String user_id;
    private String note_id;
    private String note_title;
    private String note_book_id;
    private String note_type_id;
    private String note_content;
    public String getUser_id() {
        return user_id;
    }
    public String getNote_id() {
        return note_id;
    }
    public String getNote_title() {
        return note_title;
    }
    public String getNote_book_id() {
        return note_book_id;
    }
    public String getNote_type_id() {
        return note_type_id;
    }
    public String getNote_content() {
        return note_content;
    }
    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
    public void setNote_id(String note_id) {
        this.note_id = note_id;
    }
    public void setNote_title(String note_title) {
        this.note_title = note_title;
    }
    public void setNote_book_id(String note_book_id) {
        this.note_book_id = note_book_id;
    }
    public void setNote_type_id(String note_type_id) {
        this.note_type_id = note_type_id;
    }
    public void setNote_content(String note_content) {
        this.note_content = note_content;
    }

    public String loadNotes(){
        List<String> book_ids = com.alibaba.fastjson.JSON.parseArray(array, String.class);
        Map<String,List<Note>> map = new HashMap<String,List<Note>>();
        for(String id :book_ids) {
            List<Note> notes =  noteService.getNotes(id);
            map.put(id,notes);
        }
        setResult(map);
        return JSON;
    }
    public String toAddNote(){
        return SUCCESS;
    }

    public String addNote(){
        Note note = new Note();
        note.setUser_id(user_id);
        note.setNote_id(NoteUtil.createId());
        note.setNote_status_id("1");
        note.setNote_title(note_title);
        //下面的暂时用1
        note.setNote_type_id("1");
        note.setCreated_at(new Date().getTime());
        note.setUpdated_at(new Date().getTime());
        note.setNote_book_id(note_book_id);
        note.setNote_content(note_content);
        noteService.addNote(note);
        setResult(note);
        return JSON;
    }

    public String getContent(){
        String noteContent = noteService.loadContent(note_id);
        setResult(noteContent);
        return JSON;
    }

    public String updateNote(){
        Note note = new Note();
        note.setNote_id(note_id);
        note.setNote_title(note_title);
        note.setNote_content(note_content);
        String msg = noteService.updateNote(note);
        setResult(msg,0);
        return JSON;
    }
    public String toMoveNote(){
        return SUCCESS;
    }
    public String moveNote(){
        String msg = noteService.moveNote(note_book_id,note_id);
        setResult(msg,0);
        return JSON;
    }

    public String starNote(){
        String msg = noteService.starNote(note_id);
        setResult(msg,0);
        return JSON;
    }
    public String unStarNote(){
        String msg = noteService.unstarNote(note_id);
        setResult(msg,0);
        return JSON;
    }

    public String noteToTrash(){
        System.out.println(note_id);
        String msg = noteService.noteToTrash(note_id);
        setResult(msg,0);
        return JSON;
    }

    public String deleteNote(){
        String msg = noteService.deleteNote(note_id);
        setResult(msg,0);
        return JSON;
    }

    public String list(){
        return SUCCESS;
    }

    public String titleSearch(){
        List<Information> infos = noteService.findNotesByName(user_id,note_title);
        setResult(infos);
        return JSON;
    }

    public String notesToTrash(){
        List<String> note_ids = com.alibaba.fastjson.JSON.parseArray(array, String.class);
        String[] ids = new String[note_ids.size()];
        note_ids.toArray(ids);
        String msg = noteService.notesToTrash(ids);
        setResult(msg,0);
        return JSON;
    }

    public String loadTrash(){
        List<Information> infos = noteService.trash(user_id);
        setResult(infos);
        return JSON;
    }

    public String trashRecovery(){
        String msg= noteService.tnoteRecover(note_id);
        setResult(msg,0);
        return JSON;
    }

    public String trashnotesRecovery(){
        List<String> note_ids = com.alibaba.fastjson.JSON.parseArray(array, String.class);
        String[] ids = new String[note_ids.size()];
        note_ids.toArray(ids);
        String msg = noteService.notesRecovery(ids);
        setResult(msg,0);
        return JSON;
    }

    public String deleteNotes(){
        List<String> note_ids = com.alibaba.fastjson.JSON.parseArray(array, String.class);
        String[] ids = new String[note_ids.size()];
        note_ids.toArray(ids);
        String msg = noteService.deleteNotes(ids);
        setResult(msg,0);
        return JSON;
    }
}
