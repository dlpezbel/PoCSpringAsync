package poc.springasync;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

@Component
@EnableAsync
public class ValidatorRepartosAsync implements IValidatorAsync{
	@Autowired 
	ValidatorRepartosAsyncHelper validatorRepartosAsyncHelper;
	
    public List<String> validateAsync() throws Exception {
    	List<String> resultValidationList = new ArrayList<String>();
    	
    	List<Future<String>> futureResultMap = runParallelValidator();
    	
    	for (Future<String> future : futureResultMap) {
    		//Añadimos el resultado al informe de procesamiento a medida que los hilos van finalizando
    		resultValidationList.add(future.get());	
		}
    	return null;
    }

    private List<Future<String>> runParallelValidator() throws InterruptedException {
    	List<Future<String>> futureResultList = new ArrayList<Future<String>>();
        for (int i = 0; i < 10; i++) {
            Future<String> resultValidation = validatorRepartosAsyncHelper.validate(new Integer(i));
            futureResultList.add(resultValidation);
		}
        return futureResultList;
	}
}