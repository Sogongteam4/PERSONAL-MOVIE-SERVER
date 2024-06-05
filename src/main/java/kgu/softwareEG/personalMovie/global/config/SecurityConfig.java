package kgu.softwareEG.personalMovie.global.config;

import kgu.softwareEG.personalMovie.domain.user.repository.UserRepository;
import kgu.softwareEG.personalMovie.global.auth.handelr.CustomAccessDeniedHandler;
import kgu.softwareEG.personalMovie.global.auth.handelr.CustomAuthenticationEntryPoint;
import kgu.softwareEG.personalMovie.global.auth.jwt.ExceptionHandlerFilter;
import kgu.softwareEG.personalMovie.global.auth.jwt.JwtAuthenticationFilter;
import kgu.softwareEG.personalMovie.global.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.RememberMeConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfig {
    private final CorsConfig corsConfig;
    private final JwtUtil jwtUtil;
    private final ExceptionHandlerFilter exceptionHandlerFilter;
    private final UserRepository userRepository;
    private final CustomAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        /**
         * JWT 사용 설정
         */
        http
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .rememberMe(RememberMeConfigurer::disable)
                .sessionManagement(sessionManagementConfigurer ->
                        sessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS));


        /**
         * 접근 허용 uri 추가
         */
        http
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers("/api/users/test","/api/users","/swagger-ui/**","/v3/api-docs/**").permitAll()
                        .anyRequest().authenticated());

        /**
         * 에러 발생 처리
         */
        http
                .exceptionHandling(exceptionHandlingConfigurer ->
                        exceptionHandlingConfigurer.authenticationEntryPoint(jwtAuthenticationEntryPoint))
                .exceptionHandling(exceptionHandlingConfigurer->
                        exceptionHandlingConfigurer.accessDeniedHandler(customAccessDeniedHandler));

        /**
         * 필터 추가
         */
        http
                .addFilter(corsConfig.corsFilter())
                .addFilterBefore(new JwtAuthenticationFilter(jwtUtil, userRepository), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(exceptionHandlerFilter, JwtAuthenticationFilter.class);

        return http.build();
    }
}