package service;
import java.io.UnsupportedEncodingException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import org.apache.log4j.*;

public class Service {

    private static Service service = null;
    static Object dataTypeKey;
    static Object dataTypeValue;

    //constructor is written in a way to make service Singleton
    public Service Service() {
        if(service==null){
            service =  new Service();
        }
        return service;
    }

    public int getSetNumber(Object key, int numOfSet){
        int k = key.hashCode();
        return k % numOfSet;
    }

    public void setDataType(Object key, Object value){
        dataTypeKey = key;
        dataTypeValue = value;
    }

    public boolean checkTypeSafety(Object key, Object value){
        if((dataTypeValue == value.getClass()) && (dataTypeKey == key.getClass())){
            return true;
        }
        return false;
    }

    public boolean checkTypeSafety(Object key){
        if(dataTypeKey == key.getClass()){
            return true;
        }
        return false;
    }

}


