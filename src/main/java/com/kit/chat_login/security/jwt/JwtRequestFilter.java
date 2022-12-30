package com.kit.chat_login.security.jwt;

import com.kit.chat_login.model.StatusModel;
import com.kit.chat_login.model.token.Token;
import com.kit.chat_login.repository.token.TokenRepository;
import com.kit.chat_login.security.jwt.userdetail.UserDetailsImp;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Component
@Setter
@Getter
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil = new JwtUtil();

    public JwtRequestFilter(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    public JwtRequestFilter() {
    }

    private TokenRepository tokenRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authorizationHeader = request.getHeader("Authorization");
        UserDetailsImp user = null;
        Token token = null;
        if (StringUtils.hasText(authorizationHeader) && authorizationHeader.startsWith("Token ")) {
            String jwt = authorizationHeader.substring(6);
            user = jwtUtil.getUserFromToken(jwt);
            token = tokenRepository.findByToken(jwt);
        }
        if (user != null && token != null && token.getToken_exp().after(new Date()) && token.getStatus() == StatusModel.ACTIVE) {
            Set<GrantedAuthority> authorities = new HashSet<>();
            for (Object role : user.getAuthorities().toArray()) {
                authorities.add(new SimpleGrantedAuthority(role.toString()));
            }
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(user, null, authorities);
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }
}
