package poc.springasync;

import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

@Component
public class ValidatorFactory {

	
    public static final String VALIDATION_A = "VALIDATION_A";
    
    @Autowired
    ValidatorA validatorA;

	public IValidator get(String validationType) {
		if (VALIDATION_A.equals(validationType))
		{
			return validatorA;
		}
		return null;
	}


	

}