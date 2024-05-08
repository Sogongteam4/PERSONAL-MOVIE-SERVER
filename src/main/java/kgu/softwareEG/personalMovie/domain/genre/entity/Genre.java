package kgu.softwareEG.personalMovie.domain.genre.entity;

import jakarta.persistence.*;
import kgu.softwareEG.personalMovie.domain.movie.entity.MovieGenre;
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
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "genre", fetch = LAZY)
    private List<MovieGenre> movieGenres = new ArrayList<>();

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "type_id")
    private Type type;

}
