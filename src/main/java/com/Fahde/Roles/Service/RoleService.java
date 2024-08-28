package com.Fahde.Roles.Service;

import com.Fahde.Admin.Admin;
import com.Fahde.Admin.AdminRepository;
import com.Fahde.Roles.Request.RoleRequest;
import com.Fahde.Roles.Respones.RoleResponse;
import com.Fahde.config.JwtService;
import com.Fahde.Roles.Entity.Role;
import com.Fahde.Roles.Repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class RoleService {
    private final RoleRepository roleRepository;
    private final JwtService jwtService;
    private final AdminRepository adminRepository;

    public ResponseEntity<RoleResponse> addRole(RoleRequest roleRequest, String authorizationHeader) {

        String token =authorizationHeader.replace("Bearer ", "");
        Integer adminId=jwtService.extractId(token);

        Admin admin=adminRepository.findById(adminId).orElseThrow(()-> new RuntimeException("this ID Admin Not Found"));

        Role role= Role.builder()
                .name(roleRequest.name())
                .admin(admin)
                .build();

        roleRepository.save(role);
        RoleResponse roleResponse=RoleResponse.builder()
                .name(role.getName())
                .adminName(role.getAdmin().getFirstname() + " "+ role.getAdmin().getLastname())
                .build();
        return new ResponseEntity(roleResponse ,HttpStatus.ACCEPTED);
    }
}
