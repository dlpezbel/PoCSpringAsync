package poc.springasync;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidatorFactory {
	@Autowired
	Validator1Async validator1Async;

	public Validator1Async get(String validationType)
	{
		if ("Validator1Async".equals(validationType)) {
			return validator1Async;
		}

		return null;
	}
}