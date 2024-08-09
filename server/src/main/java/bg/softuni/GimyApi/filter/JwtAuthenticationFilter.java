package bg.softuni.GimyApi.filter;

import bg.softuni.GimyApi.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    public JwtAuthenticationFilter(JwtUtil jwtUtil, UserDetailsService userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Authorization: "Bearer nwgoang810awn12an02@1Aw"
        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);

        System.out.println("Header: " + header);

        if (header == null || !header.startsWith("Bearer ")) {
            response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
            response.setHeader("Access-Control-Allow-Credentials", "true");
            response.setHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, PATCH");
            response.setHeader("Access-Control-Max-Age", "3600");
            response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With");

            filterChain.doFilter(request, response);
            return;
        }

        final String token = header.split("\\s+")[1];
        final String email = jwtUtil.extractEmail(token);

        System.out.println("Token: " + token);
        System.out.println("Email: " + email);

        // If there is such user and he hasn't authenticated yet
        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // Get user identity and set it to the spring security context
            UserDetails userDetails = userDetailsService.loadUserByUsername(email);

            // Validate the JWT Token
            if (!jwtUtil.isTokenValid(token, userDetails)) {
                System.out.println("Invalid JWT token!");
                filterChain.doFilter(request, response);
                return;
            }

            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    userDetails,
                    null,
                    userDetails.getAuthorities()
            );

            authentication.setDetails(
                    new WebAuthenticationDetailsSource().buildDetails(request)
            );

            // This is where authentication happens and user is now valid
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }
}
