package com.sselab.springboot.book.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sselab.springboot.book.dao.BookDao;
import com.sselab.springboot.book.form.BookForm;
import com.sselab.springboot.book.form.BookUpdateForm;
import com.sselab.springboot.book.mapper.AuthorMapper;
import com.sselab.springboot.book.mapper.BookMapper;
import com.sselab.springboot.book.model.AuthorModel;
import com.sselab.springboot.book.model.BookModel;
import com.sselab.springboot.book.service.BookService;
import com.sselab.springboot.book.vm.BookGetVM;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;
    
    @Autowired
    private AuthorMapper mapper;
    
    @Autowired
    private BookMapper bookmapper;
    
    
	@Override
	public List<BookModel> getBookInfo(Long authorId) {
		// TODO Auto-generated method stub
		return bookDao.getInfo(authorId);
	}

	@Override
	public long getAuthorId(String authorname) {
		// TODO Auto-generated method stub
		return bookDao.findAuthorId(authorname);
	}
	
	@Override
	public long add(BookForm form) {
		// TODO Auto-generated method stub
		if(bookDao.findAuthorId(form.getAuthorname())==1){
			AuthorModel authormodel = bookDao.findAuthor(form.getAuthorname());
//			System.out.println("id1: "+authormodel.getAuthorId());
//			System.out.println("name1: "+authormodel.getAuthorname());
//			System.out.println("email1: "+authormodel.getEmail());
//			System.out.println("address1: "+authormodel.getAddress());
			BookModel model = new BookModel(null, form.getBookname(), form.getYear(), authormodel.getAuthorId());
			bookDao.insert(model);
			return model.getBookId();
						
		}else{
			System.out.println(bookDao.findAuthorId(form.getAuthorname()));
			AuthorModel author = new AuthorModel(null, form.getAuthorname(),null, null);
			mapper.insert(author);
			AuthorModel author1 = bookDao.findAuthor(form.getAuthorname());
//			System.out.println("id: "+author1.getAuthorId());
//			System.out.println("name: "+author1.getAuthorname());
//			System.out.println("email: "+author1.getEmail());
//			System.out.println("address: "+author1.getAddress());
			BookModel model1 = new BookModel(null, form.getBookname(), form.getYear(), author1.getAuthorId());
			bookDao.insert(model1);
			return model1.getBookId();

		}

	}

	@Override
	public long updateById(BookUpdateForm form) {
		// TODO Auto-generated method stub
		
		if(bookDao.findAuthorId(form.getAuthorname())==1){
			BookModel model = new BookModel(form.getBookId(), form.getBookname(), form.getYear(), bookmapper.findAuthorId(form.getAuthorname()).getAuthorId());
			bookDao.update(model);
			return model.getBookId();						
		}else{		
			AuthorModel author = new AuthorModel(null, form.getAuthorname(),null, null);
			mapper.insert(author);
			BookModel model1 = new BookModel(form.getBookId(), form.getBookname(), form.getYear(),bookmapper.findAuthorId(form.getAuthorname()).getAuthorId());
			bookDao.update(model1);
			return model1.getBookId();
		}		
	}
	
	@Override
	public boolean updateById1(BookUpdateForm form) {
		// TODO Auto-generated method stub
		boolean flag = false;
		if(bookDao.findAuthorId(form.getAuthorname())==1){
			BookModel model = new BookModel(form.getBookId(), form.getBookname(), form.getYear(), bookmapper.findAuthorId(form.getAuthorname()).getAuthorId());
			if(bookDao.update(model)==1){
				flag = true;
			}
			return flag;						
		}else{		
			AuthorModel author = new AuthorModel(null, form.getAuthorname(),null, null);
			mapper.insert(author);
			BookModel model1 = new BookModel(form.getBookId(), form.getBookname(), form.getYear(),bookmapper.findAuthorId(form.getAuthorname()).getAuthorId());
			if(bookDao.update(model1)==1){
				flag = true;
			}			
			return flag;
		}		
	}

	@Override
	public int deleteById(long bookId) {
		// TODO Auto-generated method stub
		return bookDao.deleteById(bookId);
	}

	@Override
	public List<BookModel> selectPaged(int page, int limits) {
		// TODO Auto-generated method stub
		return bookDao.selectPaged(page, limits);
	}

	@Override
	public BookModel getById(long bookId) {
		// TODO Auto-generated method stub
		return bookDao.selectById(bookId);
	}

	@Override
	public List<BookGetVM> getByName(String bookname) {
		// TODO Auto-generated method stub
		
		List<BookModel> model = bookDao.findBookByName(bookname);
		List<BookGetVM> bookmodel = new ArrayList<BookGetVM>();
		for(int i=0;i<model.size();i++){
			BookGetVM book = new BookGetVM();
			book.setBookId(model.get(i).getBookId());
			book.setBookname(model.get(i).getBookname());
			book.setYear(model.get(i).getYear());
			book.setAuthorname(mapper.selectByPrimaryKey(model.get(i).getAuthorId()).getAuthorname());
			bookmodel.add(book);
		}
		return bookmodel;
	}

	@Override
	public List<BookGetVM> getBookByAuthorName(String authorname) {
		// TODO Auto-generated method stub
		List<BookModel> model = bookDao.finaBookByAuthorName(authorname);
		List<BookGetVM> bookmodel = new ArrayList<BookGetVM>();
		for(int i=0;i<model.size();i++){
			BookGetVM book = new BookGetVM();
			book.setBookId(model.get(i).getBookId());
			book.setBookname(model.get(i).getBookname());
			book.setYear(model.get(i).getYear());
			book.setAuthorname(mapper.selectByPrimaryKey(model.get(i).getAuthorId()).getAuthorname());
			bookmodel.add(book);
		}
		return bookmodel;
	}
	
	@Override
	public AuthorModel selectById(long authorId) {
		// TODO Auto-generated method stub
		return mapper.selectByPrimaryKey(authorId);
	}

	@Override
	public List<BookGetVM> getBookInof(int page, int limits) {
		// TODO Auto-generated method stub
		List<BookModel> model = bookDao.selectPaged(page, limits);
		List<BookGetVM> bookmodel = new ArrayList<BookGetVM>();
		for(int i=0;i<model.size();i++){
			BookGetVM book = new BookGetVM();
			book.setBookId(model.get(i).getBookId());
			book.setBookname(model.get(i).getBookname());
			book.setYear(model.get(i).getYear());
			book.setAuthorname(mapper.selectByPrimaryKey(model.get(i).getAuthorId()).getAuthorname());
			bookmodel.add(book);
		}
		return bookmodel;
	}
}
