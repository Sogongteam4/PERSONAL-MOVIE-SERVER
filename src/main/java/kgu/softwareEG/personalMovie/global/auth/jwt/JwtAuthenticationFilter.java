package kgu.softwareEG.personalMovie.global.auth.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kgu.softwareEG.personalMovie.domain.user.entity.User;
import kgu.softwareEG.personalMovie.domain.user.repository.UserRepository;
import kgu.softwareEG.personalMovie.global.auth.OAuthPrincipalDetails;
import kgu.softwareEG.personalMovie.global.error.ErrorCode;
import kgu.softwareEG.personalMovie.global.error.exception.UnauthorizedException;
import kgu.softwareEG.personalMovie.global.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static kgu.softwareEG.personalMovie.global.error.ErrorCode.MEMBER_NOT_FOUND;
import static kgu.softwareEG.personalMovie.global.util.JwtUtil.Authorization;
import static kgu.softwareEG.personalMovie.global.util.JwtUtil.PREFIX;


@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String accessToken = request.getHeader(Authorization);

        if (accessToken == null) {
            filterChain.doFilter(request, response);
        }
        else {
            if (!accessToken.startsWith(PREFIX)) {
                throw new UnauthorizedException(ErrorCode.INVALID_ACCESS_TOKEN);}

            accessToken = accessToken.replace(PREFIX, "");

            jwtUtil.validateAccessToken(accessToken);
            String socialId = jwtUtil.extractSocialId(accessToken);

            setAuthentication(socialId);
            filterChain.doFilter(request, response);
        }
    }


    private void setAuthentication(String socialId) {
        User user = userRepository.findBySocialId(socialId)
                .orElseThrow(()-> new UnauthorizedException(MEMBER_NOT_FOUND));

        // user를 세션에 저장하기 위해 authentication 객체를 생성한다.
        OAuthPrincipalDetails principalDetails = new OAuthPrincipalDetails(user);
        Authentication authentication = new UsernamePasswordAuthenticationToken(principalDetails, null, principalDetails.getAuthorities());
        // 시큐리티의 세션에 authentication 객체를 저장한다.
        SecurityContextHolder.getContext().setAuthentication(authentication);

    }
}
