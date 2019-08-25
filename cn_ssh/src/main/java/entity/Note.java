package entity;

public class Note {
    private String noteId;
    private String noteTitle;
    private Long updatedAt;
    private Long createdAt;
    private String noteContent;

    public String getNoteId() {
        return noteId;
    }

    public void setNoteId(String noteId) {
        this.noteId = noteId;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public Long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Long updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public String getNoteContent() {
        return noteContent;
    }

    public void setNoteContent(String noteContent) {
        this.noteContent = noteContent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Note note = (Note) o;

        if (noteId != null ? !noteId.equals(note.noteId) : note.noteId != null) return false;
        if (noteTitle != null ? !noteTitle.equals(note.noteTitle) : note.noteTitle != null) return false;
        if (updatedAt != null ? !updatedAt.equals(note.updatedAt) : note.updatedAt != null) return false;
        if (createdAt != null ? !createdAt.equals(note.createdAt) : note.createdAt != null) return false;
        if (noteContent != null ? !noteContent.equals(note.noteContent) : note.noteContent != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = noteId != null ? noteId.hashCode() : 0;
        result = 31 * result + (noteTitle != null ? noteTitle.hashCode() : 0);
        result = 31 * result + (updatedAt != null ? updatedAt.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (noteContent != null ? noteContent.hashCode() : 0);
        return result;
    }
}
