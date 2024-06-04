package com.example.demo.dto;

import com.example.demo.entity.Role;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;


@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode
@Data

public class UserDTO extends BaseObject {

    private String id;

    private String uuid;

    private String lastName;

    private String firstName;

    private String email;

    private String password;

    private Set<Role> roles = new HashSet<>();

    String role;

}
