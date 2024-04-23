package com.example.usermicroservice.adapters.Service;

import com.example.usermicroservice.adapters.driven.jpa.mysql.entity.RolEntity;
import com.example.usermicroservice.adapters.driven.jpa.mysql.entity.UserEntity;
import com.example.usermicroservice.adapters.driven.jpa.mysql.exceptions.PermissionDeniedCreateUserException;
import com.example.usermicroservice.adapters.driven.jpa.mysql.repository.IRolRepository;
import com.example.usermicroservice.adapters.driven.jpa.mysql.repository.IUserRepository;
import com.example.usermicroservice.adapters.driving.http.dto.request.AddUserRequest;
import com.example.usermicroservice.adapters.driving.http.dto.request.AuthLoginRequest;
import com.example.usermicroservice.adapters.driving.http.dto.response.AuthResponse;
import com.example.usermicroservice.adapters.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IRolRepository rolRepository;


    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private  IUserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRol().getName().toUpperCase()));

        authorities.add(new SimpleGrantedAuthority("WRITE"));

        return new User(user.getEmail(),
                user.getPassword(),
                user.getIsEnabled(),
                user.getIsAccountNonExpired(),
                user.getIsCredentialsNonExpired(),
                user.getIsAccountNonLocked(),
                authorities);
    }

    public AuthResponse loginUser(AuthLoginRequest authLoginRequest){
        String username = authLoginRequest.getUsername();
        String password = authLoginRequest.getPassword();

        Authentication authentication = this.authenticate(username, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String accessToken = jwtUtils.createToken(authentication);

        AuthResponse authResponse = new AuthResponse(username, "User logged successfully", accessToken,true);

        return authResponse;


    }

    public Authentication authenticate(String username, String password){
        UserDetails userDetails = loadUserByUsername(username);
        if (userDetails == null){
            throw new BadCredentialsException("Invalid username or password");
        }

        if (!passwordEncoder.matches(password, userDetails.getPassword())){
            throw new BadCredentialsException("Invalid username or password");
        }

        return new UsernamePasswordAuthenticationToken(username,userDetails.getPassword(), userDetails.getAuthorities());


    }

    public void ValidateUser(Long rolId) {
        RolEntity rolEntity = rolRepository.findById(rolId)
                .orElseThrow(() -> new RuntimeException("Rol not found"));

        String rol = rolEntity.getName().toUpperCase();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = loadUserByUsername((String) authentication.getPrincipal());
        boolean isAdmin = userDetails.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"));
        boolean isTutor = userDetails.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_TUTOR"));
        if (!isAdmin && !isTutor) {
            throw new PermissionDeniedCreateUserException();
        } else if (isTutor && (rol.equals("ADMIN") || rol.equals("TUTOR"))) {
            throw new PermissionDeniedCreateUserException();
        }
    }

    public AuthResponse createUser(AddUserRequest addUserRequest){
        RolEntity newRolEntity = rolRepository.findById(addUserRequest.getRolId())
                .orElseThrow(() -> new RuntimeException("Rol not found"));


        // Crear la nueva cuenta
        UserEntity user = new UserEntity();
        user.setEmail(addUserRequest.getEmail());
        user.setPassword(passwordEncoder.encode(addUserRequest.getPassword()));
        user.setIsEnabled(true);
        user.setIsAccountNonExpired(true);
        user.setIsCredentialsNonExpired(true);
        user.setIsAccountNonLocked(true);
        user.setRol(newRolEntity);
        UserEntity userEntity = userRepository.save(user);

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + userEntity.getRol().getName().toUpperCase()));

        Authentication newAuthentication = new UsernamePasswordAuthenticationToken(userEntity.getEmail(),
                userEntity.getPassword(), authorities);

        String accessToken = jwtUtils.createToken(newAuthentication);

        AuthResponse authResponse = new AuthResponse(userEntity.getEmail(), "User created successfully", accessToken,true);

        return authResponse;
    }
}
