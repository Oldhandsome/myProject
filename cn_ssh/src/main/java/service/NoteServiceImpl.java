package service;

import dao.NoteDao;
import entity.Information;
import entity.Note;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.StringJoiner;

@Service
public class NoteServiceImpl implements NoteService {

    @Resource(name = "noteDaoImpl")
    private NoteDao noteDao;


    @Override
    @Transactional
    public List<Note> getNotes(String book_id) throws RuntimeException{
        List<Note> notes = noteDao.findByBookId(book_id );
        if(notes == null){
            throw new RuntimeException("笔记本加载异常");
        }
        return notes;
    }

    @Override
    @Transactional
    public String loadContent(String note_id) throws RuntimeException{
        String object = noteDao.loadContent(note_id);
        if(object == null){
            throw  new RuntimeException("笔记内容加载异常");
        }
        return object;
    }

    @Override
    @Transactional
    public String updateNote(Note note) throws RuntimeException{
        if(noteDao.findByNoteId(note.getNote_id()).equals(note)){
            throw new RuntimeException("笔记未发生改变");
        }
        int i = noteDao.updateNote(note);
        if(i != 0){
            throw new RuntimeException("笔记更新异常");
        }
        return "笔记更新成功";
    }

    @Override
    @Transactional
    public String addNote(Note note) throws RuntimeException{
        int i = noteDao.addNote(note);
        if(i != 0){
            throw new RuntimeException("笔记添加异常");
        }
        return "笔记添加成功";
    }

    @Override
    @Transactional
    public String deleteNote(String note_id) throws RuntimeException{
        int i = noteDao.deleteNote(note_id);
        if(i != 0){
            throw new RuntimeException("笔记删除异常");
        }
        return "笔记删除成功";
    }

    @Override
    @Transactional
    public String noteToTrash(String note_id) throws RuntimeException {
        int i = noteDao.moveToTrash(note_id);
        if(i != 0){
            throw new RuntimeException("笔记回收异常");
        }
        return "笔记回收成功";
    }
    @Override
    @Transactional
    public String tnoteRecover(String note_id) throws RuntimeException {
        int i = noteDao.trashNoteRecovery(note_id);
        if(i != 0){
            throw new RuntimeException("笔记恢复异常");
        }
        return "笔记恢复成功";
    }

    @Override
    @Transactional
    public String starNote(String note_id) throws RuntimeException{
        int i =noteDao.starNote(note_id);
        if(i != 0){
            throw new RuntimeException("收藏异常");
        }
        return "收藏成功";
    }

    @Override
    @Transactional
    public String unstarNote(String note_id) throws RuntimeException{
        int i = noteDao.unstarNote(note_id);
        if(i != 0){
            throw new RuntimeException("取消收藏异常");
        }
        return "取消收藏成功";
    }

    @Override
    @Transactional
    public String moveNote(String book_id, String note_id) throws RuntimeException{
        int i = noteDao.moveNote(book_id,note_id);
        if(i != 0){
            throw new RuntimeException("笔记移动失败");
        }
        return "笔记移动成功";
    }

    @Override
    @Transactional
    public List<Information> findNotesByName(String user_id, String note_title) throws RuntimeException{
        List<Information> notes = noteDao.findByName(user_id,String.format("%%%s%%",note_title));
        if(notes == null || notes.isEmpty())
            throw new RuntimeException("模糊查询为空");
        return notes;
    }
    @Override
    @Transactional
    public List<Information> trash(String user_id) throws RuntimeException{
        List<Information> notes = noteDao.trash(user_id);
        if(notes == null || notes.isEmpty()){
            throw new RuntimeException("回收站为空");
        }
        return notes;
    }

    @Override
    @Transactional
    public String notesToTrash(String... ids) throws RuntimeException{
        StringJoiner joiner = new StringJoiner(",");
        for(String id:ids){
            joiner.add(String.format("'%s'",id));
        }
        int i = noteDao.moveNotesToTrash(joiner.toString());
        if (i != ids.length){
            throw new RuntimeException("批量回收异常");
        }
        return "批量回收成功";
    }

    @Override
    @Transactional
    public String notesRecovery(String... ids) throws RuntimeException{
        StringJoiner joiner = new StringJoiner(",");
        for(String id:ids){
            joiner.add(String.format("'%s'",id));
        }
        int i = noteDao.trashRecovery(joiner.toString());
        if(i != ids.length)
            throw new RuntimeException("批量恢复异常");
        return "批量恢复成功";
    }

    @Override
    @Transactional
    public String deleteNotes(String[] ids) {
        StringJoiner joiner = new StringJoiner(",");
        for(String id:ids){
            joiner.add(String.format("'%s'",id));
        }
        int i = noteDao.notesDelete(joiner.toString());
        if(i != ids.length)
            throw new RuntimeException("批量删除异常");
        return "批量删除成功";
    }
}
