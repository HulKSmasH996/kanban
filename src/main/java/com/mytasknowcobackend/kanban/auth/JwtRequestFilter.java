package com.mytasknowcobackend.kanban.auth;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mytasknowcobackend.kanban.auth.roles.RoleConstants;
import com.mytasknowcobackend.kanban.auth.roles.RoleService;
import com.mytasknowcobackend.kanban.model.Credentials;
import com.mytasknowcobackend.kanban.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;

import io.jsonwebtoken.ExpiredJwtException;
import com.mytasknowcobackend.kanban.model.Credentials.CredentialType;
import com.mytasknowcobackend.kanban.model.SecurityProperties;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private SecurityService securityService;

    @Autowired
    private SecurityProperties securityProps;

    @Autowired
    private CookieService cookieUtils;

    @Autowired
    RoleService securityRoleService;

    @Autowired
    private FirebaseAuth firebaseAuth;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        authorize(request);
        filterChain.doFilter(request, response);
    }

    private void authorize(HttpServletRequest request) {
        String sessionCookieValue = null;
        FirebaseToken decodedToken = null;
        CredentialType type = null;
        // Token verification
        boolean strictServerSessionEnabled = securityProps.getFirebaseProps().isEnableStrictServerSession();
        Cookie sessionCookie = cookieUtils.getCookie("session");
        String token = securityService.getBearerToken(request);
        try {
            if (sessionCookie != null) {
                sessionCookieValue = sessionCookie.getValue();
                decodedToken = firebaseAuth.verifySessionCookie(sessionCookieValue,
                        securityProps.getFirebaseProps().isEnableCheckSessionRevoked());
                type = Credentials.CredentialType.SESSION;
            } else if (!strictServerSessionEnabled && token != null && !token.equals("null")
                    && !token.equalsIgnoreCase("undefined")) {
                decodedToken = firebaseAuth.verifyIdToken(token);
                type = Credentials.CredentialType.ID_TOKEN;
            }
        } catch (FirebaseAuthException e) {
            e.printStackTrace();
           // log.error("Firebase Exception:: ", e.getLocalizedMessage());
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        Users user = firebaseTokenToUserDto(decodedToken);
        // Handle roles
        if (user != null) {
            // Handle Super Role
            if (securityProps.getSuperAdmins().contains(user.getUserEmail())) {
                if (!decodedToken.getClaims().containsKey(RoleConstants.ROLE_SUPER)) {
                    try {
                        securityRoleService.addRole(decodedToken.getUid(), RoleConstants.ROLE_SUPER);
                    } catch (Exception e) {
                       // log.error("Super Role registeration expcetion ", e);
                        e.printStackTrace();
                    }
                }
                authorities.add(new SimpleGrantedAuthority(RoleConstants.ROLE_SUPER));
            }
            // Handle Other roles
            decodedToken.getClaims().forEach((k, v) -> authorities.add(new SimpleGrantedAuthority(k)));
            // Set security context
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user,
                    new Credentials(type, decodedToken, token, sessionCookieValue), authorities);
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
    }
    private Users firebaseTokenToUserDto(FirebaseToken decodedToken) {
        Users user = null;
        if (decodedToken != null) {
            user = new Users();
            user.setUserId(decodedToken.getUid());
            user.setUserName(decodedToken.getName());
            user.setUserEmail(decodedToken.getEmail());
        }
        return user;
    }

}