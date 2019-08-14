package entity;

import java.io.Serializable;
import java.util.Objects;

public class Status implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String status_id;
	private String status_code;
	private String status_name;
	private String status_explaination;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getStatus_id() {
		return status_id;
	}

	public void setStatus_id(String status_id) {
		this.status_id = status_id;
	}

	public String getStatus_code() {
		return status_code;
	}

	public void setStatus_code(String status_code) {
		this.status_code = status_code;
	}

	public String getStatus_name() {
		return status_name;
	}

	public void setStatus_name(String status_name) {
		this.status_name = status_name;
	}

	public String getStatus_explaination() {
		return status_explaination;
	}

	public void setStatus_explaination(String status_explaination) {
		this.status_explaination = status_explaination;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Status)) return false;
		Status status = (Status) o;
		return Objects.equals(getStatus_id(), status.getStatus_id()) &&
				Objects.equals(getStatus_code(), status.getStatus_code()) &&
				Objects.equals(getStatus_name(), status.getStatus_name()) &&
				Objects.equals(getStatus_explaination(), status.getStatus_explaination());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getStatus_id(), getStatus_code(), getStatus_name(), getStatus_explaination());
	}

	@Override
	public String toString() {
		return "Status{" +
				"status_id='" + status_id + '\'' +
				", status_code='" + status_code + '\'' +
				", status_name='" + status_name + '\'' +
				", status_explaination='" + status_explaination + '\'' +
				'}';
	}
}
