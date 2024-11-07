package dev.farhan.movies;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovieRepository extends MongoRepository<Movie, ObjectId> {

    // mongodb will automatically implement this method according to the method name ImdbId is the field name in the Movie class
    // therefore we can the same method to find movie by any field in the Movie class
    // mongodb will implement automatically for us as long as the field name is correct
    Optional<Movie> findMovieByImdbId(String imdbId);
}
