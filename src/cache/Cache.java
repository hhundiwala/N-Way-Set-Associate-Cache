package cache;

import exceptions.NoSuchStrategyException;
import exceptions.TypeMismatchException;
import replacementStrategies.IReplacementStrategies;
import replacementStrategies.LeastRecentlyUsed;
import service.Service;

import java.util.HashMap;
import org.apache.log4j.Logger;

public class Cache {

    int numOfSet, setSize;
    HashMap<Integer,SetList> setAssociativeCache = new HashMap<>();
    IReplacementStrategies replacementStrategy;
    Logger log;

    public Cache(int numOfSet, int setSize,Object keyType,Object valueType,String strategy) {
        if(setSize <=0){// if set size is invalid
            this.setSize = 2;
        }else {
            this.setSize = setSize;
        }
        if(numOfSet <=0){ // if numOfSet is invalid
            this.numOfSet = 10;
        }else {
            this.numOfSet = setSize;
        }
        this.log = Logger.getLogger(Cache.class.getName());
        Service service = new Service();
        service.setDataType(keyType,valueType);
        ReplacementStrategiesFactory strategyFactory = new ReplacementStrategiesFactory();
        replacementStrategy = strategyFactory.getReplacementStrategy(strategy);
    }

    public Cache(Object keyType, Object valueType, String strategy) {
        this.numOfSet = 10;
        this.setSize = 2;
        Service service = new Service();
        service.setDataType(keyType,valueType);
        this.log = Logger.getLogger(Cache.class.getName());
        ReplacementStrategiesFactory strategyFactory = new ReplacementStrategiesFactory();
        replacementStrategy = strategyFactory.getReplacementStrategy(strategy);
    }


    /*
        Method Function: To put the key and value in cache
        Parameters: Key and Value
        Working:
        1. Check if Key and Object are type safe else throw
        2. Generate the setNumber, Create SetList if not already present
        3. Append/update data in the SetList
     */

    public void put(Object key, Object value) throws TypeMismatchException{
        log.info("Put Operation - Starting Execution");
        Service service = new Service();
        if(service.checkTypeSafety(key,value)){
            int setNumber = service.getSetNumber(key,this.numOfSet);
            DataBlock block = new DataBlock(value,key);
            if(!setAssociativeCache.containsKey(setNumber)){
                //initialising new SetList
                SetList list = new SetList(this.setSize);
                list.add(block);
                setAssociativeCache.put(setNumber,list);
            }else{   //the key already exist,
                SetList list = setAssociativeCache.get(setNumber);
                if(list.isFull()){
                    replacementStrategy.replace(list,block);
                }else{
                    list.add(block);
                }
                setAssociativeCache.put(setNumber,list);
            }
            log.info("Put Operation - Execution finished successfully");
        }else{
            log.error("Type Mismatch Exception found, Datatype of key,value should be same for a instance of cache");
            throw new TypeMismatchException("Type Mismatch Exception in either Key or Value");
        }
    }


    /*
       Method Function: To get the value of data corresponding to the key
       Parameters: Key
       Working:
       1. Check if Key is type safe else throw exception
       2. Generate the setNumber, check if setList corresponding to setNumber exist
       3. Check if the Datablock with key exist in setList
       3. Retrieve the Datablock, update its position
    */
    public Object get(Object key) throws TypeMismatchException{
        log.info("Get Operation - Starting Execution");
        //need to find the particular element
        Service service = new Service();
        if(service.checkTypeSafety(key)){
            int setNumber = service.getSetNumber(key,this.numOfSet);
            if(!setAssociativeCache.containsKey(setNumber)){
                log.info("Get Operation - Cache Miss identified (Set Number unidentified), Terminating execution");
            }else{
                SetList list = setAssociativeCache.get(setNumber);
                if(!list.record.containsKey(key)){
                    log.info("Get Operation - Cache Miss identified (DataBlock not found in cache), Terminating execution");
                }else{
                    DataBlock block = list.record.get(key);
                    list.updateCache(block);
                    log.info("Get Operation - Execution finished successfully");
                    return block.getData();
                }
            }
            return null;
        }else{
            log.error("Type Mismatch Exception found, Datatype of key should be same for a instance of cache");
            throw new TypeMismatchException("Type Mismatch Exception in Key");
        }
    }


    /*
       Method Function: To remove DataBlock corresponding to the key
       Parameters: Key
       Working:
       1. Check if Key is type safe else throw exception
       2. Generate the setNumber, check if setList corresponding to setNumber exist
       3. Check if the Datablock with key exist in setList
       4. Retrieve the Datablock
       5. Remove it from the list
       6. Remove it from record
     */
    public Object remove(Object key) throws TypeMismatchException{
        //this function should remove the block corresponding to the key provided
        Service service = new Service();
        if(service.checkTypeSafety(key)){
            int setNumber = service.getSetNumber(key,this.numOfSet);
            if(!setAssociativeCache.containsKey(setNumber)){
                log.info("Get Operation - Cache Miss identified (Set Number unidentified), Termination execution");
            }else{
                SetList list = setAssociativeCache.get(setNumber);
                if(!list.record.containsKey(key)){
                    log.info("Get Operation - Cache Miss identified (DataBlock not found in cache), Termination execution");
                }else{
                    DataBlock block = list.record.get(key);
                    list.delete(block);
                    list.record.remove(key);
                    return block.getData();
                }
            }
        }else{
            log.error("Type Mismatch Exception found, Datatype of key should be same for a instance of cache");
            throw new TypeMismatchException("Type Mismatch Exception in Key");
        }
        return null;
    }


    public void clearCache(){
        setAssociativeCache = new HashMap<>();
        System.gc();
    }

}
