package test;

import dao.NoteBookDao;
import entity.NoteBook;
import org.junit.Before;
import org.junit.Test;
import util.NoteUtil;

import java.util.Date;
import java.util.List;

public class NoteBookDaoTest extends ParentTest {
    private NoteBookDao noteBookDao;
    @Before
    public void init(){
        noteBookDao = getApplicationContext().getBean("noteBookDaoImpl",NoteBookDao.class);
    }
    @Test
    public void testAddNoteBook(){
        NoteBook book = new NoteBook();
        book.setNote_book_id(NoteUtil.createId());
        book.setNote_book_name("wahahah");
        book.setNote_book_type_id("0");
        book.setUser_id("79519");
        book.setCreated_at(new Date().getTime());
        book.setExplaination("wahahaha");
        System.out.println(noteBookDao.addBook(book));;
    }
    @Test
    public void testFindBooksByUserId(){
        String user_id = "79519";
        List<NoteBook> books = noteBookDao.findBooksByUserId(user_id);
        for(NoteBook book:books){
            System.out.println(book);
        }
    }

    @Test
    public void testUpdateBook(){
        NoteBook book = new NoteBook();
        book.setNote_book_id("22439a7d-c0e1-4ed1-8939-a7ece4795bf5");
        book.setUser_id("79519");
        book.setExplaination("你知道我在想你么");
        System.out.println(noteBookDao.updateBook(book));;
    }

  /*  @Test
    public void testDeleteBook(){
        String id = "22439a7d-c0e1-4ed1-8939-a7ece4795bf5";
        System.out.println(noteBookDao.deleteNoteBook(id));
    }*/
}
