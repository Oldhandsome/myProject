package test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import entity.Note;
import entity.NoteBook;
import org.junit.Before;
import org.junit.Test;
import service.NoteBookService;
import util.NoteUtil;

import java.util.Date;
import java.util.List;

public class NoteBookServiceTest extends ParentTest{
    private NoteBookService noteBookService;

    @Before
    public void init(){
        noteBookService = getApplicationContext().getBean("noteBookServiceImpl",NoteBookService.class);
    }

    @Test
    public void testFindBookByUserId(){
        List<NoteBook> noteBooks = noteBookService.loadUserNoteBook("79519");
        for(NoteBook noteBook:noteBooks)
            System.out.println(noteBook);
    }

    @Test
    public void testAddBook(){
        NoteBook noteBook = new NoteBook();
        noteBook.setUser_id("79519");
        noteBook.setNote_book_type_id("0");
        noteBook.setNote_book_name("xixihaha");
        noteBook.setNote_book_id(NoteUtil.createId());
        noteBook.setExplaination("xixihaha");
        noteBook.setCreated_at(new Date().getTime());
        System.out.println(noteBookService.addNoteBook(noteBook));
    }

    @Test
    public void testUpdateNoteBook(){
        NoteBook noteBook = new NoteBook();
        noteBook.setUser_id("79519");
        noteBook.setNote_book_type_id("0");
        noteBook.setNote_book_name("wahaha");
        noteBook.setNote_book_id("403fa50b-d8f5-4b41-aaea-4abded8382e2");
        noteBook.setExplaination("wahaha");
        noteBook.setUpdated_at(new Date().getTime());
        System.out.println(noteBookService.updateNoteBook(noteBook));
    }
    @Test
    public void testJson(){
        String[] strs = {"abcd","1234","efgh"};
        System.out.println(strs);
        String str = JSONArray.toJSONString(strs);
        System.out.println(str);
        List<String> obj = JSON.parseArray(str, String.class);
        System.out.println(obj);
        Object obj1 = JSONArray.parse(str);
        System.out.println(obj1);

    }
}
