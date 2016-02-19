package poc.springasync;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

@SpringBootApplication
@EnableAsync
public class Application implements CommandLineRunner {

    @Autowired
    ValidatorFactory validatorFactory;
    
    @Override
    public void run(String... args) throws Exception {
        long start = System.currentTimeMillis();
        
        ValidatorA validatorA = validatorFactory.get("ValidatorA");
        Map<Integer, Future<String>> futureResultVMap = runParallelValidator(validatorA);
        List<String> results = sortResults(futureResultVMap);
        
        System.out.println("Elapsed time: " + (System.currentTimeMillis() - start));
    }

    private Map<Integer, Future<String>> runParallelValidator(ValidatorA validatorA) throws InterruptedException {
        Map<Integer,Future<String>> futureResultVMap = new HashMap<Integer,Future<String>>();
        for (int i = 0; i < 10; i++) {
            Future<String> resultValidation = validatorA.validate(new Integer(i));
            futureResultVMap.put(new Integer(i),resultValidation);
		}
        return futureResultVMap;
	}

	private List<String> sortResults(Map<Integer, Future<String>> futureResultVMap) throws InterruptedException, ExecutionException {
    	List<String> actualStringList = new ArrayList<String>();
        for (Map.Entry<Integer, Future<String>> entry : futureResultVMap.entrySet())
        {
            String resultValidation = (String) ((Future<?>)entry.getValue()).get();
            actualStringList.add(resultValidation);
        }
        return actualStringList;
	}

	public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}