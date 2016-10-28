package com.sselab.springboot.book.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "BOOK")
public class BookModel {

    @Id
    @GeneratedValue(generator = "JDBC")
    @Column(name =  "BOOK_PK_ID")
    private Long bookId;

    @Column(name =  "BOOK_NAME")
    private String bookname;

    @Column(name =  "BOOK_YEAR")
    private String year;
    
    @Column(name =  "BOOK_FK_AUTHOR_ID")
    private Long authorId;


	public BookModel(Long bookId,String bookname, String year, Long authorId) {
		this.bookId = bookId;
		this.bookname = bookname;
        this.year = year;
        this.authorId = authorId;
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


	public Long getAuthorId() {
		return authorId;
	}


	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
	}


	@Override
	public String toString() {
		return "BookModel [bookId=" + bookId + ", bookname=" + 
				bookname + ", year=" + year+ ", authorId=" + authorId + "]";
	}
}




