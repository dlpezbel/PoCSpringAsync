package poc.springasync;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;


@Component
@EnableAsync
public class Validator1Async implements IValidatorAsync {
	@Autowired 
	Validator1AsyncHelper validator1AsyncHelper;
	
	@Override
    public List<String> validateAsync() throws Exception {
    	Map<Integer, Future<String>> futureResultVMap = runParallelValidator();
	    List<String> results = sortResults(futureResultVMap);
	    return results;
    }

    /**
     * @return
     * @throws InterruptedException
     */
    private Map<Integer, Future<String>> runParallelValidator() throws InterruptedException {
        Map<Integer,Future<String>> futureResultVMap = new HashMap<Integer,Future<String>>();
        for (int i = 0; i < 10; i++) {
            Future<String> resultValidation = validator1AsyncHelper.validate(new Integer(i));
            futureResultVMap.put(new Integer(i),resultValidation);
		}
        return futureResultVMap;
	}

	/**
	 * @param futureResultVMap
	 * @return
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	private List<String> sortResults(Map<Integer, Future<String>> futureResultVMap) throws InterruptedException, ExecutionException {
    	List<String> actualStringList = new ArrayList<String>();
        for (Map.Entry<Integer, Future<String>> entry : futureResultVMap.entrySet())
        {
            String resultValidation = (String) ((Future<?>)entry.getValue()).get();
            actualStringList.add(resultValidation);
        }
        return actualStringList;
	}
    
}