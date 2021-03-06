/*
ID: jmg20482
LANG: JAVA
TASK: gift1
*/

import java.io.*;
import java.util.*;

public class gift1
{
    public static void main(String[] args) throws IOException
    {
	BufferedReader in = new BufferedReader(new FileReader("gift1.in"));
	PrintWriter out = new PrintWriter(new FileWriter("gift1.out"));

	String NP;
	giftGiver[] givers = new giftGiver[10];
	for(int i=0; i < 10; ++i)
	    givers[i] = new giftGiver("0");

	NP = in.readLine();
	int np = Integer.parseInt(NP);
	for(int a=0; a < np; ++a)
	    givers[a] = new giftGiver(in.readLine());
	
	String name;
	while(in.ready())
	{
	    name = in.readLine();
	    int ref = searchList(givers,name);

	    String info=in.readLine();
	    StringTokenizer tokenizer = new StringTokenizer(info);
	    
	    int giftTotal=0;
	    while(tokenizer.hasMoreTokens())
	    {
		giftTotal = Integer.parseInt(tokenizer.nextToken());
		givers[ref].NG = Integer.parseInt(tokenizer.nextToken());
	    }

	    if(givers[ref].NG == 0)
		{}
	    else
	    {
		int giftAmt=giftTotal/givers[ref].NG;
		String giftRcvr;
		int ref2;
		for(int x=0; x < givers[ref].NG; ++x)
		{
		    giftRcvr = in.readLine();
		    ref2 = searchList(givers,giftRcvr);
		    givers[ref2].account+=giftAmt;
		}
		givers[ref].account-=giftTotal;
		if( (giftTotal%givers[ref].NG) != 0)
		    givers[ref].account+=(giftTotal%givers[ref].NG);
	    }
	}

	for(int j=0; j < np; ++j)
	    out.println(givers[j].name + " " + givers[j].account);

	out.close();
	in.close();
	System.exit(0);
    }

    public static int searchList(giftGiver[] a, String giver)
    {
	int i=0;
	while(a[i].name!="0")
	{
	    if(giver.compareTo(a[i].name) == 0)
		return i;
	    ++i;
	}
	System.out.println(i);
	return i;
    }
}

class giftGiver
{
    public giftGiver(String listName)
    {
	name=listName;
	account=0;
	NG=0;
    }

    String name;
    int account;
    int NG;
}
