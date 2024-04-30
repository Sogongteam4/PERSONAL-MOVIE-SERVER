package kgu.softwareEG.personalMovie.domain.survey.entity;

import jakarta.persistence.*;
import kgu.softwareEG.personalMovie.domain.user.entity.UserChoice;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @OneToMany(mappedBy = "question", fetch = LAZY)
    List<Choice> choices = new ArrayList<>();

}
