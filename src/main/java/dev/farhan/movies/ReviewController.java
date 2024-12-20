package dev.farhan.movies;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    // constructor injection
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }
    // Map<String, String> 表示payload是一个包含多个键值对的集合，里面的的每个键和值都要求是 String 类型。
    // {
    //    "title": "Inception",
    //    "director": "Christopher Nolan"
    //} json
    // 当通过 @RequestBody Map<String, String> payload 接收 JSON 数据时，Spring 会将 JSON 中的每一对键值自动映射到 Map 中作为键值对。
    @PostMapping
    public ResponseEntity<Review> createReview(@RequestBody Map<String, String> payload) {
        return new ResponseEntity<Review>(reviewService.createReview(payload.get("reviewBody"), payload.get("imdbId")), HttpStatus.CREATED);
    }
}
