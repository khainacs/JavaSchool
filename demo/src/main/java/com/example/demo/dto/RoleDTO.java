package com.example.demo.dto;

import com.example.demo.Enum.RoleName;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;

@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode
@Data
public class RoleDTO extends BaseObject{
    private String roleName;
}
