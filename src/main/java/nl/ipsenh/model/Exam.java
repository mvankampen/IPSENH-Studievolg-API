package nl.ipsenh.model;

import com.fasterxml.jackson.annotation.JsonView;
import nl.ipsenh.View;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by Jamie on 23-5-2017.
 */
public class Exam {

    @NotEmpty @Length(max = 255) @JsonView(View.Public.class) private String name;

    @NotEmpty @Length(max = 100) @JsonView(View.Public.class) private int weight;

    @NotEmpty @Length(min = 3, max = 255) @JsonView(View.Public.class) private String courseCode;

    public Exam() {
    }

    public Exam(String name, int weight, String courseCode) {
        this.name = name;
        this.weight = weight;
        this.courseCode = courseCode;
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }

    public String getCourseCode() {
        return courseCode;
    }
}
