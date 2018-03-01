package ch.sharedvd.tipi.sample;

import ch.sharedvd.tipi.engine.client.TipiFacade;
import ch.sharedvd.tipi.engine.client.VariableMap;
import ch.sharedvd.tipi.engine.query.TipiQueryFacade;
import ch.sharedvd.tipi.engine.runner.TipiStarter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TipiSampleApplication implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(TipiSampleApplication.class);

    @Autowired
    private TipiStarter tipiStarter;
    @Autowired
    private TipiFacade tipiFacade;
    @Autowired
    private TipiQueryFacade tipiQueryFacade;

    public static void main(String[] args) {
        SpringApplication.run(TipiSampleApplication.class, args);
        log.info("Application terminated");
    }

    @Override
    public void run(String... strings) throws Exception {
        log.info("Running TiPi sample ...");

        final VariableMap vars = new VariableMap();
        long pid = tipiFacade.launch(SampleTopProcess.class, vars);
        while (tipiFacade.isRunning(pid)) {
            Thread.sleep(500);
        }

        // We cleanly stop TiPi
        //tipiStarter.stop();
    }
}
