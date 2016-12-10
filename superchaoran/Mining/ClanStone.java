package superchaoran.Mining;

import com.oracle.tools.packager.Log;
import org.powerbot.script.*;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.GameObject;
import org.powerbot.script.rt6.Item;

import java.util.concurrent.Callable;

/**
 * Created by chaoran on 5/11/16.
 */

@Script.Manifest(
        name = "Clan Stone", properties = "author=superchaoran; topic=-5; client=6;",
        description = "Clan Stone"
)
public class ClanStone extends PollingScript<ClientContext> implements MessageListener{
    private int rockIds[] = {28037, 28038};
    private final String rockName= "Stone";

    private long previousTime = System.currentTimeMillis();
    int count = 0;

    @Override
    public void poll(){
            log.info("Finding " + rockName);
            GameObject rock0 = ctx.objects.select().id(rockIds[count]).poll();
            if (rock0.valid()) {
                long timeDiff = System.currentTimeMillis() - previousTime;
                if (timeDiff > 5000) {
                    log.info("Time Diff:"+timeDiff +"bigger than 5s, click");
                    rock0.click();
                    Condition.sleep(300);
                    previousTime = System.currentTimeMillis();
                }
            } else {
                log.info("Mine disappeared");
                count = ++count % 2;
                previousTime = System.currentTimeMillis();
            }
    }
    @Override
    public void messaged(MessageEvent m) {
        String msg = m.text();
        if (msg.equals("You mine some stone.")){
            log.info("Game method match reset time");
            previousTime = System.currentTimeMillis();
        }

    }
}
