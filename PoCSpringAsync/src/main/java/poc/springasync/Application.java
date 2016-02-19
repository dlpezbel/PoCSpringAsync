package poc.springasync;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    ValidatorFactory validatorFactory;
    
    @Override
    public void run(String... args) throws Exception {
        long start = System.currentTimeMillis();
        
        Validator1Async validator1Async = validatorFactory.get(ValidatorFactory.VALIDATION_TYPE_1);
        List<String> results = validator1Async.validateAsync();
        
        System.out.println("Elapsed time: " + (System.currentTimeMillis() - start));
    }


	public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}