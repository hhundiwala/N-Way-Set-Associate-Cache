package cache;

import java.util.HashMap;
import org.apache.log4j.Logger;

public class SetList {
    public DataBlock head;
    public DataBlock rear;
    public int space;
    public HashMap<Object,DataBlock> record;
    Logger log;

    public SetList(int space) {
        this.space = space;
        this.head = null;
        this.rear = null;
        record = new HashMap<>();
        this.log = Logger.getLogger(SetList.class.getName());
    }

    //checks whether there is space in the set
    public boolean isFull(){
        return space <= 0;
    }



    /*
    Method Function: To add the Datablock in the SetList
    Note: The data block will always be added at the head (Since we want list to be sorted always according to last access)
       Parameters: DataBlock
       Working:
       1. Check if SetList is full, (if full, adding terminates)
       2. If the block is already present, remove it from record and list, and add it to the head
       3. If block not present, add it to head
       4. Update the space variable
     */
    public void add(DataBlock block){
        log.info("Add - Adding DataBlock to cache");
        if(isFull()){
            log.error("Add to list not allowed when max size reached - termination add operation");
            return;
        }
        try {
            if(record.containsKey(block.getKey())){
                //removing it from SetList
                delete(record.get(block.getKey()));
                //removing it from record
                record.remove(block.getKey());
            }
            record.put(block.getKey(),block);
            //if set is fully empty
            if(this.head == null || this.rear == null){
                this.head = block;
                this.rear = block;
            }else{
                block.next = this.head;
                this.head.prev = block;
                this.head = block;
            }
            this.space--;
        }catch (NullPointerException e){
            log.info("Null Pointer Exception caught adding block to SetList");
        }
    }



    /*
    Method Function: To delete the Datablock from the SetList
       Parameters: DataBlock
       Working:
       1. Delete the blocks
       2. Update the space variable
     */
    public void delete(DataBlock block){
        try{
            //if list is empty
            if(rear==null && head==null){
                System.out.println("Empty List nothing to delete");
                return;
            }
            //if only one node in the list
            if(head.prev==null && head.next==null){
                head = null;
                rear = null;
            }

            //if head needs to be deleted
            else if(head == block){
                head = head.next;
                if(head!=null){
                    head.prev = null;
                }
            }

            //if rear needs to be deleted
            else if(rear == block){
                rear = rear.prev;
                if(rear!=null){
                    rear.next = null;
                }
            }else{
                block.prev.next = block.next;
                block.next.prev = block.prev;
            }
            this.space++;
        }
        catch (NullPointerException e){
            log.error("Null Pointer Exception identified in delete method of SetList");
            log.error(e.getMessage());
        }
    }


    /*
   Method Function: To update the position the Datablock in the SetList
   Note: Whenever any block is accessed, the position of that block is update. i.e. the block is moved to the start of list
      Parameters: DataBlock
      Working:
      1. Make the DataBlock head of SetList
      2. Update the head of SetList
    */
    public void updateCache(DataBlock block){
       try{
           if(block == this.head){
               return;
           }
           if(block == rear){
               block.prev.next = null;
               rear = block.prev;
               block.next = head;
               head.prev = block;
               block.prev = null;
               head = head.prev;
           }else{
               block.prev.next = block.next;
               block.next.prev = block.prev;
               block.next = head;
               head.prev = block;
               block.prev = null;
               head = head.prev;
           }
       }catch (NullPointerException e){
           log.error("Null Pointer Exception identified in updateCache method of SetList");
           log.error(e.getMessage());
       }

    }



    //Getters and Setters
    public DataBlock getHead() {
        return head;
    }

    public void setHead(DataBlock head) {
        this.head = head;
    }

    public DataBlock getRear() {
        return rear;
    }

}
