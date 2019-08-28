package test;

import entity.Information;
import entity.Note;
import org.junit.Before;
import org.junit.Test;
import service.NoteService;
import util.NoteUtil;

import java.util.Date;
import java.util.List;

public class NoteServiceTest extends ParentTest {
    private NoteService noteService;

    @Before
    public void init(){
        noteService = getApplicationContext().getBean("noteServiceImpl",NoteService.class);
    }

    @Test
    public void testGetNotes(){
        String book_id = "noteService.getNotes(book_id)";
        List<Note> notes = noteService.getNotes(book_id);
        for(Note note:notes){
            System.out.println(notes);
        }
    }
    @Test
    public void testLoadNoteContent(){
        String note_id = "08837535-68c5-4998-af01-773d8bc4faa0";
        System.out.println(noteService.loadContent(note_id).getClass());
    }

    @Test
    public void testUpdateNote(){
        Note note = new Note();
        note.setNote_id("072383e4-54fd-4cad-bdad-c87632996be4");
        note.setNote_title("xixihaha");
        note.setNote_content("xixihaha");
        System.out.println(noteService.updateNote(note));
    }
    @Test
    public void testAddNote(){
        Note note = new Note();
        note.setNote_id(NoteUtil.createId());
        note.setNote_title("xixihaha");
        note.setNote_content("xixihaha");
        note.setUser_id("79519");
        note.setNote_book_id("4162484d-70cc-4eea-a2f5-63b1fbc67624");
        note.setCreated_at(new Date().getTime());
        note.setNote_type_id("1");
        note.setNote_status_id("1");
        System.out.println(noteService.addNote(note));;
    }
    @Test
    public void testdeleteNote(){
        String note_id = "f2eee146-536f-4247-9bb3-468c08238172";
        System.out.println(noteService.deleteNote(note_id));
    }
    @Test
    public void testStarNote(){
        String note_id = "f2eee146-536f-4247-9bb3-468c08238172";
        System.out.println(noteService.starNote(note_id));
    }

    @Test
    public void testUnStartNote(){
        String note_id = "f2eee146-536f-4247-9bb3-468c08238172";
        System.out.println(noteService.unstarNote(note_id));
    }
    @Test
    public void testMoveNote(){
        String note_id = "f2eee146-536f-4247-9bb3-468c08238172";
        String book_id = "7c67fc47-ba75-4271-b56f-5f9999079795";
        System.out.println(noteService.moveNote(book_id, note_id));
    }

    @Test
    public void testFindByName(){
        String user_id = "79519";
        String note_title = "笔记";
        List<Information> infos = noteService.findNotesByName(user_id, note_title);
        for(Information info:infos)
            System.out.println(info);
    }

    @Test
    public void testTrash(){
        String user_id ="79519";
        List<Information> infos = noteService.trash(user_id);
        for(Information info : infos)
            System.out.println(info);
    }

    @Test
    public void testNotesToTrash(){
        String[] notes = {"f2eee146-536f-4247-9bb3-468c08238172",
                            "e786f144-6df8-4a5f-a03b-3487458c43c0",
                            "e39cddd3-c9d1-41a7-9f77-eeb5ba0a8ad7",
                            "e1f139eb-3378-481f-bd5c-9e57404bd272",
                            "d9d945a0-b0bf-4ad8-b95c-8abc0b08cff7"};
        System.out.println(noteService.notesToTrash(notes));
    }

    @Test
    public void testTrashRecovery(){
        String[] notes = {"f2eee146-536f-4247-9bb3-468c08238172",
                "e786f144-6df8-4a5f-a03b-3487458c43c0",
                "e39cddd3-c9d1-41a7-9f77-eeb5ba0a8ad7",
                "e1f139eb-3378-481f-bd5c-9e57404bd272",
                "d9d945a0-b0bf-4ad8-b95c-8abc0b08cff7"};
        System.out.println(noteService.notesRecovery(notes));
    }
}
