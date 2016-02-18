package poc.springasync;

import java.util.concurrent.Future;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class Validator {

    @Async
    public Future<String> findUser(Integer i) throws InterruptedException {
        System.out.println("Validation started ...  " + i);
        //User results = restTemplate.getForObject("https://api.github.com/users/" + user, User.class);
        // Artificial delay of 1s for demonstration purposes
        Thread.sleep(1000);
        return new AsyncResult<String>("Validation finished ... " + i);
    }

}