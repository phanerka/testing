package framework.model;
import java.util.Objects;


public class User {

    private String username;
    private String password;
    private String incorrectUsername;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String incorrectUsername) {
        this.incorrectUsername = incorrectUsername;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIncorrectUsername() {
        return incorrectUsername;
    }

    public void setIncorrectUsername(String incorrectUsername) {
        this.incorrectUsername = incorrectUsername;
    }


    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(getUsername(), user.getUsername()) &&
                Objects.equals(getPassword(), user.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getPassword());
    }
}
