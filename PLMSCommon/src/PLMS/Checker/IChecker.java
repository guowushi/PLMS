package PLMS.Checker;
public interface IChecker{

	public boolean valid(String text);
    public String getTips();
    public void setTips(String tips);
}