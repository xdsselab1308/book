package com.sselab.springboot.book.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sselab.springboot.book.common.BaseMapper;
import com.sselab.springboot.book.model.AuthorModel;
import com.sselab.springboot.book.model.BookModel;

public interface BookMapper extends BaseMapper<BookModel>{
	
	public List<BookModel> findBookInfo(Long authorId);
	
	public AuthorModel findAuthorId(String authorname);

	public List<BookModel> selectPaged(@Param("offset") Integer offset,
            @Param("limits") Integer limits,
            @Param("orderBy") String orderBy);
	
	public List<BookModel> findBookByName(String bookname);

	public List<BookModel> findBookByAuthorName(String authorname);
}