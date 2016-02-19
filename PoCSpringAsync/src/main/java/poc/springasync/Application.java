package poc.springasync;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class Application implements CommandLineRunner {

    @Autowired
    ValidatorFactory validatorFactory;
    
    @Override
    public void run(String... args) throws Exception {
        // Start the clock
        long start = System.currentTimeMillis();
        IValidator validatorA = validatorFactory.get(validatorFactory.VALIDATION_A);
        Map<Integer,Future<String>> futureResultVMap = new HashMap<Integer,Future<String>>();
        for (int i = 0; i < 10; i++) {
            Future<String> resultValidation = validatorA.validate(new Integer(i));
            futureResultVMap.put(new Integer(i),resultValidation);
		}

        List<String> actualStringList = new ArrayList<String>();
        
        for (Map.Entry<Integer, Future<String>> entry : futureResultVMap.entrySet())
        {
            String resultValidation = (String) ((Future)entry.getValue()).get();
            actualStringList.add(resultValidation);
        }   
        
         // Print results, including elapsed time
        System.out.println("Elapsed time: " + (System.currentTimeMillis() - start));
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}