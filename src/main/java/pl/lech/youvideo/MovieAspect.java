package pl.lech.youvideo;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MovieAspect {

    @After("execution(* pl.lech.youvideo.MovieApi.addMovie(..))")
    private void afterAddMovie() {
        System.out.println("Wysłałem maila.");
    }
}
