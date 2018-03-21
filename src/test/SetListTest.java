package test;

import cache.DataBlock;
import cache.SetList;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import service.Service;

public class SetListTest {

    DataBlock block1 = new DataBlock("block1",1);
    DataBlock block2 = new DataBlock("block2",2);
    DataBlock block3 = new DataBlock("block3",3);



    @Test
    public void add() {
        SetList list = new SetList(3);
        list.add(block1);
        //when we add first block, rear and head should point to it
        Assert.assertEquals(block1,list.head);
        Assert.assertEquals(block1,list.rear);
        //adding second block
        list.add(block2);
        Assert.assertEquals(block2,list.head);
        Assert.assertEquals(block1,list.rear);
        //adding third block
        list.add(block3);
        Assert.assertEquals(block1,list.rear);
        Assert.assertEquals(block3,list.head);
    }

    @Test
    public void updateCache() {
        SetList list = new SetList(3);
        list.add(block1);
        list.add(block2);
        list.add(block3);
        list.updateCache(block1);
        Assert.assertEquals(block1,list.head);
        Assert.assertEquals(block2,list.rear);
    }

    @Test
    public void isFull() {
        SetList list = new SetList(3);
        Assert.assertEquals(false,list.isFull());
        list.add(block1);
        list.add(block2);
        list.add(block3);
        /* After adding 3 blocks, the list should be full*/
        Assert.assertEquals(true,list.isFull());
    }


}
