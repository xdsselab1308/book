package com.sselab.springboot.book.vm;

import java.util.ArrayList;
import java.util.List;

import com.sselab.springboot.book.model.BookModel;

public class BookVM {
	
    private Long bookId;
    private String bookname;
    private String year;
    
    private BookVM(Long bookId, String bookname, String year) {
        this.bookId = bookId;
        this.bookname = bookname;
        this.year = year;
    }

	public static BookVM FromModel(BookModel entity) {
        return new BookVM(entity.getBookId(), entity.getBookname(),entity.getYear());
    }

    public static List<BookVM> FromModel(List<BookModel> list) {
        List<BookVM> ret = new ArrayList<BookVM>(list.size());
        for (BookModel item : list) {
            ret.add(FromModel(item));
        }
        return ret;
    }

	public Long getBookId() {
		return bookId;
	}


	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}


	public String getBookname() {
		return bookname;
	}


	public void setBookname(String bookname) {
		this.bookname = bookname;
	}


	public String getYear() {
		return year;
	}


	public void setYear(String year) {
		this.year = year;
	}

}
