package kgu.softwareEG.personalMovie.domain.user.controller;

import kgu.softwareEG.personalMovie.domain.user.dto.GetIsSurveyedResponseDto;
import kgu.softwareEG.personalMovie.domain.user.service.UserService;
import kgu.softwareEG.personalMovie.global.auth.UserId;
import kgu.softwareEG.personalMovie.global.common.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/test")
    public ResponseEntity<SuccessResponse<?>> testRedirect() {
        return SuccessResponse.ok("로그인 성공하였습니다.");
    }

    @GetMapping("/status")
    public ResponseEntity<SuccessResponse<?>> getIsSurveyed(@UserId Long userId) {
        GetIsSurveyedResponseDto getIsSurveyedResponseDto = userService.getIsSurveyed(userId);
        return SuccessResponse.ok(getIsSurveyedResponseDto);
    }
}
