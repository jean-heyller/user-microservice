package com.example.usermicroservice.adapters.driving.http.util;

import java.util.Arrays;
import java.util.List;

public enum Rol {

    ADMIN(Arrays.asList("OWNER","RESTAURANT")),
    OWNER(Arrays.asList("EMPLOYEE")),
    GUEST(Arrays.asList("READ"));

    private List<String> permisos;

    Rol(List<String> permisos) {
        this.permisos = permisos;
    }

    public List<String> getPermissions() {
        return permisos;
    }

    public boolean allowed(String permiso) {
        return permisos.contains(permiso);
    }
}
