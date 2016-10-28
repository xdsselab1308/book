package com.sselab.springboot.book.dao;

import java.util.List;

import com.sselab.springboot.book.model.AuthorModel;
import com.sselab.springboot.book.model.BookModel;

public interface  BookDao {
	
	List<BookModel> getInfo(Long authorId);
	
	long findAuthorId(String authorname);
	
	AuthorModel findAuthor(String authorname);
	
	int insert(BookModel model);

	boolean AuthorId(String authorname);

}
