package kgu.softwareEG.personalMovie.domain.type.controller;

import jakarta.validation.Valid;
import kgu.softwareEG.personalMovie.domain.type.dto.request.GetUserTypeRequestDto;
import kgu.softwareEG.personalMovie.domain.type.dto.response.GetUserTypeResponseDto;
import kgu.softwareEG.personalMovie.domain.type.service.TypeService;
import kgu.softwareEG.personalMovie.global.auth.UserId;
import kgu.softwareEG.personalMovie.global.common.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/types")
public class TypeController {

    private final TypeService typeService;

    @PostMapping
    public ResponseEntity<SuccessResponse<?>> getUserType(@UserId Long userId, @RequestBody @Valid GetUserTypeRequestDto getUserTypeRequestDto){
        GetUserTypeResponseDto getUserTypeResponseDto = typeService.getUserType(userId, getUserTypeRequestDto);
        return SuccessResponse.ok(getUserTypeResponseDto);
    }
}
