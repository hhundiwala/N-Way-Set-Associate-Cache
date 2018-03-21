package replacementStrategies;

import cache.Cache;
import cache.DataBlock;
import cache.SetList;
import org.apache.log4j.Logger;

public class LeastRecentlyUsed implements IReplacementStrategies {

    @Override
    public void replace(SetList list, DataBlock block) {
        //In least recently used, we have to delete the oldest element
        //oldest element will always be the last element
        Logger log = Logger.getLogger(LeastRecentlyUsed.class.getName());
        log.info("LRU Algorithm using to remove DataBlock");
        try{
            DataBlock rear = list.getRear();
            list.record.remove(rear.getKey());
            list.rear = rear.prev;
            if(list.rear != null)
                list.rear.next = null;
            list.space++;
            list.add(block);
        }catch (NullPointerException e){
            log.error("Null Pointer Exception caught in LRU implementation");
        }

    }

}
