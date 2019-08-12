package entity;

import java.io.Serializable;

public class NoteType implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String note_type_id;
	private String note_type_code;
	private String note_type_name;
	public String getNote_type_id() {
		return note_type_id;
	}
	public void setNote_type_id(String note_type_id) {
		this.note_type_id = note_type_id;
	}
	public String getNote_type_code() {
		return note_type_code;
	}
	public void setNote_type_code(String note_type_code) {
		this.note_type_code = note_type_code;
	}
	public String getNote_type_name() {
		return note_type_name;
	}
	public void setNote_type_name(String note_type_name) {
		this.note_type_name = note_type_name;
	}
	@Override
	public String toString() {
		return "NoteType [note_type_id=" + note_type_id + ", note_type_code=" + note_type_code + ", note_type_name="
				+ note_type_name + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((note_type_code == null) ? 0 : note_type_code.hashCode());
		result = prime * result + ((note_type_id == null) ? 0 : note_type_id.hashCode());
		result = prime * result + ((note_type_name == null) ? 0 : note_type_name.hashCode());
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
		NoteType other = (NoteType) obj;
		if (note_type_code == null) {
			if (other.note_type_code != null)
				return false;
		} else if (!note_type_code.equals(other.note_type_code))
			return false;
		if (note_type_id == null) {
			if (other.note_type_id != null)
				return false;
		} else if (!note_type_id.equals(other.note_type_id))
			return false;
		if (note_type_name == null) {
			if (other.note_type_name != null)
				return false;
		} else if (!note_type_name.equals(other.note_type_name))
			return false;
		return true;
	}

	
}
