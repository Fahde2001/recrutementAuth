package com.Fahde.Roles.Respones;

import lombok.Builder;

@Builder
public record RoleResponse(String name,
                           String adminName) {
}
