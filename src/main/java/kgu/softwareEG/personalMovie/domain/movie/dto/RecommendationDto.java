package kgu.softwareEG.personalMovie.domain.movie.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data @Builder
@NoArgsConstructor @AllArgsConstructor
public class RecommendationDto {
    private Long userId;
    private List<MovieData> pickedMovies;


}

@Data @Builder
class MovieData{
    private Long movieId;
    private String title;
    private double rate;
}