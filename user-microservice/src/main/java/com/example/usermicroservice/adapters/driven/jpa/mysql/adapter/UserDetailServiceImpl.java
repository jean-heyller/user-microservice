package com.example.usermicroservice.adapters.driven.jpa.mysql.adapter;

import com.example.usermicroservice.adapters.driven.jpa.mysql.entity.RolEntity;
import com.example.usermicroservice.adapters.driven.jpa.mysql.entity.UserEntity;
import com.example.usermicroservice.adapters.driven.jpa.mysql.exceptions.BadCredentialsException;
import com.example.usermicroservice.adapters.driven.jpa.mysql.exceptions.DataNotFoundException;
import com.example.usermicroservice.adapters.driven.jpa.mysql.exceptions.PermissionDeniedCreateUserException;
import com.example.usermicroservice.adapters.driven.jpa.mysql.repository.IRolRepository;
import com.example.usermicroservice.adapters.driven.jpa.mysql.repository.IUserRepository;
import com.example.usermicroservice.adapters.driving.http.dto.request.AuthLoginRequest;
import com.example.usermicroservice.adapters.driving.http.dto.response.AuthResponse;
import com.example.usermicroservice.adapters.driving.http.util.AdapterConstants;
import com.example.usermicroservice.adapters.util.JwtUtils;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

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

    public static final String ROL_NOT_FOUND = "The role";

    private final PasswordEncoder passwordEncoder;
    private final IRolRepository rolRepository;
    private final JwtUtils jwtUtils;
    private final IUserRepository userRepository;

    public UserDetailServiceImpl(PasswordEncoder passwordEncoder, IRolRepository rolRepository, JwtUtils jwtUtils, IUserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.rolRepository = rolRepository;
        this.jwtUtils = jwtUtils;
        this.userRepository = userRepository;
    }




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

        return new AuthResponse(username, "User logged successfully", accessToken,true);
    }

    public Authentication authenticate(String username, String password){
        UserDetails userDetails = loadUserByUsername(username);
        if (userDetails == null){
            throw new BadCredentialsException();
        }

        if (!passwordEncoder.matches(password, userDetails.getPassword())){
            throw new BadCredentialsException();
        }

        return new UsernamePasswordAuthenticationToken(username,userDetails.getPassword(), userDetails.getAuthorities());


    }

    public void validateUser(Long rolId) {
        RolEntity rolEntity = rolRepository.findById(rolId)
                .orElseThrow(() -> new DataNotFoundException(ROL_NOT_FOUND));

        String rol = rolEntity.getName().toUpperCase();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = loadUserByUsername((String) authentication.getPrincipal());
        boolean isAdmin = userDetails.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(AdapterConstants.ROL_AMIN));
        boolean isTutor = userDetails.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(AdapterConstants.ROL_TUTOR));
        if ((!isAdmin && !isTutor) || (isTutor && (rol.equals("ADMIN") || rol.equals("TUTOR")))) {
            throw new PermissionDeniedCreateUserException();
        }
    }


}
