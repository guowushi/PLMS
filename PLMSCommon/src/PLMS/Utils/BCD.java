package PLMS.Utils;

import PLMS.Exceptions.ParameterInvalidException;

/**
 * BCD编码类
 */
public class BCD {
    public short getValue() {
        return value;
    }

    public void setValue(short value) {
        if (value > 9 )
        {  throw new IllegalArgumentException();}
        else
        {  this.value = value;}
    }

    private  short  value;
    /**
     * 获取num中小数点的左边（右边）第position位的数值
     * @exception ParameterInvalidException
     * @param num:数值
     * @param position:
     * @return num中小数点左边的第position位
     */
    public static int getBCDBit(double num,int position)
            throws ParameterInvalidException{
        if(position==0){
            throw new ParameterInvalidException("不能取小数点左边第0位");
        }
        String str=num+"";
        String [] nums=str.split("\\.")  ;

        int ret=0;
        if(position>0){
            if(nums[0].length()-position<0)
                ret=0;
            else
                ret  =nums[0].charAt(nums[0].length()-position)-'0';
        }
        else if(position<0) {
            if(nums[1]==null || Math.abs(position)>nums[1].length())
                ret=0;
            else
                ret=nums[1].charAt(Math.abs(position)-1)-'0';
        }
        return ret;

    }



}
