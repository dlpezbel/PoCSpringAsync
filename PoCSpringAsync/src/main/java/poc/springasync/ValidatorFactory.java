package poc.springasync;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidatorFactory {
	@Autowired
	ValidatorRepartosAsync validator1Async;

	public ValidatorRepartosAsync get(String validationType)
	{
		if ("Validator1Async".equals(validationType)) {
			return validator1Async;
		}

		return null;
	}
}