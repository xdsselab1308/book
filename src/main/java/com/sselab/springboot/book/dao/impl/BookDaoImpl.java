package com.sselab.springboot.book.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sselab.springboot.book.dao.BookDao;
import com.sselab.springboot.book.mapper.BookMapper;
import com.sselab.springboot.book.model.AuthorModel;
import com.sselab.springboot.book.model.BookModel;

@Repository
public class BookDaoImpl implements BookDao {

    @Autowired
    private BookMapper mapper;

	@Override
	public List<BookModel> getInfo(Long authorId) {
		// TODO Auto-generated method stub
		return mapper.findBookInfo(authorId);
	}

	@Override
	public long findAuthorId(String authorname) {
		// TODO Auto-generated method stub
		long flag = 0;
		try{
			AuthorModel model = mapper.findAuthorId(authorname);
			if(model.getAuthorId()!=null){
				flag = 1;
			}			
		}catch(Exception e){
//			e.printStackTrace();
		}
		return flag;
	}
	
	public boolean AuthorId(String authorname) {
		// TODO Auto-generated method stub
		if(mapper.findAuthorId(authorname).getAuthorId()==null){
			return false;
		}else{
			return true;
		}
	}

	@Override
	public AuthorModel findAuthor(String authorname) {
		// TODO Auto-generated method stub
		return mapper.findAuthorId(authorname);
	}

	@Override
	public int insert(BookModel model) {
		// TODO Auto-generated method stub
		return mapper.insert(model);
	}
	
	

}

