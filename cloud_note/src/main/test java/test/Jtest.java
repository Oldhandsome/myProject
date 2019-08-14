package test;

import java.util.Date;
import java.util.List;

import entity.Information;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import dao.NoteBookDao;
import dao.NoteDao;
import dao.UserDao;
import entity.Note;
import entity.NoteBook;
import entity.User;
import service.NoteBookService;
import service.NoteService;
import service.UserService;
import util.NoteResult;
import util.NoteUtil;
import util.RandomUtil;

public class Jtest {
	ApplicationContext ac;
	NoteService ns;
	NoteBookService nbs;
	UserService us;
	UserDao dao;
	NoteBookDao noteBookDao;
	NoteDao noteDao;
	@Before
	public void init() {
		ac = new ClassPathXmlApplicationContext("conf/spring-mybatis.xml");
//		us = ac.getBean("userService",UserService.class);
//		dao = ac.getBean("userDaoImpl",UserDao.class);
//		noteBookDao = ac.getBean("noteBookDaoImpl",NoteBookDao.class);
//		noteDao = ac.getBean("noteDaoImpl",NoteDao.class);
//		nbs = ac.getBean("noteBookServiceImpl",NoteBookService.class);
		ns = ac.getBean("noteServiceImpl",NoteService.class);
	}
	@Test
	public void test() {
		NoteResult<User> result = us.checkLogin("00001", "jackson");
		System.out.println(result);
	}
	@Test
	public void test1() {
		NoteResult<User> result = us.checkLogin("00001", "00001");
		System.out.println(result);
	}
	@Test
	public void test2() {
		NoteResult<User> result = us.checkLogin("00000", "00000");
		System.out.println(result);
	}
	
	@Test
	public void test3() {
		User user = new User();
		user.setUser_id("00004");
		user.setUser_name("jack");
		user.setPassword("123456");
		user.setAuthoritative_code("12345678");
		user.setExplaination("这人很丑");
		dao.insert(user);
	}
	@Test
	public void test4() {
		User user = new User();
		user.setUser_id("00004");
		user.setUser_name("Lucy");
		user.setPassword("123456");
		user.setAuthoritative_code("12345678");
		user.setExplaination("这人很美");
		dao.update(user);
	}
	@Test
	public void test5() {
		dao.delete("00004");
	}
	
	@Test
	public void test6() {
		String id = null;
		do {
			id = RandomUtil.random();
		}while(dao.findById(id) != null);
		System.out.println("user_id:"+id);
	}
	@Test
	public void test7() {
		User user = new User();
		user.setUser_name("jack");
		user.setPassword("123456");
		user.setAuthoritative_code("11111111");
		user.setExplaination("这人很黑！");
		NoteResult<String> result = us.register(user);
		System.out.println(result);
	}
	@Test
	public void test8() {
		List<NoteBook> books = noteBookDao.findByUserId("79519");
		System.out.println(books);
	}
	@Test
	public void test9() {
		List<Note> notes = noteDao.findByIds("6419a47a-c980-4531-b538-3f8ade974f7c");
		System.out.println(notes);
	}
	@Test
	public void test10() {
		System.out.println(nbs.loadUserNoteBooks("86063"));
	}
	@Test
	public void test11() {
		List<Note> notes = noteDao.findByIds("6419a47a-c980-4531-b538-3f8ade974f7c");
		for (Note note : notes) {
			System.out.println(note);
		}
	}
	@Test
	public void test12() {
		NoteResult<List<Note>> result = ns.loadBookNotes("7c67fc47-ba75-4271-b56f-5f9999079795");
		List<Note> notes = result.getData();
		for (Note note : notes) {
			System.out.println(note);
		}
	}
	
	@Test
	public void test13() {
		System.out.println(noteDao.loadNoteContent("072383e4-54fd-4cad-bdad-c87632996be4"));
	}
	@Test
	public void test14() {
		System.out.println(ns.loadNoteContent("072383e4-54fd-4cad-bdad-c87632996be4"));
	}

	@Test
	public void test16() {
		NoteBook noteBook = new NoteBook();
		noteBook.setNote_book_id(NoteUtil.createId());
		noteBook.setNote_book_name("music");
		noteBook.setNote_book_type_id("0");
		noteBook.setUser_id("86063");
		noteBook.setCreated_at(new Date().getTime());
		noteBook.setExplaination("音乐笔记本");
		System.out.println(nbs.addNoteBook(noteBook));
	}
	@Test
	public void test17(){
		noteDao.deleteNote("4ec450e1-88e4-4a75-91c1-1ba0792e32de");
	}
	@Test
	public void test18(){
		Note note = new Note();
		note.setUser_id("79519");
		note.setNote_title("math-4");
		note.setNote_status_id("1");
		note.setNote_id(NoteUtil.createId());
		note.setNote_content("the question is too hard to complete!");
		note.setNote_book_id("7c67fc47-ba75-4271-b56f-5f9999079795");
		note.setCreated_at(new Date().getTime());
		System.out.println(ns.addNote(note));
	}
	@Test
	public void test19(){
		System.out.println(ns.deleteNote("e9d1f223-e3b9-49e8-ba89-65a1342217a9"));
	}
	@Test
	public void test20(){
		NoteBook noteBook = new NoteBook();
		noteBook.setNote_book_id(NoteUtil.createId());
		noteBook.setNote_book_name("playing");
		noteBook.setNote_book_type_id("0");
		noteBook.setUser_id("86063");
		noteBook.setCreated_at(new Date().getTime());
		noteBook.setExplaination("playing笔记本");
		System.out.println(nbs.addNoteBook(noteBook));;
	}
	@Test
	public void test21(){
		System.out.println(ns.starNote("072383e4-54fd-4cad-bdad-c87632996be4"));
	}
	@Test
	public void test22(){
		System.out.println(ns.unstartNote("072383e4-54fd-4cad-bdad-c87632996be4"));
	}
	@Test
	public void test23(){
		String note_id = "e786f144-6df8-4a5f-a03b-3487458c43c0";
		String note_book_id = "6419a47a-c980-4531-b538-3f8ade974f7c";
		noteDao.moveNote(note_id,note_book_id,new Date().getTime());
	}
	@Test
	public void test24(){
		String note_id = "e786f144-6df8-4a5f-a03b-3487458c43c0";
		String note_book_id = "6419a47a-c980-4531-b538-3f8ade974f7c";
		System.out.println(ns.moveNote(note_id,note_book_id));
	}
	@Test
	public void test25(){
		List<Information> list = noteDao.loadNotes("1","79519");
		for(Information info:list){
			System.out.println(info);
		}
	}
	@Test
	public void test26(){
		List<Information> list = noteDao.trash("79519");
		for(Information info:list){
			System.out.println(info);
		}
	}
	@Test
	public void test27(){
		List<Information> list = noteDao.searchNotes("79519","%笔记%");
		for(Information info:list){
			System.out.println(info);
		}
	}
	@Test
	public void test28(){
		NoteResult<List<Information>> list = ns.loadTrushNote("79519");
		for(Information info:list.getData()){
			System.out.println(info);
		}
	}
	@Test
	public void test29(){
		NoteResult<List<Information>> list = ns.searchNotes("79519","笔记");
		for(Information info:list.getData()){
			System.out.println(info);
		}
	}
}
