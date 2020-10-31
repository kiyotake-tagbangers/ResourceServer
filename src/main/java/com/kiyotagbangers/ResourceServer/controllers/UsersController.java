package com.kiyotagbangers.ResourceServer.controllers;

import com.kiyotagbangers.ResourceServer.response.UserRest;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UsersController {

    @GetMapping("/status/check")
    public String status(){
        return "working...";
    }

    // @PreAuthorize("hasRole('developer')")
    // @PreAuthorize("hasAuthority('ROLE_developer') or #id == #jwt.subject")
    @PreAuthorize("#id == #jwt.subject")
    // @Secured("ROLE_developer")
    @DeleteMapping(path = "/{id}")
    public String deleteUser(@PathVariable String id, @AuthenticationPrincipal Jwt jwt){
        return "Deleted user with id " + id + " and JWT subject" + jwt.getSubject();
    }

    @PostAuthorize("returnObject.userId == #jwt.subject")
    @GetMapping(path = "/{id}")
    public UserRest getUser(@PathVariable String id, @AuthenticationPrincipal Jwt jwt){
        return new UserRest("Yamada", "Taro", "80b6edcf-2901-4924-9180-8c6d29ffdab0");
    }
}
