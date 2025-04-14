package LAB2;

public class Person {
    private String nick;
    private int age;

    public Person(String nick, int age) {
        this.nick = nick;
        this.age = age;
    }

    public String getNick() {
        return nick;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return nick + ", lat " + age;
    }
}