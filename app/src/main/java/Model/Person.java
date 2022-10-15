package Model;

public class Person {
    int id;
    int age;
    String name;
    String lname;

    public Person() {

    }

    public Person(int age, String name, String lname) {
        this.age = age;
        this.name = name;
        this.lname = lname;
    }

    public Person(int id, int age, String name, String lname) {
        this.id = id;
        this.age = age;
        this.name = name;
        this.lname = lname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }


}
