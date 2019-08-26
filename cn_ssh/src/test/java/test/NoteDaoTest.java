package test;

import dao.NoteDao;
import entity.Note;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.List;

public class NoteDaoTest extends ParentTest {
    private NoteDao noteDao;
    @Before
    public void init(){
        noteDao = getApplicationContext().getBean("noteDaoImpl",NoteDao.class);
    }
    @Test
    public void testFindByUserId(){
        List<Note> notes = noteDao.findByUserId("6419a47a-c980-4531-b538-3f8ade974f7c");
        for(Note note:notes){
            System.out.println(note);
        }
    }

    @Test
    public void testLoadNoteContent(){
        System.out.println(noteDao.loadContent("a23ee999-fa0e-4b30-ac07-55389c55aa26"));;
    }

    @Test
    public void testUpdateNote(){
        Note note = new Note();
        note.setNote_id("a23ee999-fa0e-4b30-ac07-55389c55aa26");
        note.setNote_content("嘻嘻哈哈");
        System.out.println(noteDao.updateNote(note));
    }

    @Test
    public void testAddNote(){
        Note note = new Note();
        note.setNote_id("1");
        note.setNote_content("singing is too hard to me");
        note.setNote_book_id("0881d652-e751-40db-9894-6ac42a68f80d");
        note.setCreated_at(new Date().getTime());
        note.setUser_id("86063");
        note.setNote_type_id("1");
        note.setNote_title("sing note 1");
        note.setNote_status_id("1");
        System.out.println(noteDao.addNote(note));
    }

    @Test
    public void testDeleteNote(){
        System.out.println(noteDao.deleteNote("1"));;
    }

    @Test
    public void testStarNote(){
        System.out.println(noteDao.starNote("1"));
    }

    @Test
    public void testUnStarNote(){
        System.out.println(noteDao.unstarNote("1"));
    }
}
