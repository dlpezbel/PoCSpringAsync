package poc.springasync;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidatorFactory {
	public static String VALIDATION_TYPE_1 = "Validator1Async";
	@Autowired
	Validator1Async validator1Async;

	public Validator1Async get(String validationType)
	{
		if (VALIDATION_TYPE_1.equals(validationType)) {
			return validator1Async;
		}

		return null;
	}
}