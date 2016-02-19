package poc.springasync;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidatorFactory {
	@Autowired
	ValidatorA validatorA;

	@Autowired
	ValidatorA validatorB;

	public ValidatorA get(String validationType)
	{
		if ("ValidatorA".equals(validationType)) {
			return validatorA;
		}
		else if ("ValidatorB".equals(validationType)) {
			return validatorB;
		}
		return null;
	}
}