package test;

import org.junit.Before;
import org.junit.Test;
import service.NoteService;
import util.NoteResult;

public class NoteServiceTest extends ParentClass{
    NoteService ns ;
    @Before
    public void init(){
        ns = getApplicationContext().getBean("noteServiceImpl",NoteService.class);
    }
    @Test
    public void test1(){
        NoteResult result = ns.deleteNotes(new String[]{"072383e4-54fd-4cad-bdad-c87632996be4",
                "08837535-68c5-4998-af01-773d8bc4faa0",
                "id1"});
        System.out.println(result);
    }
    @Test
    public void test2(){
        NoteResult result = ns.moveNotesToTrush(new String[]{"072383e4-54fd-4cad-bdad-c87632996be4",
                "08837535-68c5-4998-af01-773d8bc4faa0",
                "0a0d4503-7b77-48a5-b845-5c9f6fba82cf"});
        System.out.println(result);
    }
    @Test
    public void test3(){
        NoteResult result = ns.trashNotesRecovery(new String[]{"072383e4-54fd-4cad-bdad-c87632996be4",
                "08837535-68c5-4998-af01-773d8bc4faa0",
                "0a0d4503-7b77-48a5-b845-5c9f6fba82cf"});
        System.out.println(result);
    }
}
