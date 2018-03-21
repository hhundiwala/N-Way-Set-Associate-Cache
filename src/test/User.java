package test;

public class User {


    // this class is used only for testing purpose
    // this class has no application in cache
    int id;
    String fname;
    String lname;

    public User(int id, String fname, String lname) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                '}';
    }
}
