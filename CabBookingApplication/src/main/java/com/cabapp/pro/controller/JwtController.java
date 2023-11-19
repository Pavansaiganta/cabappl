package com.cabapp.pro.controller;

import com.cabapp.pro.dto.LoginDto;
import com.cabapp.pro.jwt.JwtResponse;
import com.cabapp.pro.jwt.JwtTokenUtil;
import com.cabapp.pro.jwt.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("jwt")
public class JwtController
{


    @Autowired
    private MyUserDetailService userService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private AuthenticationManager authenticationManager;




    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticateToken(@RequestBody LoginDto user) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    user.getUsername(),user.getPassword()));
        }
        catch(BadCredentialsException e) {
            throw new Exception("Incorrect password");
        }

        final UserDetails userDetails = userService.loadUserByUsername(user.getUsername());
        final String jwt = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(jwt));
    }
    
    

}
