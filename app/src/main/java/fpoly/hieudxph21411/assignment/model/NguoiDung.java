package fpoly.hieudxph21411.assignment.model;

public class NguoiDung {
    private int id;
    private String userName;
    private String passWord;
    private String name;

    public NguoiDung() {
    }

    public NguoiDung(int id, String userName, String passWord, String name) {
        this.id = id;
        this.userName = userName;
        this.passWord = passWord;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
