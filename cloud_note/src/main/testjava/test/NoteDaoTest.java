package test;

import dao.NoteDao;
import org.junit.Test;

import java.util.Date;

public class NoteDaoTest extends ParentClass {
    private NoteDao dao;
    public NoteDaoTest(){
        super();
        dao = super.ac.getBean("noteDaoImpl",NoteDao.class);
    }
    @Test
    public void test(){
        String note_id = "e786f144-6df8-4a5f-a03b-3487458c43c0";
        String note_book_id = "7c67fc47-ba75-4271-b56f-5f9999079795";
        dao.moveNote(note_id,note_book_id,new Date().getTime());
    }
}
