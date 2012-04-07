package PLMS.GUI;

import java.util.Comparator;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 12-3-2
 * Time: 下午3:24
 * To change this template use File | Settings | File Templates.
 */
public class MenuObj {

        public int mnid;
        public String menuName;
        public int parentID;
        public int order;  //同级菜单之间的顺序

        public MenuObj(int v, String s, int pid, int order) {
            this.mnid = v;
            this.menuName = s;
            this.parentID = pid;
            this.order=order;
        }

}
class MenuOrderComparator implements Comparator<MenuObj> {

    @Override
    public int compare(MenuObj o1, MenuObj o2) {
        return o1.order-o2.order;
    }

    @Override
    public boolean equals(Object obj) {
        return false;
    }
}


