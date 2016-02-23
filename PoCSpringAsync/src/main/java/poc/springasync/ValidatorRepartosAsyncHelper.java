package poc.springasync;

import java.util.concurrent.Future;

import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class ValidatorRepartosAsyncHelper {
	@Async
	public Future<String> validate(Integer integer) {
		System.out.println("validation started... " + integer);
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("validation finished... " + integer);
		return new AsyncResult<String>("validation result... " + integer);
	}

}
