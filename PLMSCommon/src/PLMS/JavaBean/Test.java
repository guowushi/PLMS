package PLMS.JavaBean;

import PLMS.GUI.TableColumnDefine;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 12-3-25
 * Time: 上午10:00
 * To change this template use File | Settings | File Templates.
 */
public class Test {
    public static  void main(String []args){
        ArrayList<Integer> arrayList=new ArrayList<Integer>(4);
        arrayList.add(new Integer(1));
        arrayList.add(new Integer(1));
        arrayList.add(new Integer(1));
        for(int i=0;i<arrayList.size();i++){
            System.out.print(arrayList.get(i));
        }
        System.out.println("");
        arrayList.set(1,new Integer(3));
        for(int i=0;i<arrayList.size();i++){
            System.out.print(arrayList.get(i));
        }
   Method []methods=TerminalSessionBean.class.getMethods();
  /*        for(Method method:methods){
        Annotation[] annotations = method.getDeclaredAnnotations();

        for(Annotation annotation : annotations){
            if(annotation instanceof TableColumnDefine){
                TableColumnDefine myAnnotation = (TableColumnDefine) annotation;
                System.out.println("name: " + myAnnotation.order());

            }
        }
        }*/
     for(Method method:methods){
         System.out.println(method.getName());
        Annotation annotation= method.getAnnotation(TableColumnDefine.class);

        if(annotation!=null){
            System.out.println("\t"+method.getName()+" 有TableColumnDefine");
        }
     }



    }
}


