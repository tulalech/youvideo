package pl.lech.youvideo;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Controller
@Component
@Aspect
public class MovieAspect {

    @Autowired
    private JavaMailSender mailSender;

    public void sendMail() {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setFrom("lech.tula@gmail.com");
        mail.setTo("lech.tula@onmail.com");
        mail.setSubject("I have add a movie!");
        mail.setText("Hi! You have a new movie in our library.");
        mailSender.send(mail);
    }

    @After("execution(* pl.lech.youvideo.MovieApi.addMovie(..))")
    private void afterAddMovie() {
        System.out.println("Wysłałem maila.");
        sendMail();
    }
}
