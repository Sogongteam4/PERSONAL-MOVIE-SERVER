package kgu.softwareEG.personalMovie.domain.user.controller;

import kgu.softwareEG.personalMovie.domain.user.dto.request.LoginRequestDto;
import kgu.softwareEG.personalMovie.domain.user.dto.response.LoginResponseDto;
import kgu.softwareEG.personalMovie.domain.user.service.UserService;
import kgu.softwareEG.personalMovie.global.common.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/test")
    public ResponseEntity<SuccessResponse<?>> testRedirect() {
        return SuccessResponse.ok("로그인 성공하였습니다.");
    }

    @PostMapping
    public ResponseEntity<SuccessResponse<?>> login(@RequestBody LoginRequestDto loginRequestDto) {
        LoginResponseDto loginResponseDto = userService.login(loginRequestDto);
        return SuccessResponse.ok(loginResponseDto);
    }

}
