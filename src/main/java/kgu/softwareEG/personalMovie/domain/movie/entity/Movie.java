package kgu.softwareEG.personalMovie.domain.movie.entity;

import jakarta.persistence.*;
import kgu.softwareEG.personalMovie.domain.type.entity.Type;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private float rate;

    private int year;

    private String trailerUri;

    private String reviewVideoUri;

    @OneToMany(mappedBy = "movie", fetch = LAZY)
    private List<MovieGenre> movieGenres = new ArrayList<>();

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "type_id")
    private Type type;

    public void addTrailer(String trailerUri) {
        this.trailerUri = trailerUri;
    }
}
