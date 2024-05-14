package kgu.softwareEG.personalMovie.domain.user.service;

import kgu.softwareEG.personalMovie.domain.user.dto.request.LoginRequestDto;
import kgu.softwareEG.personalMovie.domain.user.dto.response.LoginResponseDto;
import kgu.softwareEG.personalMovie.domain.user.entity.User;
import kgu.softwareEG.personalMovie.domain.user.repository.UserRepository;
import kgu.softwareEG.personalMovie.global.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        String socialId = loginRequestDto.kakaoToken();
        Optional<User> user = userRepository.findBySocialId(socialId);

        if (!user.isPresent()) {
            User newUser = User.builder().socialId(socialId).build();
            userRepository.save(newUser);
        }

        String accessToken = jwtUtil.createAccessToken(socialId);

        return LoginResponseDto.of(accessToken);
    }
}
