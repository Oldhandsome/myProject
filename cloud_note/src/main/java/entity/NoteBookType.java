package entity;

import java.io.Serializable;

public class NoteBookType implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String note_book_type_id;
	private String note_book_type_code;
	private String note_book_type_name;
	private String note_book_type_explaination;
	public String getNote_book_type_id() {
		return note_book_type_id;
	}
	public void setNote_book_type_id(String note_book_type_id) {
		this.note_book_type_id = note_book_type_id;
	}
	public String getNote_book_type_code() {
		return note_book_type_code;
	}
	public void setNote_book_type_code(String note_book_type_code) {
		this.note_book_type_code = note_book_type_code;
	}
	public String getNote_book_type_name() {
		return note_book_type_name;
	}
	public void setNote_book_type_name(String note_book_type_name) {
		this.note_book_type_name = note_book_type_name;
	}
	public String getNote_book_type_explaination() {
		return note_book_type_explaination;
	}
	public void setNote_book_type_explaination(String note_book_type_explaination) {
		this.note_book_type_explaination = note_book_type_explaination;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((note_book_type_code == null) ? 0 : note_book_type_code.hashCode());
		result = prime * result + ((note_book_type_explaination == null) ? 0 : note_book_type_explaination.hashCode());
		result = prime * result + ((note_book_type_id == null) ? 0 : note_book_type_id.hashCode());
		result = prime * result + ((note_book_type_name == null) ? 0 : note_book_type_name.hashCode());
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
		NoteBookType other = (NoteBookType) obj;
		if (note_book_type_code == null) {
			if (other.note_book_type_code != null)
				return false;
		} else if (!note_book_type_code.equals(other.note_book_type_code))
			return false;
		if (note_book_type_explaination == null) {
			if (other.note_book_type_explaination != null)
				return false;
		} else if (!note_book_type_explaination.equals(other.note_book_type_explaination))
			return false;
		if (note_book_type_id == null) {
			if (other.note_book_type_id != null)
				return false;
		} else if (!note_book_type_id.equals(other.note_book_type_id))
			return false;
		if (note_book_type_name == null) {
			if (other.note_book_type_name != null)
				return false;
		} else if (!note_book_type_name.equals(other.note_book_type_name))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "NoteBookType [note_book_type_id=" + note_book_type_id + ", note_book_type_code=" + note_book_type_code
				+ ", note_book_type_name=" + note_book_type_name + ", note_book_type_explaination="
				+ note_book_type_explaination + "]";
	}
}
