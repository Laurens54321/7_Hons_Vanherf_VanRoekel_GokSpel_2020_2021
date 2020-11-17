package model;

public class Player {
    private String firstName;
    private String lastName;
    private String userid;
    private int money;

    public void Player() { }

    public Player(String firstName, String lastName, String userid, int money) {
        setFirstName(firstName);
        setLastName(lastName);
        setUserid(userid);
        setMoney(money);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (firstName.isEmpty() || firstName == null) throw new DomainException("firstName cannot be empty");
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (lastName.isEmpty() || lastName == null) throw new DomainException("lastName cannot be empty");
        this.lastName = lastName;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        if (userid.isEmpty() || userid == null) throw new DomainException("userid cannot be empty");
        this.userid = userid;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        if (money < 0) throw new DomainException("money cannot be negative");
        this.money = money;
    }

    public void addMoney(int money) {
        this.money += money;
    }
}
