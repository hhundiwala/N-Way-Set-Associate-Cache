package test;


import org.junit.jupiter.api.Test;
import org.junit.*;
import service.Service;



public class ServiceTest {


    @Test
    public void getSetNumber() {
        Service service = new Service();
        Assert.assertEquals("This number should always be less than number of set",0,service.getSetNumber("Test Key",1));
        Assert.assertEquals("This number should always be less than number of set",4,service.getSetNumber(4,5));
    }

    @Test
    public void getDataType() {
        Service service = new Service();
        service.setDataType(String.class,Integer.class);
        Assert.assertEquals("Checking type safety",true,service.checkTypeSafety("g",4));
        Assert.assertEquals("Checking type safety",true,service.checkTypeSafety("Test Key"));
        Assert.assertEquals("Checking type safety",false,service.checkTypeSafety(3,1));
        Assert.assertEquals("Checking type safety",false,service.checkTypeSafety(2));
    }



}
