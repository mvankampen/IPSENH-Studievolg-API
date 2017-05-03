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
    private String[] requirements;

    @JsonCreator
    public Restriction(@JsonProperty("courseCode") String courseCode, String[] requirements) {
        this.courseCode = courseCode;
        this.requirements = requirements;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String[] getRequirements() {
        return requirements;
    }
}
