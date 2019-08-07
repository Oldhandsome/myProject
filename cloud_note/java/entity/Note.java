package entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class Note implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String note_id;
	private String note_book_id;
	private String user_id;
	private String note_status_id;
	private String note_type_id;
	private String note_title;
	private Timestamp updated_at;
	private Timestamp created_at;
	private String note_content;
	public String getNote_id() {
		return note_id;
	}
	public void setNote_id(String note_id) {
		this.note_id = note_id;
	}
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
	public String getNote_status_id() {
		return note_status_id;
	}
	public void setNote_status_id(String note_status_id) {
		this.note_status_id = note_status_id;
	}
	public String getNote_type_id() {
		return note_type_id;
	}
	public void setNote_type_id(String note_type_id) {
		this.note_type_id = note_type_id;
	}
	public String getNote_title() {
		return note_title;
	}
	public void setNote_title(String note_title) {
		this.note_title = note_title;
	}
	public Timestamp getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(long updated_at) {
		this.updated_at = new Timestamp(updated_at);
	}
	public Timestamp getCreated_at() {
		return created_at;
	}
	public void setCreated_at(long created_at) {
		this.created_at = new Timestamp(created_at);
	}
	public String getNote_content() {
		return note_content;
	}
	public void setNote_content(String note_content) {
		this.note_content = note_content;
	}
	@Override
	public String toString() {
		return "Note [note_id=" + note_id + ", note_book_id=" + note_book_id + ", user_id=" + user_id
				+ ", note_status_id=" + note_status_id + ", note_type_id=" + note_type_id + ", note_title=" + note_title
				+ ", updated_at=" + updated_at + ", created_at=" + created_at + ", note_content=" + note_content + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((created_at == null) ? 0 : created_at.hashCode());
		result = prime * result + ((note_book_id == null) ? 0 : note_book_id.hashCode());
		result = prime * result + ((note_content == null) ? 0 : note_content.hashCode());
		result = prime * result + ((note_id == null) ? 0 : note_id.hashCode());
		result = prime * result + ((note_status_id == null) ? 0 : note_status_id.hashCode());
		result = prime * result + ((note_title == null) ? 0 : note_title.hashCode());
		result = prime * result + ((note_type_id == null) ? 0 : note_type_id.hashCode());
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
		Note other = (Note) obj;
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
		if (note_content == null) {
			if (other.note_content != null)
				return false;
		} else if (!note_content.equals(other.note_content))
			return false;
		if (note_id == null) {
			if (other.note_id != null)
				return false;
		} else if (!note_id.equals(other.note_id))
			return false;
		if (note_status_id == null) {
			if (other.note_status_id != null)
				return false;
		} else if (!note_status_id.equals(other.note_status_id))
			return false;
		if (note_title == null) {
			if (other.note_title != null)
				return false;
		} else if (!note_title.equals(other.note_title))
			return false;
		if (note_type_id == null) {
			if (other.note_type_id != null)
				return false;
		} else if (!note_type_id.equals(other.note_type_id))
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
	
	
	
}
