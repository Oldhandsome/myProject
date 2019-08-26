package entity;

import java.io.Serializable;
import java.util.Objects;

public class Note implements Serializable {
    private String note_id;
    private String note_book_id;
    private String user_id;
    private String note_status_id;
    private String note_type_id;
    private String note_title;
    private Long updated_at;
    private Long created_at;
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

    public Long getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Long updated_at) {
        this.updated_at = updated_at;
    }

    public Long getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Long created_at) {
        this.created_at = created_at;
    }

    public String getNote_content() {
        return note_content;
    }

    public void setNote_content(String note_content) {
        this.note_content = note_content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Note)) return false;
        Note note = (Note) o;
        return Objects.equals(getNote_id(), note.getNote_id()) &&
                Objects.equals(getNote_book_id(), note.getNote_book_id()) &&
                Objects.equals(getUser_id(), note.getUser_id()) &&
                Objects.equals(getNote_status_id(), note.getNote_status_id()) &&
                Objects.equals(getNote_type_id(), note.getNote_type_id()) &&
                Objects.equals(getNote_title(), note.getNote_title()) &&
                Objects.equals(getUpdated_at(), note.getUpdated_at()) &&
                Objects.equals(getCreated_at(), note.getCreated_at()) &&
                Objects.equals(getNote_content(), note.getNote_content());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNote_id(), getNote_book_id(), getUser_id(), getNote_status_id(), getNote_type_id(), getNote_title(), getUpdated_at(), getCreated_at(), getNote_content());
    }

    @Override
    public String toString() {
        return "Note{" +
                "note_id='" + note_id + '\'' +
                ", note_book_id='" + note_book_id + '\'' +
                ", user_id='" + user_id + '\'' +
                ", note_status_id='" + note_status_id + '\'' +
                ", note_type_id='" + note_type_id + '\'' +
                ", note_title='" + note_title + '\'' +
                ", updated_at=" + updated_at +
                ", created_at=" + created_at +
                ", note_content='" + note_content + '\'' +
                '}';
    }
}
