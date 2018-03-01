package ch.sharedvd.tipi.sample;

import ch.sharedvd.tipi.engine.action.ActivityResultContext;
import ch.sharedvd.tipi.engine.action.FinishedActivityResultContext;
import ch.sharedvd.tipi.engine.action.TopProcess;
import ch.sharedvd.tipi.engine.client.TipiTopProcess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@TipiTopProcess(description = "Main process")
public class SampleTopProcess extends TopProcess {

    private static final Logger log = LoggerFactory.getLogger(TipiSampleApplication.class);

    @Override
    protected ActivityResultContext execute() throws Exception {
        log.info("Sample process started ...");
        Thread.sleep(3000);

        return new FinishedActivityResultContext();
    }
}
