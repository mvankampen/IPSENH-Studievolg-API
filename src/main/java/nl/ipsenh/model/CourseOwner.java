package nl.ipsenh.model;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import nl.ipsenh.View;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author Michael van Kampen
 * @version 1.0
 * @sine 2017-05-08
 */
public class CourseOwner {

    @NotEmpty @Length(min = 3, max = 255) @JsonView(View.Public.class) private String courseCode;

    @Email @NotEmpty @Length(min = 3, max = 255) @JsonView(View.Public.class) private String
        courseLeader;

    /**
     * @param courseCode   of Course
     * @param courseLeader of Course
     */
    @JsonCreator public CourseOwner(@JsonProperty("courseLeader") String courseLeader,
        @JsonProperty("courseCode") String courseCode) {
        this.courseCode = courseCode;
        this.courseLeader = courseLeader;
    }

    /**
     * @return code of course
     */
    public String getCourseCode() {
        return this.courseCode;
    }

    /**
     * @return email address of course leader
     */
    public String getCourseLeader() {
        return this.courseLeader;
    }

    @Override public boolean equals(Object obj) {
        return this.courseLeader.equals(((CourseOwner) obj).getCourseLeader());
    }
}
