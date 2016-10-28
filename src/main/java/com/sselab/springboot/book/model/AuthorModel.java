package com.sselab.springboot.book.model;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "AUTHOR")
public class AuthorModel {

    @Id
    @GeneratedValue(generator = "JDBC")
    @Column(name =  "AUTHOR_PK_ID")
    private Long authorId;

    @Column(name =  "AUTHOR_NAME")
    private String authorname;

    @Column(name =  "AUTHOR_EMAIL")
    private String email;
    
    @Column(name =  "AUTHOR_ADDRESS")
    private String address;

   

	public AuthorModel(Long authorId,String authorname, String email, String address) {
		this.authorId = authorId;
		this.authorname = authorname;
        this.email = email;
        this.address = address;
	}



	public Long getAuthorId() {
		return authorId;
	}



	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
	}



	public String getAuthorname() {
		return authorname;
	}



	public void setAuthorname(String authorname) {
		this.authorname = authorname;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getAddress() {
		return address;
	}



	public void setAddress(String address) {
		this.address = address;
	}



	@Override
	public String toString() {
		return "AuthorModel [authorId=" + authorId + ", authorname=" + authorname 
				+ ", email=" + email + ", address=" + address + "]";
	}
}





