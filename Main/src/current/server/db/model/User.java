package current.server.db.model;

public class User {
    private long id;
    private String login;
    private String pass;
    private String nick;

    public User() {
    }

    public User(String login, String pass, String nick) {
        this(0, login, pass, nick);
    }

    public User(long id, String login, String pass, String nick) {
        this.id = id;
        this.login = login;
        this.pass = pass;
        this.nick = nick;
    }

    public long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPass() {
        return pass;
    }

    public String getNick() {
        return nick;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", pass='" + pass + '\'' +
                ", nick='" + nick + '\'' +
                '}';
    }
}
