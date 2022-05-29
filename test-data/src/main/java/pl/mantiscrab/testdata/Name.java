package pl.mantiscrab.testdata;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Name {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int number;
    private String gender;

    public Name() {
    }

    @Override
    public String toString() {
        return "Name{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", number=" + number +
                ", gender='" + gender + '\'' +
                '}';
    }
}
