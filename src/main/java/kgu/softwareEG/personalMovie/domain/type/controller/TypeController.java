package kgu.softwareEG.personalMovie.domain.type.controller;

import kgu.softwareEG.personalMovie.domain.type.dto.response.GetUserTypeResponseDto;
import kgu.softwareEG.personalMovie.domain.type.service.TypeService;
import kgu.softwareEG.personalMovie.global.auth.UserId;
import kgu.softwareEG.personalMovie.global.common.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/types")
public class TypeController {

    private final TypeService typeService;

    @GetMapping
    public ResponseEntity<SuccessResponse<?>> getUserType(@UserId Long userId){
        GetUserTypeResponseDto getUserTypeResponseDto = typeService.getUserType(userId);
        return SuccessResponse.ok(getUserTypeResponseDto);
    }
}
