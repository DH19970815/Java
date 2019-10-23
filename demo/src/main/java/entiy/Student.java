package entiy;

import lombok.Data;

import java.io.Serializable;

@Data
public class Student implements Serializable {
    private String name;
    private int age;
    private String sex;

    public Student(String name,int age,String sex){
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Student{}";
    }
}
