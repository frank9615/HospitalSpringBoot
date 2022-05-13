package com.example.HospitalSpringBoot.entities;

import com.example.HospitalSpringBoot.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
        name="USER_TYPE",
        discriminatorType=DiscriminatorType.STRING
)
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;

    @Column(unique = true)
    @NotNull
    @Basic
    private String username;

    @Basic
    @NotNull
    private String password;

    @Basic
    @NotNull
    private String name;

    @Basic
    @NotNull
    private String surname;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Role role;

}
