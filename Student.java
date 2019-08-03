package BigWork1;

public class Student {
    private long id;
    private String name;
    private String gender;
    private int age;
    private String birth;

    public Student() {
    }

    public Student(long id, String name, String gender, int age, String birth) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.birth = birth;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }
}
