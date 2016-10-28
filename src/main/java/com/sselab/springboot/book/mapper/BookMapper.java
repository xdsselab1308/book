package com.sselab.springboot.book.mapper;

import java.util.List;

import com.sselab.springboot.book.common.BaseMapper;
import com.sselab.springboot.book.model.AuthorModel;
import com.sselab.springboot.book.model.BookModel;

public interface BookMapper extends BaseMapper<BookModel>{
	
	public List<BookModel> findBookInfo(Long authorId);
	
	public AuthorModel findAuthorId(String authorname);

}