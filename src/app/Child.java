package app;

public class Child {
    private static int idCounter = 1;

    private int id;
    private String name;
    private String lastName;
    private int age;
    private String gender;

    public Child(String name, String lastName, int age, String gender) {
        this.id = idCounter++;
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

}
