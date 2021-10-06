package com.example.produitapi.config.jwt;

import com.auth0.jwt.JWT;
import static com.example.produitapi.config.SecurityConstants.*;

import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.produitapi.models.entity.Role;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Component
public class JwtTokenProvider {

    private final UserDetailsService service;

    public JwtTokenProvider(UserDetailsService service) {
        this.service = service;
    }

    public String createToken(String username, List<String> roles){
        String token = JWT.create()
                .withSubject(username)
                .withExpiresAt(new Date( System.currentTimeMillis() + EXPIRATION_TIME))
                .withClaim("roles", roles)
                .sign(Algorithm.HMAC512(JWT_KEY));

        return TOKEN_PREFIX + token;
    }

    public String resolveToken(HttpServletRequest request){
        String bearer = request.getHeader(HEADER_KEY);
        if(bearer != null && bearer.startsWith(TOKEN_PREFIX)){
            return bearer.substring( TOKEN_PREFIX.length() );
        }

        return null;
    }

    public boolean validateToken(String token){
        try {
            String username = JWT.require( Algorithm.HMAC512(JWT_KEY) )
                    .build()
                    .verify( token.replace(TOKEN_PREFIX, "") )
                    .getSubject();

            return username != null;
        }
        catch ( JWTVerificationException ex){
            return false;
        }


    }
    public String getUsername(String token) {
        return JWT.decode(token).getSubject();
    }

    public Authentication getAuthentication(String token){
        UserDetails userDetails = service.loadUserByUsername( getUsername(token) );
        return new UsernamePasswordAuthenticationToken(userDetails.getUsername(),null, userDetails.getAuthorities());
    }
}
