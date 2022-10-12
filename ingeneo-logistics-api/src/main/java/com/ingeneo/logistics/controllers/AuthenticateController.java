package com.ingeneo.logistics.controllers;

import com.ingeneo.logistics.dto.AuthenticationRequest;
import com.ingeneo.logistics.services.Impl.CustomUserDetailsServiceImpl;
import com.ingeneo.logistics.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/authenticate")
@RequiredArgsConstructor
public class AuthenticateController {
    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsServiceImpl customUserDetails;
    private final JwtUtil jwtUtil;

    @PostMapping
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest authenticationRequest, HttpServletRequest request) throws InvalidKeySpecException, NoSuchAlgorithmException {

        final Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authenticationRequest.getUsername(), authenticationRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        final UserDetails user = customUserDetails.loadUserByUsername(authenticationRequest.getUsername());
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));
        String URL = request.getRequestURL().toString();
        String access_token = jwtUtil.createToken(claims, user.getUsername(), URL);
        Map<String, String> tokens = new HashMap<String, String>();
        tokens.put("access_token", access_token);
        return ResponseEntity.ok(tokens);
    }
}
