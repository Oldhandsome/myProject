package entity;

public class User {
    private String userId;
    private String userName;
    private String password;
    private String authoritativeCode;
    private String explaination;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAuthoritativeCode() {
        return authoritativeCode;
    }

    public void setAuthoritativeCode(String authoritativeCode) {
        this.authoritativeCode = authoritativeCode;
    }

    public String getExplaination() {
        return explaination;
    }

    public void setExplaination(String explaination) {
        this.explaination = explaination;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (userId != null ? !userId.equals(user.userId) : user.userId != null) return false;
        if (userName != null ? !userName.equals(user.userName) : user.userName != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (authoritativeCode != null ? !authoritativeCode.equals(user.authoritativeCode) : user.authoritativeCode != null)
            return false;
        if (explaination != null ? !explaination.equals(user.explaination) : user.explaination != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (authoritativeCode != null ? authoritativeCode.hashCode() : 0);
        result = 31 * result + (explaination != null ? explaination.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", authoritativeCode='" + authoritativeCode + '\'' +
                ", explaination='" + explaination + '\'' +
                '}';
    }
}
