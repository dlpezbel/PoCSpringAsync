package poc.springasync;

import java.util.concurrent.Future;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public interface IValidator {
	@Async
    public Future<String> validate(Integer i) throws InterruptedException;
    
}