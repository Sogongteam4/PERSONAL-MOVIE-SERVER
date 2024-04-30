package kgu.softwareEG.personalMovie.global.auth.handelr;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kgu.softwareEG.personalMovie.global.auth.OAuthPrincipalDetails;
import kgu.softwareEG.personalMovie.global.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static kgu.softwareEG.personalMovie.global.util.JwtUtil.Authorization;


@Component
@RequiredArgsConstructor
@Slf4j
public class OAuthAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JwtUtil jwtUtil;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        OAuthPrincipalDetails principal = (OAuthPrincipalDetails) authentication.getPrincipal();
        String principalsocialId = principal.getSocialId();
        String accessToken = jwtUtil.createAccessToken(principalsocialId);
        response.addHeader(Authorization, accessToken);
        log.info(accessToken);
        getRedirectStrategy().sendRedirect(request, response, "http://localhost:8080/api/user/test");
    }
}
