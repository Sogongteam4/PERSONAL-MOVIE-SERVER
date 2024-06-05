package kgu.softwareEG.personalMovie.global.util;

import java.util.*;

public class ItemSimilarity {

    public static double cosineSimilarity(Map<Long, Double> movieRatings1, Map<Long, Double> movieRatings2) {
        Set<Long> commonUsers = new HashSet<>(movieRatings1.keySet());
        commonUsers.retainAll(movieRatings2.keySet());

        if (commonUsers.isEmpty()) {
            return 0.0;
        }

        double dotProduct = 0.0;
        double norm1 = 0.0;
        double norm2 = 0.0;

        for (Long userId : commonUsers) {
            dotProduct += movieRatings1.get(userId) * movieRatings2.get(userId);
        }

        for (double rating : movieRatings1.values()) {
            norm1 += rating * rating;
        }

        for (double rating : movieRatings2.values()) {
            norm2 += rating * rating;
        }

        return dotProduct / (Math.sqrt(norm1) * Math.sqrt(norm2));
    }
}
