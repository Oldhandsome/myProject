package entity;

public class NoteBook {
    private String noteBookId;
    private String noteBookName;
    private String explaination;
    private Long createdAt;
    private Long updatedAt;

    public String getNoteBookId() {
        return noteBookId;
    }

    public void setNoteBookId(String noteBookId) {
        this.noteBookId = noteBookId;
    }

    public String getNoteBookName() {
        return noteBookName;
    }

    public void setNoteBookName(String noteBookName) {
        this.noteBookName = noteBookName;
    }

    public String getExplaination() {
        return explaination;
    }

    public void setExplaination(String explaination) {
        this.explaination = explaination;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public Long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Long updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NoteBook noteBook = (NoteBook) o;

        if (noteBookId != null ? !noteBookId.equals(noteBook.noteBookId) : noteBook.noteBookId != null) return false;
        if (noteBookName != null ? !noteBookName.equals(noteBook.noteBookName) : noteBook.noteBookName != null)
            return false;
        if (explaination != null ? !explaination.equals(noteBook.explaination) : noteBook.explaination != null)
            return false;
        if (createdAt != null ? !createdAt.equals(noteBook.createdAt) : noteBook.createdAt != null) return false;
        if (updatedAt != null ? !updatedAt.equals(noteBook.updatedAt) : noteBook.updatedAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = noteBookId != null ? noteBookId.hashCode() : 0;
        result = 31 * result + (noteBookName != null ? noteBookName.hashCode() : 0);
        result = 31 * result + (explaination != null ? explaination.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (updatedAt != null ? updatedAt.hashCode() : 0);
        return result;
    }
}
