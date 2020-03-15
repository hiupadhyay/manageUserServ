package com.user.manage.auth.filters;

import com.user.manage.controller.UserController;
import com.user.manage.dblayer.model.User;
import com.user.manage.dblayer.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthFilter extends OncePerRequestFilter {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private  UserRepository repo;


    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String app_id = httpServletRequest.getHeader("app_id");
        String auth_token = httpServletRequest.getHeader("auth_token");
        User user = repo.userExists(app_id, auth_token);
        logger.info("" + user);
        if (user == null) {
            httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token invalid");
        } else {
            final UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(user, null, null);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            filterChain.doFilter(httpServletRequest, httpServletResponse);
        }

    }



}
