package PLMS.Checker;
import java.awt.Color;
import java.util.*;
class CheckerSet<T> extends LinkedHashSet
{
	public CheckerSet(){
		super();
	}
	public CheckerSet(Collection<? extends T> c){
		super(c);
	}
	public CheckerSet(int initialCapacity){
		super(initialCapacity);
	}
	public CheckerSet(int initialCapacity,float loadFactor){
		super(initialCapacity,loadFactor);
	}
	private Color oldBackground;
	private Color promptBackground;
	public Color getOldBackground()
	{
		return oldBackground;
	}
	public void setOldBackground(Color c){
		oldBackground=c;
	}
	public Color getPromptBackground()
	{
		return promptBackground;
	}
	public void setPromptBackground(Color c){
		promptBackground=c;
	}
}