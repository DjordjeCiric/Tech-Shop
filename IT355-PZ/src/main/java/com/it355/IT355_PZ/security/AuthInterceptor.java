package com.it355.IT355_PZ.security;

import com.it355.IT355_PZ.entity.Korisnik;
import com.it355.IT355_PZ.repository.KorisnikRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import java.util.Optional;

import static com.it355.IT355_PZ.tool.JwtDecoder.getSubject;

@Component
@Slf4j
@RequiredArgsConstructor
public class AuthInterceptor implements HandlerInterceptor{
    private final KorisnikRepository korisnikRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("REQUEST :: {}, METHOD :: {}", request.getRequestURI(), request.getMethod());
        if (request.getRequestURI().contains("/public")) {
            return HandlerInterceptor.super.preHandle(request, response, handler);
        }
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            Optional<Korisnik> optionalKorisnik = korisnikRepository.findByEmail(getSubject(token));
            if (optionalKorisnik.isEmpty()) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return false;
            }
            request.setAttribute("USER", optionalKorisnik.get());
            return HandlerInterceptor.super.preHandle(request, response, handler);
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
    }
}
