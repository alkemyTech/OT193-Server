package com.alkemy.somosmas.enums;

public enum RoleEnum {
    ROLE_USER("USER",1L),ROLE_ADMIN("ADMIN",2L);
    private String roleName;
    private Long id;

    RoleEnum(String roleName, Long id){
        this.roleName = roleName;
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public Long getId() {
        return id;
    }
}
