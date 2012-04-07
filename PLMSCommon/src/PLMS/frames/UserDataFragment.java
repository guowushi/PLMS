package PLMS.frames;

import PLMS.DataUnits.AbstractFn;
import PLMS.Enums.AFN;
import PLMS.Enums.EnumFn;
import PLMS.Enums.EnumInfoPoint;
import PLMS.Utils.MessageOperation;
import org.apache.mina.core.buffer.IoBuffer;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.EnumMap;

/**
 * 用户数据分区包括两部分
 * （1）1个4字节的数据单元标识（DA表示要采集哪些设备，DT表示要采集这些设备的哪些信息）
 * （2）多个（0-8个）Fn所对应的数据单元
 */
public class UserDataFragment {

    //存放信息类员数据对象的集合
    public EnumMap<EnumFn, AbstractFn> fnObjects = new EnumMap<EnumFn, AbstractFn>(EnumFn.class);
    //信息点集合
    public ArrayList<EnumInfoPoint> points = new ArrayList<EnumInfoPoint>(0);
    public ArrayList<EnumFn>  fnLists=new ArrayList<EnumFn>(0);
    // ----------------------------------------------------------------------
    private final static int DATA_INDENTIFY_LENGTH = 4;  //数据单元标识长度
    /**
     * DA2和DA1用来表示与终端连接的电表/采集点，总共为64个
     */
    private byte da2 = 0;  //信息点组
    private byte da1 = 0;  //信息点元
    private byte dt2 = 0;  //信息类组
    private byte dt1 = 0;    //信息类元


    public static int getDataIndentifyLength() {
        return DATA_INDENTIFY_LENGTH;
    }

    public EnumMap<EnumFn, AbstractFn> getFnObjects() {
        return fnObjects;
    }

    public ArrayList<EnumInfoPoint> getPoints() {
        return points;
    }

    // ------------------------构造函数------------------------------------------------
    public UserDataFragment() {

    }
    public UserDataFragment(byte da1,byte da2,byte dt1,byte dt2){

    }
    public UserDataFragment(byte[] in) {
                init(in);
    }
     //---------------------------------------------------------------------------------
    /**
     * 初始化
     * @param in
     */
    public  void init(byte[] in){
        ByteBuffer buf= ByteBuffer.wrap(in);
        da1 = in[0];
        da2 = in[1];
        dt1 = in[2];
        dt2 = in[3];
        setPoints(da1,da2);
        setFn(  dt1,  dt2);

    }
    /**
     * 判断是否包含有fn
     */
     public boolean containFn(EnumFn fn){

        for(EnumFn f:fnObjects.keySet()){
               if(  f==fn){
                  return true;
               }
        }
        return false;
    }

    /**
     *  根据类返回Fn
     * @param clazz
     * @param <T>
     * @return
     */
    public <T extends AbstractFn> T getFnByName(Class<T>  clazz)
    {
        for(EnumFn fn:fnObjects.keySet()){

        }
        for(AbstractFn fn:fnObjects.values()){
                if(fn.getClass() == clazz){
                       return (T)fn;
                }
          }
        return null;
    }

    /**
     *  设置信息点
     */
    public void setPoints(byte da1,byte da2) {
        // ------------总共为8组*8个=64个信息点---------------
       this.da1 = da1;   //信息点员 ，每一位代表一个信息点，共8个
       this.da2 = da2;   //信息点组 ，每一位代表一组，共8组

        for (int i = 0; i <= 7; i++) {
            // 每次取 da2的一位
            int da2_bit = (da2 >> i) & 1;
            if (da2_bit == 1) {
                // 该信息点组包含有
                for (int j = 0; j < 7; j++) {
                    //每次取da1的一位
                    int da1_bit= ( da1 >> j) & 1;
                    if(da1_bit==1)
                    {
                        // 所有行，所在列对应的信息点
                        int v = 8 * i + j + 1;
                       //this.addMeasuredPoint(v);
                    }
                }
            }
        }
    }

    /**
     *  设置dt
     *
     */
    public void setFn(byte dt1,byte dt2) {
        // -------最多可以设置1个组中的8个信息元---------------
        this.dt1 = dt1;    // 信息类元，每位代表一个信息类员，共8个
        this.dt2 = dt2;    // 信息类组，值代表一组，共可以表示256组 ，但其值最大只能为30
        for (int i = 0; i <= 7; i++) {
            //每次取da1的一位
            int col_bit;
            col_bit = dt2 * 8 + (dt1 >> i) & 1;
            if(col_bit==1){
                 // fnObjects.put();
                // fnLists.add(EnumFn.F01);
            }
        }
    }

    /**
     * 添加所有的信息点（即P1-P63)
     */
    public void addAllMeasuredPoint() {
        for (EnumInfoPoint p : EnumInfoPoint.values()) {
            if (p.valueOf() != 0) {
                addMeasuredPoint(p);
            }
        }
    }
    /**
     * 清除所有的信息点（即设置为P0)
     */
    public void clearAllMeasuredPoint() {
        da2 = 0;
        da1 = 0;
        points.clear();
    }
    /**
     * 添加一个信息点
     * @param pv pv=1表示p1信息点
     */
    public void addMeasuredPoint(int pv) {

        if (pv > 0) {
            // 根据p的数值计算，在DA中的对应位置
            int row = pv / 8;
            int col = pv % 8 - 1;
            // 将原来的da2的对应位设置为1
            int newda2 = (int) da2;
            newda2 = newda2 | (1 << row);
            da2 = (byte) newda2;
            //将原来的da1的对应位设置为1
            int newda1 = (int) da1;
            newda1 = newda1 | (1 << col);
            da1 = (byte) newda1;
        } else {
            da2 = 0;
            da1 = 0;
        }
    }

    /**
     * 添加采集信息点
     * @param point
     */
    public void addMeasuredPoint(EnumInfoPoint ... point) {
        // 根据p的数值计算，在DA中的对应位置
        for(EnumInfoPoint i:point){
            this.addMeasuredPoint( i.valueOf());
            points.add(i);
        }
    }

    /**
     * 根据枚举值，添加一个fn数据
     * @param afn :当前的功能吗
     * @param f:  该功能码下的一个fn
     * @return Fn的子类
     */
    public AbstractFn addFn(AFN afn, EnumFn f) {

        int fv = f.valueOf();
        // 根据fn的数值，计算对应的Dt2和dt1
        int col = fv % 8 - 1;
        int row = fv / 8;
        dt2 = (byte) row;
        int old_dt1 = ((int) dt1) | (1 << col);
        dt1 = (byte) old_dt1;
        // 将EnumFn.Fn与实例对象作为一个(key,Value)保存
        AbstractFn fn = MessageOperation.createFnInstance(afn, f);
        fnObjects.put(f, fn);

        return fn;
    }

    /**
     * 添加一个Fn对象
     * @param 、、afn
     * @param
     * @param <T>
     * @return
     */
    public <T extends AbstractFn> T addFn(EnumFn f, T fn) {

        fnObjects.put(f, fn);
        return fn;
    }

    // ------------------------------------------------------------------------
    public int length() {
        int len = 0;
        for(AbstractFn e:fnObjects.values()){
            len = len + e.length();
        }
        return this.DATA_INDENTIFY_LENGTH + len;
    }


    public byte[] valueOf() {
        IoBuffer buf = IoBuffer.allocate(4).setAutoExpand(true).setAutoShrink(true);
    /*    for (EnumInfoPoint p : points) {
            int pv = p.valueOf();
            this.addMeasuredPoint(pv);
        }*/

        buf.put(da1);
        buf.put(da2);
        buf.put(dt1);
        buf.put(dt2);
        //buf.putShort((short)0xffff);
        // 将DT指示的所有信息类
        for (AbstractFn e : fnObjects.values()) {
            if (e.length() != 0)
                buf.put(e.valueOf());
        }
        buf.flip();
        //buf.shrink();
        byte[] bs = new byte[buf.limit()];
        buf.get(bs);
        return bs;
    }


}
