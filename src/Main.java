import cache.Cache;
import test.User;
import exceptions.TypeMismatchException;

import java.math.BigInteger;


public class Main{


    //this is a sample Main() to learn more on How to access the API
    public static void main(String[] args) {

        //Initialising the cache
        //Key data-type => Integer.class
        //Value data-type => User.class
        Cache cache = new Cache(1,2,Integer.class,User.class,"lru");


        //Creating Users
        User user1 = new User(1,"User1","Lname1");
        User user2 = new User(2, "User2","Lname2");
        User user3 = new User(3, "User3","Lname3");


        try {
            //putting the users in cache
            cache.put(1, user1);
            cache.put(2, user2);
            //Get u2
            cache.get(2);
            //Now Set1 in cache is full
            //On next put u2 will be replaced by u1
            cache.put(3, user3);
            //Now our cache is u3->u1
            cache.get(1);
            //Now our cache is u1->u2

            //removing the u1 from cache
            cache.remove(1);

        }catch (TypeMismatchException e){
            System.out.println(e.getMessage());
        }


    }
}
