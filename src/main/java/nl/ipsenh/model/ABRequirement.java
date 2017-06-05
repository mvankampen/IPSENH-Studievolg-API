package nl.ipsenh.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import nl.ipsenh.View;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by Lorenzo Jokhan on 05/05/2017.
 */
public class ABRequirement {

    @NotEmpty @JsonView(View.Public.class) private String course;

    @JsonView(View.Public.class) private String requiredCourse;

    @JsonCreator public ABRequirement(@JsonProperty("course") String course,
        @JsonProperty("requiredCourse") String requiredCourse) {
        this.course = course;
        this.requiredCourse = requiredCourse;
    }

    public String getCourse() {
        return course;
    }

    public String getRequiredCourse() {
        return requiredCourse;
    }
}
