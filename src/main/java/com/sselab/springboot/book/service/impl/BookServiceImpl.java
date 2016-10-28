package com.sselab.springboot.book.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sselab.springboot.book.dao.BookDao;
import com.sselab.springboot.book.form.BookForm;
import com.sselab.springboot.book.mapper.AuthorMapper;
import com.sselab.springboot.book.model.AuthorModel;
import com.sselab.springboot.book.model.BookModel;
import com.sselab.springboot.book.service.BookService;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;
    
    @Autowired
    private AuthorMapper mapper;
    
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

}
