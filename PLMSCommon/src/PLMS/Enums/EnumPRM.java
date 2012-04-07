package PLMS.Enums;

/**
 * PRM
 */
public enum EnumPRM {
    FROM_SLAVE_STATION(0),     //从站
    FROM_PRIM_STATION(1);      // 主站
    //-----------------------------------------
    private  int code;
    private EnumPRM(int v) {
       this.code=v; 
    }
    public int valueOf(){
         return this.code;
    }
}
