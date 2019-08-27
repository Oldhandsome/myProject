package test;

import entity.Note;
import org.junit.Before;
import org.junit.Test;
import service.NoteService;

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

}
