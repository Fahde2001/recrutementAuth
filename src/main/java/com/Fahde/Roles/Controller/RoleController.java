package com.Fahde.Roles.Controller;

import com.Fahde.Roles.Request.RoleRequest;
import com.Fahde.Roles.Respones.RoleResponse;
import com.Fahde.Roles.Service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @PostMapping
    public ResponseEntity<RoleResponse> addRole(@RequestBody RoleRequest roleRequest, @RequestHeader("Authorization") String authorizationHeader) {
        return roleService.addRole(roleRequest, authorizationHeader);
    }
}
