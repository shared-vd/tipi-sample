package ch.sharedvd.tipi.sample.proc;

import ch.sharedvd.tipi.engine.action.ActivityResultContext;
import ch.sharedvd.tipi.engine.action.FinishedActivityResultContext;
import ch.sharedvd.tipi.engine.action.TopProcess;
import ch.sharedvd.tipi.engine.client.TipiSubProcess;
import ch.sharedvd.tipi.engine.client.TipiVariable;
import ch.sharedvd.tipi.engine.meta.VariableType;
import ch.sharedvd.tipi.sample.TipiSampleApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@TipiSubProcess(description = "Main process",
        variables = {
                @TipiVariable(name = Const.VAR_NB_CHILDREN,
                        type = VariableType.Integer,
                        description = "Number of children that must be spawned")
        })
public class SampleAsyncSubProcess extends TopProcess {

    private static final Logger log = LoggerFactory.getLogger(TipiSampleApplication.class);

    @Override
    protected ActivityResultContext execute() throws Exception {
        log.info("Sample sub-process started ...");
        Thread.sleep(3000);


        return new FinishedActivityResultContext();
    }
}
