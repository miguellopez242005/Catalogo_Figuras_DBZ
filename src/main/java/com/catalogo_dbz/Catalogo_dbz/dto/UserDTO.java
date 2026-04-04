package com.catalogo_dbz.Catalogo_dbz.dto;

import lombok.Data;

@Data
public class UserDTO {
    private Integer idUser;
    private String name;
    private String email;
    private String phone;
    private String roleName;
    private String token;
}