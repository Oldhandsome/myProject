package entity;

import java.io.Serializable;

public class NoteStatus implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String note_status_id;
	private String note_status_code;
	private String note_status_name;
	private String note_status_explaination;
	public String getNote_status_id() {
		return note_status_id;
	}
	public void setNote_status_id(String note_status_id) {
		this.note_status_id = note_status_id;
	}
	public String getNote_status_code() {
		return note_status_code;
	}
	public void setNote_status_code(String note_status_code) {
		this.note_status_code = note_status_code;
	}
	public String getNote_status_name() {
		return note_status_name;
	}
	public void setNote_status_name(String note_status_name) {
		this.note_status_name = note_status_name;
	}
	public String getNote_status_explaination() {
		return note_status_explaination;
	}
	public void setNote_status_explaination(String note_status_explaination) {
		this.note_status_explaination = note_status_explaination;
	}
	@Override
	public String toString() {
		return "NoteStatus [note_status_id=" + note_status_id + ", note_status_code=" + note_status_code
				+ ", note_status_name=" + note_status_name + ", note_status_explaination=" + note_status_explaination
				+ "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((note_status_code == null) ? 0 : note_status_code.hashCode());
		result = prime * result + ((note_status_explaination == null) ? 0 : note_status_explaination.hashCode());
		result = prime * result + ((note_status_id == null) ? 0 : note_status_id.hashCode());
		result = prime * result + ((note_status_name == null) ? 0 : note_status_name.hashCode());
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
		NoteStatus other = (NoteStatus) obj;
		if (note_status_code == null) {
			if (other.note_status_code != null)
				return false;
		} else if (!note_status_code.equals(other.note_status_code))
			return false;
		if (note_status_explaination == null) {
			if (other.note_status_explaination != null)
				return false;
		} else if (!note_status_explaination.equals(other.note_status_explaination))
			return false;
		if (note_status_id == null) {
			if (other.note_status_id != null)
				return false;
		} else if (!note_status_id.equals(other.note_status_id))
			return false;
		if (note_status_name == null) {
			if (other.note_status_name != null)
				return false;
		} else if (!note_status_name.equals(other.note_status_name))
			return false;
		return true;
	}
	
	

}
