package com.adesp.festival.authentication.infrastructure.configuration;

import com.adesp.festival.authentication.domain.entities.User;
import com.adesp.festival.authentication.domain.repositories.UserRepository;
import com.adesp.festival.authentication.infrastructure.providers.JWTProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private JWTProvider jwtProvider;

    @Autowired
    private UserRepository userRepository;

    private Collection<? extends GrantedAuthority> getSubjectAuthorities(Long id){
        User user = this.userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User Not Found!"));

        return user.getAuthorities();
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        SecurityContextHolder.getContext().setAuthentication(null);

        String accessToken = request.getHeader("Authorization");

        if(accessToken != null && !accessToken.isEmpty()){
            String subject = this.jwtProvider.validateAccessToken(accessToken);

            if(subject.isEmpty()){
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
            }

            request.setAttribute("subject", subject);

            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(subject, null, this.getSubjectAuthorities(Long.parseLong(subject)));

            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }

        filterChain.doFilter(request, response);
    }
}
