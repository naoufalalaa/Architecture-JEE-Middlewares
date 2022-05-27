package alaa.naoufal.alaaexam.security.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity // This tells Hibernate to make a table out of this class
@Data @AllArgsConstructor @NoArgsConstructor
public class AppUser {
    @Id
    private String userID;
    @Column(unique = true,length = 20)
    private String username;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // This tells Hibernate to not serialize this field
    private String password;
    private Boolean active;
    @ManyToMany(fetch = FetchType.EAGER) // This tells Hibernate to fetch the roles as well
    private List<AppRole> appRoles=new ArrayList<>();// This is the list of roles for this user

}
