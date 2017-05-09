package nl.ipsenh.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import nl.ipsenh.View;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author Jamie Kalloe
 * @version 1.0
 * @since 2017-05-03
 */
public class CourseRestriction {

    @NotEmpty @JsonView(View.Public.class) private String courseCode;

    @JsonView(View.Public.class) private String restrictionType;

    @JsonCreator
    /**
     * @param coursecode: contains course code
     * @param restrictionType: type of restriction
     */ public CourseRestriction(@JsonProperty("courseCode") String courseCode,
        @JsonProperty("restrictionType") String restrictionType) {
        this.courseCode = courseCode;
        this.restrictionType = restrictionType;
    }

    /**
     * @return String of restriction type
     */
    public String getRestrictionType() {
        return restrictionType;
    }
}
