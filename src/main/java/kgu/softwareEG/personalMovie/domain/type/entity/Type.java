package kgu.softwareEG.personalMovie.domain.type.entity;

import jakarta.persistence.*;
import kgu.softwareEG.personalMovie.domain.movie.entity.Movie;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Type {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String imgUri;

    @OneToMany(mappedBy = "type", fetch = FetchType.LAZY)
    private List<Movie> movies = new ArrayList<>();

}
