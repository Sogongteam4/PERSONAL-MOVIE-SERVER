package kgu.softwareEG.personalMovie.domain.survey.controller;

import kgu.softwareEG.personalMovie.domain.survey.dto.response.GetQuestionAndChoicesResponseDto;
import kgu.softwareEG.personalMovie.domain.survey.service.SurveyService;
import kgu.softwareEG.personalMovie.global.common.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/surveys")
public class SurveyController {

    private final SurveyService surveyService;

    @GetMapping("/{surveyId}")
    public ResponseEntity<SuccessResponse<?>> getQuestionAndChoices(@PathVariable Long surveyId) {
        GetQuestionAndChoicesResponseDto getQuestionAndChoicesResponseDto = surveyService.getQuestionAndChoices(surveyId);
        return SuccessResponse.ok(getQuestionAndChoicesResponseDto);
    }

}
