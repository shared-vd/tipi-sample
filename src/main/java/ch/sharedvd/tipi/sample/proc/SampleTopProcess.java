package ch.sharedvd.tipi.sample.proc;

import ch.sharedvd.tipi.engine.action.ActivityResultContext;
import ch.sharedvd.tipi.engine.action.FinishedActivityResultContext;
import ch.sharedvd.tipi.engine.action.TopProcess;
import ch.sharedvd.tipi.engine.client.TipiTopProcess;
import ch.sharedvd.tipi.engine.client.TipiVariable;
import ch.sharedvd.tipi.engine.client.VariableMap;
import ch.sharedvd.tipi.engine.meta.VariableType;
import ch.sharedvd.tipi.engine.utils.Assert;
import ch.sharedvd.tipi.sample.TipiSampleApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static ch.sharedvd.tipi.sample.proc.Const.*;

@TipiTopProcess(description = "Main process",
        variables = {
                @TipiVariable(name = VAR_MESSAGE,
                        type = VariableType.String,
                        description = "The message to show when run"),
                @TipiVariable(name = VAR_NB_CHILDREN,
                        type = VariableType.Integer,
                        description = "Number of children that must be spawned"),
                @TipiVariable(name = VAR_SLEEP_TIME,
                        type = VariableType.Integer,
                        description = "Number of [ms] that each child must sleep")
        })
public class SampleTopProcess extends TopProcess {

    private static final Logger log = LoggerFactory.getLogger(TipiSampleApplication.class);

    @Override
    protected ActivityResultContext execute() throws Exception {
        log.info("Sample process started ...");

        final String message = getStringVariable(VAR_MESSAGE);
        Assert.notNull(message, "Message variable not defined");
        log.info("Message: " + message);

        final Integer sleepTime = getIntVariable(VAR_SLEEP_TIME);
        Assert.notNull(sleepTime, "SleepTime variable not defined");

        {
            final VariableMap vars = new VariableMap();
            vars.put(Const.VAR_NB_CHILDREN, 20);
            addChildActivity(SampleAsyncSubProcess.class, vars);
        }
        {
            final VariableMap vars = new VariableMap();
            vars.put(Const.VAR_NB_CHILDREN, 20);
            addChildActivity(SampleSyncSubProcess.class, vars);
        }

        Thread.sleep(3000);
        return new FinishedActivityResultContext();
    }
}
