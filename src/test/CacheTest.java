package test;

import cache.Cache;
import exceptions.TypeMismatchException;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import service.Service;

public class CacheTest {

    @Test
    public void putAndGet() {
        Cache c = new Cache(1,3,Integer.class,Integer.class,"lru");
        // Integer Key and Value
        try{
            c.put(3,4);
            Assert.assertEquals(4,c.get(3));
            c.put(5,343545);
            Assert.assertEquals(343545,c.get(5));
        }catch (Exception e){
            //do nothing
        }

        // String Key and Integer Value
        try{
            c.put("Test",4);
            Assert.assertEquals(4,c.get(3));
            c.put("Testing",343545);
            Assert.assertEquals(343545,c.get(5));
        }catch (Exception e){
            //do nothing
        }
    }


    @Test
    public void sameKeyCacheUpdate() {
        Cache c = new Cache(1,3,Integer.class,Integer.class,"lru");
        // Integer Key and Value
        try{
            c.put(3,4);
            Assert.assertEquals(4,c.get(3));
            c.put(3,343545);
            Assert.assertEquals(343545,c.get(3));
        }catch (Exception e){
            //do nothing
        }
    }

    @Test
    public void cacheMiss() {
        Cache c = new Cache(1,3,Integer.class,Integer.class,"lru");
        // Integer Key and Value
        try{
            c.put(3,4);
            Assert.assertEquals(4,c.get(3));
            c.put(3,343545);
            Assert.assertEquals(null,c.get(5));
        }catch (Exception e){
            //do nothing
        }
    }



   @Test
    public void testPutException() {
        boolean thro = false;
        Cache c = new Cache(1,3,Integer.class,Integer.class,"lru");
        try {
            c.put("test","exception");
        }catch (TypeMismatchException e){
            thro = true;
        }
        Assert.assertTrue(thro);
    }



    @Test
    public void testGutException() {
        boolean thro = false;
        Cache c = new Cache(1,3,Integer.class,Integer.class,"lru");
        try {
            c.get("test");
        }catch (TypeMismatchException e){
            thro = true;
        }
        Assert.assertTrue(thro);
    }
}
