package service;

import dao.NoteBookDao;
import entity.NoteBook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class NoteBookServiceImpl implements NoteBookService {
    @Resource(name = "noteBookDaoImpl")
    private NoteBookDao noteBookDao;

    @Override
    @Transactional
    public List<NoteBook> loadUserNoteBook(String user_id) throws RuntimeException{
        List<NoteBook> noteBooks =  noteBookDao.findBooksByUserId(user_id);
        if(noteBooks == null){
            throw new RuntimeException("笔记本加载异常");
        }
        else if(noteBooks.isEmpty())
            throw new RuntimeException("笔记本为空");
        return noteBooks;
    }

    @Override
    @Transactional
    public NoteBook addNoteBook(NoteBook noteBook) throws RuntimeException{
        int index = noteBookDao.addBook(noteBook);
        if(index != 0){
            throw new RuntimeException("添加笔记本异常");
        }
        return noteBook;
    }
    @Override
    @Transactional
    public NoteBook updateNoteBook(NoteBook noteBook) throws RuntimeException{
        int index = noteBookDao.updateBook(noteBook);
        if(index != 0){
            throw new RuntimeException("更新笔记本异常");
        }
        return noteBook;
    }
}
