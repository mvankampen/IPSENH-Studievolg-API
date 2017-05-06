package nl.ipsenh.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import nl.ipsenh.View;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by Lorenzo Jokhan on 06/05/2017.
 */
public class EnrolledCourse {
  @NotEmpty
  @JsonView(View.Public.class)
  private String course;

  @JsonView(View.Public.class)
  private String email;

  @JsonCreator
  public EnrolledCourse(@JsonProperty("course") String course, @JsonProperty("user_email") String email) {
    this.course = course;
    this.email = email;
  }

  public String getCourse() {
    return course;
  }

  public String getEmail() {
    return email;
  }
}
