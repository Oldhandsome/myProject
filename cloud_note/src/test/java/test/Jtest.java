package test;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.omg.CORBA.PUBLIC_MEMBER;
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
		noteDao = ac.getBean("noteDaoImpl",NoteDao.class);
//		nbs = ac.getBean("noteBookServiceImpl",NoteBookService.class);
//		ns = ac.getBean("noteServiceImpl",NoteService.class);
		
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
/*	@Test
	public void test9() {
		List<Note> notes = noteDao.findByIds("79519", "6419a47a-c980-4531-b538-3f8ade974f7c");
		System.out.println(notes);
	}
	@Test
	public void test10() {
		System.out.println(nbs.loadUserNoteBooks("86063"));
	}
	@Test
	public void test11() {
		List<Note> notes = noteDao.findByIds("79519", "6419a47a-c980-4531-b538-3f8ade974f7c");
		for (Note note : notes) {
			System.out.println(note);
		}
	}
	@Test
	public void test12() {
		NoteResult<List<Note>> result = ns.loadBookNotes("7c67fc47-ba75-4271-b56f-5f9999079795","1");
		List<Note> notes = result.getData();
		for (Note note : notes) {
			System.out.println(note);
		}
	}
	*/
	@Test
	public void test13() {
		System.out.println(noteDao.loadNoteContent("072383e4-54fd-4cad-bdad-c87632996be4"));
	}
	@Test
	public void test14() {
		System.out.println(ns.loadNoteContent("072383e4-54fd-4cad-bdad-c87632996be4"));
	}
	@Test
	public void test15(){
		NoteBook noteBook = new NoteBook();
		noteBook.setNote_book_id(NoteUtil.createId());
		noteBook.setNote_book_name("music");
		noteBook.setNote_book_type_id("0");
		noteBook.setUser_id("79519");
		noteBook.setExplaination("音乐笔记本");
		noteBook.setCreated_at(new Date().getTime());
		System.out.println(noteBook);
		noteBookDao.addNoteBook(noteBook);
	}
	@Test
	public void test16(){
		NoteBook noteBook = new NoteBook();
		noteBook.setNote_book_id(NoteUtil.createId());
		noteBook.setNote_book_name("music");
		noteBook.setNote_book_type_id("0");
		noteBook.setUser_id("79519");
		noteBook.setExplaination("音乐笔记本");
		noteBook.setCreated_at(new Date().getTime());
		System.out.println(noteBook);
		nbs.addNoteBook(noteBook);
	}
	@Test
	public void test17(){
		Note note = new Note();
		note.setUser_id("79519");
		note.setNote_book_id("0124c6b3-eae9-463c-a4b4-be600253430d");
		note.setNote_status_id("1");
		note.setNote_type_id(null);
		note.setNote_title("music_1");
		note.setNote_content("这首歌真的难唱a");
		note.setNote_id(NoteUtil.createId());
		note.setCreated_at(new Date().getTime());
		System.out.println(note);
		noteDao.addNote(note);
	}
	@Test
	public void test18(){
		Note note = new Note();
		note.setUser_id("79519");
		note.setNote_book_id("0124c6b3-eae9-463c-a4b4-be600253430d");
		note.setNote_status_id("1");
		note.setNote_type_id(null);
		note.setNote_title("music_1");
		note.setNote_content("这首歌真的难唱a");
		note.setNote_id(NoteUtil.createId());
		note.setCreated_at(new Date().getTime());
		System.out.println(note);
		System.out.println(ns.addNote(note));
	}
	@Test
	public void test19(){
		noteDao.moveToTrush("072383e4-54fd-4cad-bdad-c87632996be4");
	}
	@Test
	public void test20(){
		List<Note> notes = noteDao.searchNotes("79519","%默认笔记%");
		for (Note note : notes) {
			System.out.println(note);
		}
	}
	@Test
	public void test21(){
		NoteResult<List<Note>> notes = ns.searchNotes("79519","默认笔记");
		for (Note note : notes.getData()) {
			System.out.println(note);
		}
	}
	@Test
	public void test22(){
		String note_title = "娃哈哈";
		String newtitle = String.format("%%%s%%", note_title);
		System.out.println(newtitle);
	}
	@Test
	public void test23(){
		List<Note> notes = noteDao.trash("79519");
		for (Note note : notes) {
			System.out.println(note);
		}
	}
	@Test
	public void test24(){
		List<Note> notes = ns.loadTrush("79519").getData();
		for (Note note : notes) {
			System.out.println(note);
		}
	}
	@Test
	public void test25(){
		noteDao.loadNotes()
	}

}
