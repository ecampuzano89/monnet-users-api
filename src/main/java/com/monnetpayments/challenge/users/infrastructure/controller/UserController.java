package com.monnetpayments.challenge.users.infrastructure.controller;

import com.monnetpayments.challenge.users.application.dto.UserDto;
import com.monnetpayments.challenge.users.application.service.UserService;
import com.monnetpayments.challenge.users.application.service.UserSyncService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Tag(name = "Users", description = "Operaciones sobre usuarios")
public class UserController {

    private final UserService userService;
    private final UserSyncService userSyncService;

    @Operation(summary = "Sincronizar usuarios", description = "Consume JSONPlaceholder y guarda los usuarios en H2.")
    @PostMapping("/sync")
    public ResponseEntity<Void> syncUsers() {
        userSyncService.syncUsers();
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Listar usuarios", description = "Devuelve todos los usuarios persistidos en H2.")
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(userService.findAll());
    }
}
