package entity;

import java.io.Serializable;
import java.util.Objects;

public class Information implements Serializable {
    //信息类
    private String id;
    private String name;
    private String type;
    private String status;
    private long created_at;
    private long updated_at;
    private String location;

    public Information() {
    }

    public Information(String id, String name, String type, String status, long created_at, long updated_at, String location) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.status = status;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCreated_at() {
        return created_at;
    }

    public void setCreated_at(long created_at) {
        this.created_at = created_at;
    }

    public long getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(long updated_at) {
        this.updated_at = updated_at;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Information)) return false;
        Information that = (Information) o;
        return getCreated_at() == that.getCreated_at() &&
                getUpdated_at() == that.getUpdated_at() &&
                Objects.equals(getId(), that.getId()) &&
                Objects.equals(getName(), that.getName()) &&
                Objects.equals(getStatus(), that.getStatus()) &&
                Objects.equals(getType(), that.getType()) &&
                Objects.equals(getLocation(), that.getLocation());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getCreated_at(), getUpdated_at(), getStatus(), getType(), getLocation());
    }

    @Override
    public String toString() {
        return "Information{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                ", status='" + status + '\'' +
                ", type='" + type + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
