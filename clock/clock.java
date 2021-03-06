/*
ID: jmg20482
LANG: JAVA
TASK: clock
*/

import java.io.*;
import java.util.*;
import java.lang.*;

public class clock
{
    public static void main(String[] args) throws IOException
    {
	BufferedReader in=new BufferedReader(new FileReader("clock.in"));
	PrintWriter out = new PrintWriter(new FileWriter("clock.out"));

	String time = in.readLine();
	StringTokenizer tokenizer = new StringTokenizer(time,":");
	int hour=Integer.parseInt(tokenizer.nextToken());
	int minute=Integer.parseInt(tokenizer.nextToken());

	String[] textClockH = {"one","One","two","Two","three","Three","four","Four","five","Five","six","Six","seven","Seven","eight","Eight","nine","Nine","ten","Ten","eleven","Eleven","twelve","Twelve"};

	String[] textClockM = {"o'clock","one","two","three","four","five","six","seven","eight","nine","ten","eleven","twelve","thirteen","fourteen","Quarter past","sixteen","seventeen","eighteen","nineteen","twenty","twenty-one","twenty-two","twenty-three","twenty-four","twenty-five","twenty-six","twenty-seven","twenty-eight","twenty-nine","thirty","thirty-one","thirty-two","thirty-three","thirty-four","thirty-five","thirty-six","thirty-seven","thirty-eight","thirty-nine","forty","forty-one","forty-two","forty-three","forty-four","Quarter to","Fourteen to","Thirteen to","Twelve to","Eleven to","Ten to","Nine to","Eight to","Seven to","Six to","Five to","Four to","Three to","Two to","One to"};

	if(minute >= 45)
	{
	    if(hour == 12)
		out.println(textClockM[minute] + " one");
	    else
		out.println(textClockM[minute] + " " + textClockH[hour*2]);
	}
	else
	{
	    if(minute == 15)
		if(hour == 1)
		    out.println(textClockM[minute] + " one");
		else
		    out.println(textClockM[minute] + " " + textClockH[hour*2-2]);
	    else
		out.println(textClockH[hour*2-1] + " " + textClockM[minute]);
	}

	in.close();
	out.close();
	System.exit(0);
    }
}
