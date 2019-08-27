package test;

import dao.NoteDao;
import entity.Information;
import entity.Note;
import org.hibernate.Session;
import org.junit.Before;
import org.junit.Test;
import org.springframework.orm.hibernate3.HibernateTemplate;

import java.util.Date;
import java.util.List;
import java.util.StringJoiner;

public class NoteDaoTest extends ParentTest {
    private NoteDao noteDao;
    private HibernateTemplate hibernateTemplate;
    @Before
    public void init(){
        noteDao = getApplicationContext().getBean("noteDaoImpl",NoteDao.class);
        hibernateTemplate = getApplicationContext().getBean("hibernateTemplate",HibernateTemplate.class);
    }
    @Test
    public void testFindByBookId(){
        List<Note> notes = noteDao.findByBookId("6419a47a-c980-4531-b538-3f8ade974f7c");
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

    @Test
    public void testMoveNote(){
        System.out.println(noteDao.moveNote("7c67fc47-ba75-4271-b56f-5f9999079795", "e786f144-6df8-4a5f-a03b-3487458c43c0"));
    }

    @Test
    public void testMoveToTrash(){
        System.out.println(noteDao.moveToTrash("e786f144-6df8-4a5f-a03b-3487458c43c0"));
    }

    @Test
    public void testFindByNameSQL(){
        String sql = "select note_id as id, note_title as name , note_type_id as type, note_status_id as status , " +
                "note.created_at as created_at, note.updated_at as updated_at, note_book_name as location " +
                "from note inner join note_book " +
                "on note.note_book_id = note_book.note_book_id " +
                "where note.user_id = '%s' and note.note_title like '%s' " +
                "ORDER BY note_title DESC";

        Session session = hibernateTemplate.getSessionFactory().openSession();
        List<Information> infos = session.createSQLQuery(String.format(sql, "79519", "%笔记%")).addEntity(Information.class).list();
        for(Information info :infos)
            System.out.println(info);
    }
    @Test
    public void testFindByNameHQL(){
        List list = noteDao.findByName("79519", "%笔记%");
        for(Object obj:list)
            System.out.println(obj);;
    }
    @Test
    public void testSql(){
        String sql = "select note_id , note_title , note_status_id , note_type_id ," +
                "note.created_at , note.updated_at , note_book_name " +
                "from note inner join note_book " +
                "on note.note_book_id = note_book.note_book_id " +
                "where note.user_id = '%s' and note.note_title = '%s' " +
                "ORDER BY note_title DESC";
        System.out.println(String.format(sql,"79519","%笔记%"));
    }

    @Test
    public void  testFindByName(){
        List<Information> infos = noteDao.findByName("79519","%笔记%");
        for(Information info : infos){
            System.out.println(info);
        }
    }

    @Test
    public void testTrash(){
        List<Information> infos = noteDao.trash("79519");
        for(Information info : infos){
            System.out.println(info);
        }
    }

    @Test
    public void testMoveNotesToTrash(){
        String[] ids = {"08837535-68c5-4998-af01-773d8bc4faa0",
                "357eb29d-9edf-4d6e-b791-08e75ed67a48",
                "895a3b55-e4ed-4c94-a78f-7b15ac3c37b3",
                "ce9633f8-5f02-4cab-a28d-b92e4188df9f",
                "e39cddd3-c9d1-41a7-9f77-eeb5ba0a8ad7"};
        StringJoiner joiner = new StringJoiner(",");
        for(String id:ids){
            joiner.add(String.format("'%s'",id));
        }
        String str = joiner.toString();
        System.out.println(noteDao.moveNotesToTrash(str));
    }

    @Test
    public void testTrashRecovery(){
        String[] ids = {"08837535-68c5-4998-af01-773d8bc4faa0",
                "357eb29d-9edf-4d6e-b791-08e75ed67a48",
                "895a3b55-e4ed-4c94-a78f-7b15ac3c37b3",
                "ce9633f8-5f02-4cab-a28d-b92e4188df9f",
                "e39cddd3-c9d1-41a7-9f77-eeb5ba0a8ad7"};
        StringJoiner joiner = new StringJoiner(",");
        for(String id:ids){
            joiner.add(String.format("'%s'",id));
        }
        String str = joiner.toString();
        System.out.println(noteDao.trashRecovery(str));
    }
}
