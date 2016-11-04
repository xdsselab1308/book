package com.sselab.springboot.book.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.sselab.springboot.book.dao.BookDao;
import com.sselab.springboot.book.form.BookForm;
import com.sselab.springboot.book.form.BookUpdateForm;
import com.sselab.springboot.book.mapper.AuthorMapper;
import com.sselab.springboot.book.mapper.BookMapper;
import com.sselab.springboot.book.model.AuthorModel;
import com.sselab.springboot.book.model.BookModel;
import com.sselab.springboot.book.service.BookService;
import com.sselab.springboot.book.vm.BookGetVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;
    
    @Autowired
    private AuthorMapper mapper;
    
    @Autowired
    private BookMapper bookmapper;
    
    
	@Override
    @HystrixCommand(fallbackMethod = "getBookInfoFB")
	public List<BookModel> getBookInfo(Long authorId) {
		// TODO Auto-generated method stub
		return bookDao.getInfo(authorId);
	}

    public List<BookModel> getBookInfoFB(Long authorId) {
        //这里可以添加错误日志的收集
        return null;
    }

	@Override
    @HystrixCommand(fallbackMethod = "getAuthorIdFB")
	public long getAuthorId(String authorname) {
		// TODO Auto-generated method stub
		return bookDao.findAuthorId(authorname);
	}

    public long getAuthorIdFB(String authorname) {    return 0l;  }
	
	@Override
    @HystrixCommand(fallbackMethod = "addFB")
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

    public long addFB(BookForm form) {  return 0l;  }

	@Override
    @HystrixCommand(fallbackMethod = "updateByIdFB")
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

    public long updateByIdFB(BookUpdateForm form) {   return 0l;  }
	
	@Override
    @HystrixCommand(fallbackMethod = "updateById1FB")
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

    public boolean updateById1FB(BookUpdateForm form) { return false; }

	@Override
    @HystrixCommand(fallbackMethod = "deleteByIdFB")
	public int deleteById(long bookId) {
		// TODO Auto-generated method stub
		return bookDao.deleteById(bookId);
	}

    public int deleteByIdFB(long bookId) {    return 0;   }

	@Override
    @HystrixCommand(fallbackMethod = "selectPagedFB")
	public List<BookModel> selectPaged(int page, int limits) {
		// TODO Auto-generated method stub
		return bookDao.selectPaged(page, limits);
	}

    public List<BookModel> selectPagedFB(int page, int limits) {  return null;    }

	@Override
    @HystrixCommand(fallbackMethod = "getByIdFB")
	public BookModel getById(long bookId) {
		// TODO Auto-generated method stub
		return bookDao.selectById(bookId);
	}

    public BookModel getByIdFB(long bookId) {
        System.out.println("error");
        return null;
    }

	@Override
    @HystrixCommand(fallbackMethod = "getByNameFB")
	public BookModel getByName(String bookname) {
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

    public BookModel getByNameFB(String bookname) {   return null;    }

	@Override
    @HystrixCommand(fallbackMethod = "getBookByAuthorNameFB")
	public List<BookModel> getBookByAuthorName(String authorname) {
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

    public List<BookModel> getBookByAuthorNameFB(String authorname) { return null;    }
	
	@Override
    @HystrixCommand(fallbackMethod = "selectByIdFB")
	public AuthorModel selectById(long authorId) {
		// TODO Auto-generated method stub
		return mapper.selectByPrimaryKey(authorId);
	}

    public AuthorModel selectByIdFB(long authorId) {  return null;    }

	@Override
    @HystrixCommand(fallbackMethod = "getBookInofFB")
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

    public List<BookGetVM> getBookInofFB(int page, int limits) {  return null;    }
}
