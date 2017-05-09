package nl.ipsenh.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import nl.ipsenh.View;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by Lorenzo Jokhan on 05/05/2017.
 */
public class CoursePassed {

    @NotEmpty @JsonView(View.Public.class) private String course;

    @JsonView(View.Public.class) private String user;

    @JsonCreator
    public CoursePassed(@JsonProperty("course") String course, @JsonProperty("user") String user) {
        this.course = course;
        this.user = user;
    }

    public String getCourse() {
        return course;
    }

    public String getUser() {
        return user;
    }

}
