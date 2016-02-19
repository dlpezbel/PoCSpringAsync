package poc.springasync;

import java.util.concurrent.Future;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class ValidatorA {

    @Async
    public Future<String> validate(Integer i) throws InterruptedException {
        System.out.println("Validation A started ...  " + i);
        Thread.sleep(10000);
        System.out.println("Validation A finished ...  " + i);
        
        return new AsyncResult<String>("Validation A finished ... " + i);
    }

}