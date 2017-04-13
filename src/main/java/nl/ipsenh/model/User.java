package nl.ipsenh.model;

import com.fasterxml.jackson.annotation.JsonView;
import nl.ipsenh.View;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import java.sql.Date;

/**
 * Created by Jamie on 13-4-2017.
 */
public class User {

    private long id;

    @Email
    @NotEmpty
    private String email;

    @NotEmpty
    @JsonView(View.Private.class)
    private String password;

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    private String insertion;

    @NotEmpty
    private Date dateOfBirth;

    @NotEmpty
    private String role;

    public User() {}

    public User(long id, String email, String password, String firstName, String insertion, String lastName,
                Date dateOfBirth, String role) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.insertion = insertion;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getInsertion() {
        return insertion;
    }

    public void setInsertion(String insertion) {
        this.insertion = insertion;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
