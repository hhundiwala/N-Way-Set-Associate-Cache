package test;

import cache.DataBlock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import service.Service;

import javax.xml.crypto.Data;

public class DataBlockTest {

    DataBlock block = new DataBlock(4,"Datablock Testing");

    @Before
    public void setUp(){
        block = new DataBlock(4,"Datablock Testing");
    }

    @Test
    public void getData() {
        Assert.assertEquals(4,block.getData());
    }

    @Test
    public void getKey() {
        Assert.assertEquals("Datablock Testing",block.getKey());
    }

}
