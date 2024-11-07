package dev.farhan.movies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    @Autowired
    // mongoTemplate 可以跨集合操作数据库， 目前在这里用来更新Movie的reviewIds
    private MongoTemplate mongoTemplate;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public Review createReview(String reviewBody, String imdbId) {
        Review review =  reviewRepository.insert(new Review(reviewBody));

        // update(Movie.class) 是指在数据库 中操作与 Movie 类对应的集合, 也就是数据库中movies所装的那些json文件
        mongoTemplate.update(Movie.class)
                .matching(Criteria.where("imdbId").is(imdbId))// find the corresponding movie by imdbId
                .apply(new Update().push("reviewIds").value(review))// push this review object to the reviewIds array
                .first();
        return review;
    }
}
