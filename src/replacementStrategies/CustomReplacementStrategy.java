package replacementStrategies;

import cache.DataBlock;
import cache.SetList;
import org.apache.log4j.Logger;

public class CustomReplacementStrategy implements IReplacementStrategies {

    @Override
    public void replace(SetList list, DataBlock block) {
        //Method for client to provide own implementation strategy
        Logger log = Logger.getLogger(CustomReplacementStrategy.class.getName());
        log.info("Custom Replacement Strategy Executing");
    }
}
