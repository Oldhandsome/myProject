package test;

import dao.NoteDao;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class NoteDaoTest extends ParentClass {
    private NoteDao dao;
    @Before
    public void init(){
        dao = getApplicationContext().getBean("noteDaoImpl", NoteDao.class);
    }
    @Test
    public void test(){
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("ids", new String[]{"072383e4-54fd-4cad-bdad-c87632996be4",
                                    "08837535-68c5-4998-af01-773d8bc4faa0",
                                    "0a0d4503-7b77-48a5-b845-5c9f6fba82cf"});
        int rows = dao.deleteNotes(map);
        System.out.println(rows);
    }
    @Test
    public void test2(){
        Map<String , Object> map = new HashMap<String,Object>();
        map.put("updated_at", new Date().getTime());
        map.put("ids",new String[]{"072383e4-54fd-4cad-bdad-c87632996be4",
                "08837535-68c5-4998-af01-773d8bc4faa0",
                "0a0d4503-7b77-48a5-b845-5c9f6fba82cf"});
        int rows = dao.trashNotesRecover(map);
        System.out.println(rows);
    }
    @Test
    public void test3(){
        Map<String , Object> map = new HashMap<String,Object>();
        map.put("updated_at", new Date().getTime());
        map.put("ids",new String[]{"072383e4-54fd-4cad-bdad-c87632996be4",
                "08837535-68c5-4998-af01-773d8bc4faa0",
                "0a0d4503-7b77-48a5-b845-5c9f6fba82cf"});
        int rows = dao.trashNotesRecover(map);
        System.out.println(rows);
    }
}
