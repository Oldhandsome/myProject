package dao;


import entity.Information;
import entity.Note;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Repository
public class NoteDaoImpl implements NoteDao  {
    @Resource
    HibernateTemplate hibernateTemplate;

    @Override
    public Note findByNoteId(String note_id){
        return hibernateTemplate.get(Note.class,note_id);
    }

    @Override
    public List<Note> findByBookId(String book_id){
        String hql = "from Note where note_book_id = ? and note_status_id in (1,4)";
        List<Note> notes = hibernateTemplate.find(hql,book_id);
        return notes;
    }

    @Override
    public String loadContent(String note_id){
        Note note = hibernateTemplate.get(Note.class,note_id);
        return note.getNote_content();
    }

    @Override
    public int updateNote(Note note){
        Note note1 = hibernateTemplate.get(Note.class,note.getNote_id());
        note1.setNote_title(note.getNote_title());
        note1.setNote_content(note.getNote_content());
        note1.setUpdated_at(new Date().getTime());
        return 0;
    }

    @Override
    public int addNote(Note note){
        hibernateTemplate.save(note);
        return 0;
    }
    @Override
    public int deleteNote(String id){
        Note note = hibernateTemplate.get(Note.class,id);
        note.setNote_status_id("3");
        note.setUpdated_at(new Date().getTime());
        return 0;
    }

    @Override
    public int starNote(String id){
        Note note = hibernateTemplate.get(Note.class,id);
        note.setNote_status_id("4");
        note.setUpdated_at(new Date().getTime());
        return 0;
    }

    @Override
    public int unstarNote(String id){
        Note note = hibernateTemplate.get(Note.class,id);
        note.setNote_status_id("1");
        note.setUpdated_at(new Date().getTime());
        return 0;
    }

    @Override
    public int moveNote(String book_id, String note_id){
        Note note = hibernateTemplate.get(Note.class,note_id);
        note.setNote_book_id(book_id);
        note.setUpdated_at(new Date().getTime());
        return 0;
    }

    @Override
    public int moveToTrash(String note_id){
        Note note = hibernateTemplate.get(Note.class,note_id);
        note.setNote_status_id("2");
        return 0;
    }
    //绕过hibernate，直接操作jdbc
    @Override
    public List<Information> findByName(String user_id, String note_title){
        String sql = "select note_id as id, note_title as name , note_type_id as type, note_status_id as status , " +
                        "note.created_at as created_at, note.updated_at as updated_at, note_book_name as location " +
                        "from note inner join note_book " +
                        "on note.note_book_id = note_book.note_book_id " +
                        "where note.user_id = '%s' and note.note_title like '%s' " +
                        "ORDER BY note_title DESC";
        Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
        List<Information> infos = session.createSQLQuery(String.format(sql,user_id,note_title)).addEntity(Information.class).list();
        return infos;
    }

    @Override
    public List<Information> trash(String user_id){
        String sql = "select note_id as id ,note_title as name ,note_type_id as type ,note_status_id as status," +
                        "note.created_at as created_at ,note.updated_at as updated_at ,note_book_name  as location " +
                        "from note inner join note_book " +
                        "on note.note_book_id = note_book.note_book_id " +
                        "where note.user_id = '%s' and note_status_id = '2' order by note_title DESC";
        Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
        List<Information> infos = session.createSQLQuery(String.format(sql,user_id)).addEntity(Information.class).list();
        return infos;
    }

    @Override
    public int moveNotesToTrash(String ids){
        String sql = "update note set note_status_id = '2' , updated_at = unix_timestamp() " +
                        "where note_id in (%s)";
        Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
        return session.createSQLQuery(String.format(sql, ids)).executeUpdate();
    }

    @Override
    public int trashRecovery(String ids){
        String sql = "update note set note_status_id = '1' , updated_at = unix_timestamp() " +
                "where note_id in (%s)";
        Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
        return session.createSQLQuery(String.format(sql,ids)).executeUpdate();
    }
}
