package entity;

import java.io.Serializable;
import java.util.Objects;

public class NoteBook implements Serializable {
    private String note_book_id;
    private String user_id;
    private String note_book_type_id;
    private String note_book_name;
    private String explaination;
    private Long created_at;
    private Long updated_at;

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

    public Long getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Long created_at) {
        this.created_at = created_at;
    }

    public Long getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Long updated_at) {
        this.updated_at = updated_at;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NoteBook)) return false;
        NoteBook noteBook = (NoteBook) o;
        return Objects.equals(getNote_book_id(), noteBook.getNote_book_id()) &&
                Objects.equals(getUser_id(), noteBook.getUser_id()) &&
                Objects.equals(getNote_book_type_id(), noteBook.getNote_book_type_id()) &&
                Objects.equals(getNote_book_name(), noteBook.getNote_book_name()) &&
                Objects.equals(getExplaination(), noteBook.getExplaination()) &&
                Objects.equals(getCreated_at(), noteBook.getCreated_at()) &&
                Objects.equals(getUpdated_at(), noteBook.getUpdated_at());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNote_book_id(), getUser_id(), getNote_book_type_id(), getNote_book_name(), getExplaination(), getCreated_at(), getUpdated_at());
    }

    @Override
    public String toString() {
        return "Book{" +
                "note_book_id='" + note_book_id + '\'' +
                ", user_id='" + user_id + '\'' +
                ", note_book_type_id='" + note_book_type_id + '\'' +
                ", note_book_name='" + note_book_name + '\'' +
                ", explaination='" + explaination + '\'' +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                '}';
    }
}
