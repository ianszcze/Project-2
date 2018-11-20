package com.revature.beans;

public class Bookmark {

	private int book_id;
	private int user_id;
	private String u_rl;
	
	public Bookmark() {
		// TODO Auto-generated constructor stub
	}

	public Bookmark(int book_id, int user_id, String u_rl) {
		super();
		this.book_id = book_id;
		this.user_id = user_id;
		this.u_rl = u_rl;
	}

	public int getBook_id() {
		return book_id;
	}

	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getU_rl() {
		return u_rl;
	}

	public void setU_rl(String u_rl) {
		this.u_rl = u_rl;
	}

	@Override
	public String toString() {
		return "Bookmark [book_id=" + book_id + ", user_id=" + user_id + ", u_rl=" + u_rl + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + book_id;
		result = prime * result + ((u_rl == null) ? 0 : u_rl.hashCode());
		result = prime * result + user_id;
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
		if (user_id != other.user_id)
			return false;
		return true;
	}

	
}
