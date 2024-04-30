package kgu.softwareEG.personalMovie.global.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import kgu.softwareEG.personalMovie.global.error.ErrorCode;
import kgu.softwareEG.personalMovie.global.error.exception.UnauthorizedException;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class JwtUtil {

    public static final String PREFIX = "Bearer ";
    public static final String Authorization ="Authorization";
    @Value("${jwt.secret}")
    private String SECRET;
    @Value("${jwt.access-token-expire-time}")
    private long ACCESS_TOKEN_EXPIRE_TIME;


    /**
     * @param socialId 토큰에 담아줄 정보
     * @return 액세스토큰
     */
    public String createAccessToken(String socialId) {
        return PREFIX + JWT.create()
                .withSubject("AccessToken")
                .withExpiresAt(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRE_TIME))
                .withClaim("socialId", socialId)
                .sign(Algorithm.HMAC512(SECRET));
    }

    public void validateAccessToken(String accessToken) {
        try {
            JWT.require(Algorithm.HMAC512(SECRET)).build().verify(accessToken);
        } catch (JWTVerificationException e) {
            ErrorCode errorCode;
            if (e instanceof TokenExpiredException) {
                errorCode = ErrorCode.EXPIRED_ACCESS_TOKEN;
            } else {
                errorCode = ErrorCode.INVALID_ACCESS_TOKEN_VALUE;
            }
            throw new UnauthorizedException(errorCode);
        }
    }


    public String extractSocialId(String token) {
        return JWT.require(Algorithm.HMAC512(SECRET)).build()
                .verify(token)
                .getClaim("socialId").asString();
    }
}