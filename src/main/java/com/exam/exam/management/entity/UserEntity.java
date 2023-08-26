package com.exam.exam.management.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "email")
        })
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String email;

    private String username;

    private String firstName;

    private String lastName;
    private String password;

    private String phoneNo;

    private boolean enabled= true;

    private String roles;
    private String profile;

//    user will have many roles
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER, mappedBy = "userEntity")
    @JsonIgnore
    private Set<UserRoleEntity> userRoleEntities = new HashSet<>();


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-mm-yyyy")
    private Date passwordUpdated;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-mm-yyyy")
    private Date createdAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-mm-yyyy")
    private Date updatedAt;


}
