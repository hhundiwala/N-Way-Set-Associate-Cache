package cache;

import exceptions.NoSuchStrategyException;
import org.apache.log4j.Logger;
import replacementStrategies.CustomReplacementStrategy;
import replacementStrategies.IReplacementStrategies;
import replacementStrategies.LeastRecentlyUsed;
import replacementStrategies.MostRecentlyUsed;

public class ReplacementStrategiesFactory {
    public IReplacementStrategies getReplacementStrategy(String startegy) {

        Logger log = Logger.getLogger(Cache.class.getName());
        if(startegy==null){
            log.error("Strategy String cannot be null");
            return null;
        }
        else if(startegy.toLowerCase() == "lru"){
            log.info("Least Recently Used replacement strategy selected");
            return new LeastRecentlyUsed();
        }
        else if(startegy.toLowerCase() == "mru"){
            log.info("Most Recently Used replacement strategy selected");
            return new MostRecentlyUsed();
        }
        else if(startegy.toLowerCase() == "custom"){
            log.info("Custom replacement strategy selected");
            return new CustomReplacementStrategy();
        }
        else{
            log.error("Incorrect Strategy Defined");
            log.error("Only Allowed Strategies are -LRU, MRU, Custom");
            log.info("Updating the replacement strategy to LRU");
            return new LeastRecentlyUsed();
        }
    }
}
