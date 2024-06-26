package kgu.softwareEG.personalMovie.domain.user.entity;

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
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String socialId;

    private boolean isSurveyed;


    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "type_id")
    private Type type;

    public void addType(Type type) {
        this.type = type;
    }

    public void updateIsSurveyed() {
        if (!isSurveyed) {
            this.isSurveyed = true;
        }
    }
}
