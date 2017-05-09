package nl.ipsenh.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import nl.ipsenh.View;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import java.security.Principal;
import java.sql.Date;

/**
 * Created by Jamie on 13-4-2017.
 */
public class User implements Principal {

    @Email @NotEmpty @Length(min = 3, max = 255) @JsonView(View.Public.class) private String email;

    @NotEmpty @JsonView(View.Private.class) private String password;

    @NotEmpty @Length(max = 255) private String firstName;

    @NotEmpty @Length(max = 255) private String lastName;

    private String insertion;

    @NotEmpty private Date dateOfBirth;

    @NotEmpty @JsonView(View.Private.class) private String role;

    public User() {
    }

    @JsonCreator
    public User(@JsonProperty("email") String email, @JsonProperty("password") String password,
        @JsonProperty("firstName") String firstName, @JsonProperty("insertion") String insertion,
        @JsonProperty("lastName") String lastName, @JsonProperty("dateOfBirth") Date dateOfBirth,
        @JsonProperty("role") String role) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.insertion = insertion;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getInsertion() {
        return insertion;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public String getRole() {
        return role;
    }

    public boolean hasRole(String requiredRole) {
        return this.role.equals(requiredRole);
    }

    @Override public String getName() {
        return this.email;
    }
}
