package com.sselab.springboot.book.dao;

import java.util.List;

import com.sselab.springboot.book.model.AuthorModel;
import com.sselab.springboot.book.model.BookModel;
import com.sselab.springboot.book.vm.BookGetVM;

public interface  BookDao {
	
	List<BookModel> getInfo(Long authorId);
	
	long findAuthorId(String authorname);
	
	AuthorModel findAuthor(String authorname);
	
	int insert(BookModel model);

	boolean AuthorId(String authorname);
	
	int update(BookModel entity);
	
	int deleteById(long bookId);
	
	List<BookModel> selectPaged(int page, int limits);
	
	BookModel selectById(long bookId);
	
	BookModel findBookByName(String bookname);
	
	List<BookModel> finaBookByAuthorName(String authorname);
	
	
	
	

}
