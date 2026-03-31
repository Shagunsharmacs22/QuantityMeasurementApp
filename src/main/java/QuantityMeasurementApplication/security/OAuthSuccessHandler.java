package QuantityMeasurementApplication.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class OAuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {

        OAuth2User user = (OAuth2User) authentication.getPrincipal();

        String email = user.getAttribute("email");

        System.out.println("🔥 LOGIN SUCCESS");
        System.out.println("EMAIL: " + email);

        // ✅ REAL JWT TOKEN generate karo
        String token = jwtUtil.generateToken(email);

        // ✅ FRONTEND pe redirect karo (IMPORTANT)
        response.sendRedirect("http://127.0.0.1:5500/index.html?token=" + token);
    }
}