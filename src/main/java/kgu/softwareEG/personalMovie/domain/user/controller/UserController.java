package kgu.softwareEG.personalMovie.domain.user.controller;

import kgu.softwareEG.personalMovie.global.common.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    @GetMapping("/test")
    public ResponseEntity<SuccessResponse<?>> testRedirect() {
        return SuccessResponse.ok("로그인 성공하였습니다.");
    }
}
