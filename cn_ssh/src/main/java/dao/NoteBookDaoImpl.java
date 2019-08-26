package dao;

import entity.NoteBook;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Repository
public class NoteBookDaoImpl implements NoteBookDao {
    @Resource
    HibernateTemplate hibernateTemplate;

    @Override
    public List<NoteBook> findBooksByUserId(String user_id){
        String hql = "from NoteBook where user_id = ?";
        List<NoteBook> books = hibernateTemplate.find(hql,user_id);
        return books;
    }
    @Override
    public int addBook(NoteBook book){
        hibernateTemplate.save(book);
        return 0;
    }
    @Override
    public int updateBook(NoteBook book){
        NoteBook book1 = hibernateTemplate.get(NoteBook.class,book.getNote_book_id());
        book1.setNote_book_name(book.getNote_book_name());
        book1.setExplaination(book.getExplaination());
        book1.setUpdated_at(new Date().getTime());
        return 0;
    }
    @Override
    public int deleteNoteBook(String id){
        String hql = "from NoteBook where note_book_id = ?";
        hibernateTemplate.delete(hql,id);
        return 0;
    }
}
