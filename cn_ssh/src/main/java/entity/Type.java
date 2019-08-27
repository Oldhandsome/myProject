package entity;

import java.io.Serializable;
import java.util.Objects;

public class Type implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String type_id;
	private String type_code;
	private String type_name;
	private String type_explaination;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getType_id() {
		return type_id;
	}

	public void setType_id(String type_id) {
		this.type_id = type_id;
	}

	public String getType_code() {
		return type_code;
	}

	public void setType_code(String type_code) {
		this.type_code = type_code;
	}

	public String getType_name() {
		return type_name;
	}

	public void setType_name(String type_name) {
		this.type_name = type_name;
	}

	public String getType_explaination() {
		return type_explaination;
	}

	public void setType_explaination(String type_explaination) {
		this.type_explaination = type_explaination;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Type)) return false;
		Type type = (Type) o;
		return Objects.equals(getType_id(), type.getType_id()) &&
				Objects.equals(getType_code(), type.getType_code()) &&
				Objects.equals(getType_name(), type.getType_name()) &&
				Objects.equals(getType_explaination(), type.getType_explaination());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getType_id(), getType_code(), getType_name(), getType_explaination());
	}

	@Override
	public String toString() {
		return "Type{" +
				"type_id='" + type_id + '\'' +
				", type_code='" + type_code + '\'' +
				", type_name='" + type_name + '\'' +
				", type_explaination='" + type_explaination + '\'' +
				'}';
	}
}
