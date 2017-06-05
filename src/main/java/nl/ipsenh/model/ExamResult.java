package nl.ipsenh.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import nl.ipsenh.View;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import java.sql.Date;

/**
 * Created by Jamie on 23-5-2017.
 */
public class ExamResult {

    @NotEmpty
    @Length(max = 255)
    @JsonView(View.Public.class)
    private String name;

    @NotEmpty
    @Length(min = 3, max = 255)
    @JsonView(View.Public.class)
    private String courseCode;

    @NotEmpty
    @JsonView(View.Public.class)
    private Date date;

    @NotEmpty
    @JsonView(View.Public.class)
    private Date mutationDate;

    @NotEmpty
    @JsonView(View.Public.class)
    @Length(min = 1, max = 10)
    private double result;

    @NotEmpty
    @Length(min = 7, max = 255)
    @JsonView(View.Public.class)
    private String userEmail;

    public ExamResult(@JsonProperty(":name") String name, @JsonProperty("courseCode") String courseCode,
                      @JsonProperty("exam_date") Date date, @JsonProperty("exam_mutation_date") Date mutationDate,
                      @JsonProperty("result") double result, @JsonProperty("userEmail") String userEmail) {
        this.name = name;
        this.courseCode = courseCode;
        this.date = date;
        this.mutationDate = mutationDate;
        this.result = result;
        this.userEmail = userEmail;
    }

    public String getName() {
        return name;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public double getResult() {
        return result;
    }
}
