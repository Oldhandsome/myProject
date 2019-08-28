package controller;

import entity.NoteBook;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import service.NoteBookService;
import util.NoteUtil;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Controller
@Scope("prototype")
public class BookAction extends JsonAction{

    private String user_id;
    private String notebook_name;
    private String note_type_id;
    private String explaination;

    public String getExplaination() {
        return explaination;
    }

    public String getNote_type_id() {
        return note_type_id;
    }

    public String getNotebook_name() {
        return notebook_name;
    }

    public void setExplaination(String explaination) {
        this.explaination = explaination;
    }

    public void setNote_type_id(String note_type_id) {
        this.note_type_id = note_type_id;
    }

    public void setNotebook_name(String notebook_name) {
        this.notebook_name = notebook_name;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
    public String getUser_id() {
        return user_id;
    }
    @Resource(name = "noteBookServiceImpl")
    private NoteBookService noteBookService;

    public String loadBooksByUserId(){
        System.out.println(user_id);
        List<NoteBook> books = noteBookService.loadUserNoteBook(user_id);
        setResult(books);
        return JSON;
    }

    public String toAddBooks(){
        return SUCCESS;
    }

    public String addBook(){
        NoteBook noteBook = new NoteBook();
        noteBook.setNote_book_id(NoteUtil.createId());
        noteBook.setNote_book_name(notebook_name);
        noteBook.setNote_book_type_id(note_type_id);
        noteBook.setUser_id(user_id);
        noteBook.setCreated_at(new Date().getTime());
        noteBook.setExplaination(explaination);
        NoteBook noteBook1 = noteBookService.addNoteBook(noteBook);
        setResult(noteBook1);
        return JSON;
    }


}
