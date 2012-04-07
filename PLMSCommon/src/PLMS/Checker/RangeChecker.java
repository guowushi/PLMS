package PLMS.Checker;
public abstract class RangeChecker implements IChecker
{
    protected String tips;
    public String getTips(){
        return tips;
    }
    public void setTips(String tips){
        this.tips=tips;
    }
}

