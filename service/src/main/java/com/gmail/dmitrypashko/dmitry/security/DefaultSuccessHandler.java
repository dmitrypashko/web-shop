package com.gmail.dmitrypashko.dmitry.security;

import com.gmail.dmitrypashko.dmitry.model.Role;
import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@Service
public class DefaultSuccessHandler implements AuthenticationSuccessHandler {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    private static final Logger logger = Logger.getLogger(DefaultSuccessHandler.class);

    private String determineTargetUrl(Authentication authentication) {
        boolean isUser = false;
        boolean isAdmin = false;
        boolean isSuperAdmin = false;
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (GrantedAuthority grantedAuthority : authorities) {
            if (grantedAuthority.getAuthority().equals(Role.ROLE_USER.name())) {
                isUser = true;
                break;
            } else if (grantedAuthority.getAuthority().equals(Role.ROLE_ADMIN.name())) {
                isAdmin = true;
                break;
            } else if (grantedAuthority.getAuthority().equals(Role.ROLE_SUPER_ADMIN.name())) {
                isSuperAdmin = true;
                break;
            }
        }
        if (isUser) {
            return "/user/news/page/1";
        } else if (isAdmin) {
            return "/admin/news/page/1";
        } else if (isSuperAdmin) {
            return "/admin/news/page/1";
        } else {
            throw new IllegalStateException();
        }
    }

    private void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        String targetUrl = determineTargetUrl(authentication);
        if (response.isCommitted()) {
            logger.debug("Response committed.Unable to redirect to" + targetUrl);
            return;
        }
        redirectStrategy.sendRedirect(request, response, targetUrl);
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        handle(request, response, authentication);
    }

}
