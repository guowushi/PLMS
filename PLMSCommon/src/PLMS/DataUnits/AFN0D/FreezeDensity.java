package PLMS.DataUnits.AFN0D;

/**
 * 冻结密度
 */
public enum FreezeDensity {

     NONE(0), //   不冻结 
     Min15(1), //   每隔15分钟
    Min30(2), //   每隔30分钟
    Min60(3); //   每隔60分钟
  private int value;
   private FreezeDensity(int v){
       this.value=v;
   }
   public void setValue(int v)
   {
       this.value=v;
   }
    public int valueOf(){
        return this.value;
    }
}
