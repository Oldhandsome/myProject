package entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class NoteBook implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String note_book_id;
	private String user_id;
	private String note_book_type_id;
	private String note_book_name;
	private String explaination;
	private Timestamp created_at;
	private Timestamp updated_at;
	public String getNote_book_id() {
		return note_book_id;
	}
	public void setNote_book_id(String note_book_id) {
		this.note_book_id = note_book_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getNote_book_type_id() {
		return note_book_type_id;
	}
	public void setNote_book_type_id(String note_book_type_id) {
		this.note_book_type_id = note_book_type_id;
	}
	public String getNote_book_name() {
		return note_book_name;
	}
	public void setNote_book_name(String note_book_name) {
		this.note_book_name = note_book_name;
	}
	public String getExplaination() {
		return explaination;
	}
	public void setExplaination(String explaination) {
		this.explaination = explaination;
	}
	public Timestamp getCreated_at() {
		return created_at;
	}
	public void setCreated_at(long created_at) {
		this.created_at = new Timestamp(created_at);
	}
	public Timestamp getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(long updated_at) {
		this.updated_at = new Timestamp(updated_at);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((created_at == null) ? 0 : created_at.hashCode());
		result = prime * result + ((note_book_id == null) ? 0 : note_book_id.hashCode());
		result = prime * result + ((note_book_name == null) ? 0 : note_book_name.hashCode());
		result = prime * result + ((note_book_type_id == null) ? 0 : note_book_type_id.hashCode());
		result = prime * result + ((updated_at == null) ? 0 : updated_at.hashCode());
		result = prime * result + ((user_id == null) ? 0 : user_id.hashCode());
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
		NoteBook other = (NoteBook) obj;
		if (created_at == null) {
			if (other.created_at != null)
				return false;
		} else if (!created_at.equals(other.created_at))
			return false;
		if (note_book_id == null) {
			if (other.note_book_id != null)
				return false;
		} else if (!note_book_id.equals(other.note_book_id))
			return false;
		if (note_book_name == null) {
			if (other.note_book_name != null)
				return false;
		} else if (!note_book_name.equals(other.note_book_name))
			return false;
		if (note_book_type_id == null) {
			if (other.note_book_type_id != null)
				return false;
		} else if (!note_book_type_id.equals(other.note_book_type_id))
			return false;
		if (updated_at == null) {
			if (other.updated_at != null)
				return false;
		} else if (!updated_at.equals(other.updated_at))
			return false;
		if (user_id == null) {
			if (other.user_id != null)
				return false;
		} else if (!user_id.equals(other.user_id))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "NoteBook [note_book_id=" + note_book_id + ", user_id=" + user_id + ", note_book_type_id="
				+ note_book_type_id + ", note_book_name=" + note_book_name + ", explaination=" + explaination
				+ ", created_at=" + created_at + ", updated_at=" + updated_at + "]";
	}
	
	
}
