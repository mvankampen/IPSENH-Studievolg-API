package nl.ipsenh.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import nl.ipsenh.View;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import java.sql.Date;

/**
 * Created by Jamie on 3-5-2017.
 */
public class Course {

    @NotEmpty @Length(min = 3, max = 255) @JsonView(View.Public.class) private String code;

    @JsonView(View.Public.class) private String description;

    @NotEmpty @JsonView(View.Public.class) private Date startDate;

    @NotEmpty @JsonView(View.Public.class) private Date endDate;

    @JsonCreator public Course(@JsonProperty("code") String code,
        @JsonProperty("description") String description, @JsonProperty("startDate") Date startDate,
        @JsonProperty("endDate") Date endDate) {
        this.code = code;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }
}
