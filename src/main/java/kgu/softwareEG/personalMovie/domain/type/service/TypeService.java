package kgu.softwareEG.personalMovie.domain.type.service;

import kgu.softwareEG.personalMovie.domain.movie.repository.MovieRepository;
import kgu.softwareEG.personalMovie.domain.survey.entity.Choice;
import kgu.softwareEG.personalMovie.domain.survey.repository.ChoiceRepository;
import kgu.softwareEG.personalMovie.domain.type.dto.request.GetUserTypeRequestDto;
import kgu.softwareEG.personalMovie.domain.type.dto.response.GetUserTypeResponseDto;
import kgu.softwareEG.personalMovie.domain.type.entity.Type;
import kgu.softwareEG.personalMovie.domain.type.repository.TypeRepository;
import kgu.softwareEG.personalMovie.domain.user.entity.User;
import kgu.softwareEG.personalMovie.domain.user.repository.UserRepository;
import kgu.softwareEG.personalMovie.global.error.exception.EntityNotFoundException;
import kgu.softwareEG.personalMovie.global.error.exception.InternalServerException;
import kgu.softwareEG.personalMovie.global.error.exception.InvalidValueException;
import kgu.softwareEG.personalMovie.global.util.YoutubeUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static kgu.softwareEG.personalMovie.global.error.ErrorCode.*;
import static kgu.softwareEG.personalMovie.global.util.YoutubeUtil.searchWord;

@Service
@RequiredArgsConstructor
@Transactional
public class TypeService {

    private final UserRepository userRepository;
    private final TypeRepository typeRepository;
    private final ChoiceRepository choiceRepository;

    public GetUserTypeResponseDto getUserType(Long userId, GetUserTypeRequestDto getUserTypeRequestDto) {
        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException(MEMBER_NOT_FOUND));
        List<Choice> choices = getUserTypeRequestDto.choices().stream().map(choiceId ->
                choiceRepository.findById(choiceId).orElseThrow(() -> new EntityNotFoundException(CHOICE_NOT_FOUND))).collect(Collectors.toList());

        if (choices.size() != 4) throw new InvalidValueException();

        Map<Long, Long> typeFrequency = choices.stream().map(choice -> choice.getType())
                .collect(Collectors.groupingBy(type -> type.getId(), Collectors.counting()));

        // 빈도 수가 2 이상인 타입의 수를 계산
        long repeatedTypeCount = typeFrequency.values().stream().filter(count -> count >= 2).count();

        long resultType = findMyType(typeFrequency, repeatedTypeCount);

        Type type = typeRepository.findById(resultType).orElseThrow(() -> new InternalServerException(INTERNAL_SERVER_ERROR));
        user.addType(type);

        return GetUserTypeResponseDto.of(type);
    }

    private long findMyType(Map<Long, Long> typeFrequency, long repeatedTypeCount) {
        long resultType;
        if (repeatedTypeCount == 2) {
            // 동일한 타입이 두 번 선택되는 경우가 두 번 이상 발생
            resultType = 8;
        } else {
            // 그 외의 경우, 빈도 수가 2 이상인 타입이 하나라도 있다면 해당 타입 반환, 없다면 타입 8 반환
            resultType = typeFrequency.entrySet().stream()
                    .filter(entry -> entry.getValue() >= 2)
                    .findFirst()
                    .map(Map.Entry::getKey)
                    .orElse(typeRepository.findById(8L).orElseThrow(()-> new InternalServerException(INTERNAL_SERVER_ERROR)).getId());
        }
        return resultType;
    }


}
