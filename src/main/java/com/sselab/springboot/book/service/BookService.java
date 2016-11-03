package com.sselab.springboot.book.service;

import java.util.List;

import com.sselab.springboot.book.form.BookForm;
import com.sselab.springboot.book.form.BookUpdateForm;
import com.sselab.springboot.book.model.AuthorModel;
import com.sselab.springboot.book.model.BookModel;
import com.sselab.springboot.book.vm.BookGetVM;

public interface BookService {

	public List<BookModel> getBookInfo(Long authorId);
	
	public long getAuthorId(String authorname);

	long add(BookForm form);
	
	long updateById(BookUpdateForm form);
	
	int deleteById(long bookId);
	
	List<BookModel> selectPaged(int page, int limits);
	
	BookModel getById(long bookId);
	
	BookModel getByName(String bookname);

	public List<BookModel> getBookByAuthorName(String authorname);
	
	List<BookGetVM> getBookInof(int page, int limits);

	AuthorModel selectById(long authorId);

	boolean updateById1(BookUpdateForm form);
}

