package poc.springasync;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class Application implements CommandLineRunner {

    @Autowired
    ValidatorFactory validatorFactory;
    
    @Override
    public void run(String... args) throws Exception {
        long start = System.currentTimeMillis();
        
        ValidatorRepartosAsync validator1Async = validatorFactory.get("Validator1Async");
        List<String> results = validator1Async.validateAsync();
        
        System.out.println("Elapsed time: " + (System.currentTimeMillis() - start));
    }


	public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}