package kgu.softwareEG.personalMovie.domain.user.service;

import kgu.softwareEG.personalMovie.domain.user.dto.GetIsSurveyedResponseDto;
import kgu.softwareEG.personalMovie.domain.user.entity.User;
import kgu.softwareEG.personalMovie.domain.user.repository.UserRepository;
import kgu.softwareEG.personalMovie.global.auth.userInfo.OAuth2UserInfo;
import kgu.softwareEG.personalMovie.global.error.ErrorCode;
import kgu.softwareEG.personalMovie.global.error.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;
    /**
     * 유저정보를 받아 User 객체를 반환한다.
     * 만약 회원가입되어 있지 않다면 회원가입 한다.
     * 이미 가입되어 있다면 닉네임과 프로필이미지를 업데이트한다.
     * @param userInfo 로그인을 요청하는 유저의 정보
     * @return
     */
    public User getOrCreateUser(OAuth2UserInfo userInfo) {
        String email = userInfo.getEmail();
        String nickname = userInfo.getNickname();
        String profileImgUrl = userInfo.getProfileImgUrl();

        return userRepository.findBySocialId(email)
                .map(user -> {
                    user.updateUserInfo(nickname, profileImgUrl);
                    return user;
                })
                .orElseGet(() -> createUser(email,nickname,profileImgUrl));
    }

    private User createUser(String email, String nickname, String profileImgUrl) {
        User createdUser = User.builder()
                .socialId(email)
                .nickname(nickname)
                .profileImgUri(profileImgUrl)
                .build();

        return userRepository.save(createdUser);
    }

    public GetIsSurveyedResponseDto getIsSurveyed(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException(ErrorCode.MEMBER_NOT_FOUND));

        return GetIsSurveyedResponseDto.of(user.isSurveyed());
    }
}
