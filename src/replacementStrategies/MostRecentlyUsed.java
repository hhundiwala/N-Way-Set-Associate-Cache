package replacementStrategies;

import cache.Cache;
import cache.DataBlock;
import cache.SetList;
import org.apache.log4j.Logger;

public class MostRecentlyUsed implements IReplacementStrategies {

    @Override
    public void replace(SetList list, DataBlock block) {
        //In Most recently used, we have to delete the newest element
        //newest element will always be the first element
        Logger log = Logger.getLogger(MostRecentlyUsed.class.getName());
        log.info("MRU Algorithm using to remove DataBlock");
        try{
            DataBlock head = list.getHead();
            list.record.remove(head.getKey());
            list.head = head.next;
            if(list.head!=null)
                list.head.prev = null;
            list.space++;
            list.add(block);
        }catch (NullPointerException e){
            log.error("Null Pointer Exception caught in MRU implementation");
        }
    }
}
