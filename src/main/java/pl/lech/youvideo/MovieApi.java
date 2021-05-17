package pl.lech.youvideo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movies")
public class MovieApi {

    private List<Movie> movieList;

    public MovieApi() {
        this.movieList = new ArrayList<>();
        movieList.add(new Movie(1, "First", "https://www.youtube.com/watch?v=VzWqtUkJhro"));
        movieList.add(new Movie(2, "Second", "https://www.youtube.com/watch?v=HvfIeTieXOI"));
    }

    @GetMapping
    public ResponseEntity<List<Movie>> getMovies() {
        return new ResponseEntity<>(movieList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMoviesById(@PathVariable long id) {
        Optional<Movie> first =  movieList.stream().filter(movie -> movie.getId() == id).findFirst();
        if (first.isPresent()) {
            return new ResponseEntity<>(first.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity addMovie(@RequestBody Movie movie) {
        boolean added = movieList.add(movie);
        if (added) {
            return new ResponseEntity(HttpStatus.CREATED);
        }
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping
    public ResponseEntity modifyMovie(@RequestBody Movie newMovie) {
        Optional<Movie> first =  movieList.stream().filter(movie -> movie.getId() == newMovie.getId()).findFirst();
        if (first.isPresent()) {
            movieList.remove(first.get());
            movieList.add(newMovie);
            return new ResponseEntity(HttpStatus.CREATED);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteMovie(@PathVariable long id) {
        Optional<Movie> first =  movieList.stream().filter(movie -> movie.getId() == id).findFirst();
        if (first.isPresent()) {
            movieList.remove(first.get());
            return new ResponseEntity<>(first.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
