package com.fpt.hava.web.api.user;


import com.fpt.hava.hava_manager.security.JwtUtils;
import com.fpt.hava.hava_manager.user.domain.LoginRequest;
import com.fpt.hava.hava_manager.user.domain.UserEntity;
import com.fpt.hava.hava_manager.user.repository.UserRepository;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class AuthController {

  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UserRepository userRepository;

  @Autowired
  PasswordEncoder passwordEncoder;

  @Autowired
  JwtUtils jwtUtils;

  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);

    return ResponseEntity.ok(jwt);
  }

  @PostMapping("/signup")
  public ResponseEntity<?> authenticateUsers() {
    UserEntity userEntity = new UserEntity();
    userEntity.setName("ok");
    userEntity.setEmail("siunhanPu@gmail.com");
    String s = "admin";
    userEntity.setPassword(passwordEncoder.encode(s));
    userEntity.setPhone("");
    userEntity.setAddress("");
    userEntity.setAvata("");


    LocalDate sss = LocalDate.now();

    userEntity.setBirthday(Date.valueOf(sss));
    userEntity.setSex((short) 0);
    userEntity.setLastSession("");
    userEntity.setFbId(0);

    userRepository.save(userEntity);

    return ResponseEntity.ok(userEntity);
  }

}
