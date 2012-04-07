import org.apache.mina.util.ExpirationListener;
import org.apache.mina.util.ExpiringMap;

/**
 * Created by IntelliJ IDEA.
 */
public class ExpireMapdemo {


    public static void main(String[] args)  {
       // final ExpiringMap<String,Object> expiringMap=new ExpiringMap<String, Object>(5);
       demo2 d= new demo2();
        d.start();
        try{

            Thread.sleep(100000); }
        catch (Exception e){

        }
    }

}
class demo2 extends Thread {
    @Override
    public void run()   {
       final ExpiringMap<String,Object> expiringMap=new ExpiringMap<String, Object>(5);
        expiringMap.addExpirationListener(new ExpirationListener<Object>() {
            @Override
            public void expired(Object o) {
                System.out.print(expiringMap.size()) ;
                System.out.println("过期对象："+o.toString());

            }
        });
        expiringMap.getExpirer().startExpiring();
        try{
        expiringMap.put("1","ojbect1");

            expiringMap.put("2","ojbect2");
         //   Thread.sleep(10000);
        expiringMap.put("3","ojbect3");
            Thread.sleep(10000);
            expiringMap.put("4","ojbect4");
            System.out.print(expiringMap.size()) ;
            Thread.sleep(10000);

        }catch (InterruptedException e){

        }


    }
}



