package kgu.softwareEG.personalMovie.domain.survey.entity;

import jakarta.persistence.*;
import kgu.softwareEG.personalMovie.domain.type.entity.Type;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Choice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "question_id")
    private Question question;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "type_id")
    private Type type;

}
