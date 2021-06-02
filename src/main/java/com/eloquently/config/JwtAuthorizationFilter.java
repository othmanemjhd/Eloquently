package com.eloquently.config;

import com.eloquently.security.JwtTokenProvider;
import com.eloquently.dao.AbonneeDao;
import com.eloquently.dao.CreateurDao;
import com.eloquently.model.Abonnee;
import com.eloquently.model.Createur;
import com.eloquently.model.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
    public static final String HEADER_STRING = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";

    @Autowired
    private CreateurDao createurDao;
    @Autowired
    private AbonneeDao abonneeDao;

    @Autowired
    private JwtTokenProvider tokenProvider;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        String header = request.getHeader(HEADER_STRING);
        if (header == null || !header.startsWith(TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }
        Authentication authentication = getUsernamePasswordAuthentication(request);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }

    private Authentication getUsernamePasswordAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING)
                .replace(TOKEN_PREFIX, "");

        if (StringUtils.hasText(token) && tokenProvider.validateToken(token)) {
            String email = tokenProvider.getUserNameFromJWT(token);

            if (email != null) {
                    Createur user = createurDao.findByEmail(email);
                if (user!=null) {
                    UserPrincipal principal = new UserPrincipal(user);
                    return new UsernamePasswordAuthenticationToken(principal, null, principal.getAuthorities());
                }
               Abonnee abonnee = abonneeDao.findByEmail(email);
                if (abonnee!=null) {
                    UserPrincipal principal = new UserPrincipal(user);
                    return new UsernamePasswordAuthenticationToken(principal, null, principal.getAuthorities());
                }
            }
            return null;
        }
        return null;
    }
}
