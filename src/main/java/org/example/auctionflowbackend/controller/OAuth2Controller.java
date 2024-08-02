package org.example.auctionflowbackend.controller;

import org.example.auctionflowbackend.entity.User;
import org.example.auctionflowbackend.service.TokenService;
import org.example.auctionflowbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
@RestController
@RequestMapping("/oauth2")
public class OAuth2Controller {

    @GetMapping("/loginSuccess")
    public Map<String, Object> loginSuccess(@AuthenticationPrincipal OAuth2User oauth2User) {
        return Map.of(
                "name", oauth2User.getAttribute("nickname"),
                "email", oauth2User.getAttribute("email")
        );
    }

    @GetMapping("/loginFailure")
    public Map<String, String> loginFailure() {
        return Map.of("error", "Login failed");
    }
}