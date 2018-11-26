package com.revature.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Bookmark {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="bookSequence")
	@SequenceGenerator(name="bookSequence",allocationSize=1, sequenceName="SQ_BOOK_PK")
	private int book_id;
	
	@ManyToOne
	@JoinColumn(name="USER_ID")
	private User user;
	
	@Column
	private String u_rl;
	
	public Bookmark() {
		// TODO Auto-generated constructor stub
	}

	public Bookmark(User user, String u_rl) {
		super();
		this.user = user;
		this.u_rl = u_rl;
	}

	public int getBook_id() {
		return book_id;
	}

	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getU_rl() {
		return u_rl;
	}

	public void setU_rl(String u_rl) {
		this.u_rl = u_rl;
	}

	@Override
	public String toString() {
		return "Bookmark [book_id=" + book_id + ", user=" + user + ", u_rl=" + u_rl + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + book_id;
		result = prime * result + ((u_rl == null) ? 0 : u_rl.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bookmark other = (Bookmark) obj;
		if (book_id != other.book_id)
			return false;
		if (u_rl == null) {
			if (other.u_rl != null)
				return false;
		} else if (!u_rl.equals(other.u_rl))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	
}
