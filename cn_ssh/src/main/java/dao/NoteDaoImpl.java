package dao;


import entity.Information;
import entity.Note;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Repository
public class NoteDaoImpl implements NoteDao {
    @Resource
    HibernateTemplate hibernateTemplate;

    /**
     * 根据bookid返回笔记本中的笔记id
     * @param book_id
     * @return notes
     */
    @Override
    @Transactional
    public List<Note> findByUserId(String book_id){
        String hql = "from Note where note_book_id = ? and note_status_id in (1,4)";
        List<Note> notes = hibernateTemplate.find(hql,book_id);
        return notes;
    }

    @Override
    @Transactional
    public String loadContent(String note_id){
        Note note = hibernateTemplate.get(Note.class,note_id);
        return note.getNote_content();
    }

    @Override
    @Transactional
    public int updateNote(Note note){
        Note note1 = hibernateTemplate.get(Note.class,note.getNote_id());
        note1.setNote_title(note.getNote_title());
        note1.setNote_content(note.getNote_content());
        note1.setUpdated_at(new Date().getTime());
        return 0;
    }

    @Override
    @Transactional
    public int addNote(Note note){
        hibernateTemplate.save(note);
        return 0;
    }
    @Override
    @Transactional
    public int deleteNote(String id){
        Note note = hibernateTemplate.get(Note.class,id);
        note.setNote_status_id("3");
        note.setUpdated_at(new Date().getTime());
        return 0;
    }

    @Override
    @Transactional
    public int starNote(String id){
        Note note = hibernateTemplate.get(Note.class,id);
        note.setNote_status_id("4");
        note.setUpdated_at(new Date().getTime());
        return 0;
    }

    @Override
    @Transactional
    public int unstarNote(String id){
        Note note = hibernateTemplate.get(Note.class,id);
        note.setNote_status_id("1");
        note.setUpdated_at(new Date().getTime());
        return 0;
    }

    @Override
    @Transactional
    public int moveNote(String book_id, String note_id){
        Note note = hibernateTemplate.get(Note.class,note_id);
        note.setNote_book_id(book_id);
        note.setUpdated_at(new Date().getTime());
        return 0;
    }

    @Override
    @Transactional
    public int moveToTrash(String note_id){
        Note note = hibernateTemplate.get(Note.class,note_id);
        note.setNote_status_id("2");
        return 0;
    }

    @Override
    @Transactional
    public List<Information> findByName(String user_id, String note_title){
        String hql = "select new Information(note_id,note_title,note_type_id,note_status_id" +
                "Note.created_at,Note.updated_at,note_book_name ) " +
                "from Note inner join NoteBook where Note.note_book_id = NoteBook.note_Book_id" +
                "where Note.user_id = ? and Note.note_title like %?% order by note_title DESC";
        List<Information> infos= hibernateTemplate.find(hql,user_id,note_title);
        return infos;
    }

    @Override
    @Transactional
    public List<Information> trash(String user_id){
        String hql = "select new Information(note_id,note_title,note_type_id,note_status_id" +
                "Note.created_at,Note.updated_at,note_book_name ) " +
                "from Note inner join NoteBook where Note.note_book_id = NoteBook.note_Book_id" +
                "where Note.user_id = ? and note_status_id = 2 order by note_title DESC";
        List<Information> infos = hibernateTemplate.find(hql,user_id);
        return infos;
    }

}
