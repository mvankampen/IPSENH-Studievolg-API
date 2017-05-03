package nl.ipsenh.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import nl.ipsenh.View;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by Jamie on 3-5-2017.
 */
public class Restriction {

    @NotEmpty
    @JsonView(View.Public.class)
    private String courseCode;

    @JsonView(View.Public.class)
    private String requirement;

    @JsonCreator
    public Restriction(@JsonProperty("courseCode") String courseCode, @JsonProperty("requirement") String requirement) {
        this.courseCode = courseCode;
        this.requirement = requirement;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getRequirement() {
        return requirement;
    }
}
