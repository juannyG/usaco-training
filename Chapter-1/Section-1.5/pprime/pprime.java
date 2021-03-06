/*
ID: jmg20482
LANG: JAVA
TASK: pprime
*/

import java.io.*;
import java.util.*;
import java.lang.*;

public class pprime
{
    public static void main(String[] args) throws IOException
    {
	BufferedReader in = new BufferedReader(new FileReader("pprime.in"));
	PrintWriter out = new PrintWriter(new FileWriter("pprime.out"));

	String text = in.readLine();
	StringTokenizer tokenizer = new StringTokenizer(text);
	int a = Integer.parseInt(tokenizer.nextToken());
	int b = Integer.parseInt(tokenizer.nextToken());

	int tempA = a;
	int tempB = b;
	int ndigA=0;
	int ndigB=0;
	while(tempA != 0)
	{
	    ++ndigA;
	    tempA/=10;
	}
	while(tempB != 0)
	{
	    ++ndigB;
	    tempB/=10;
	}

	if(ndigA == 1)
	    genPal1(a,b,ndigB,out);
	else if(ndigA == 2)
	    genPal2(a,b,ndigB,out);
	else if(ndigA == 3)
	    genPal3(a,b,ndigB,out);
	else if(ndigA == 4)
	    genPal4(a,b,ndigB,out);
	else if(ndigA == 5)
	    genPal5(a,b,ndigB,out);
	else if(ndigA == 6)
	    genPal6(a,b,ndigB,out);
	else if(ndigA == 7)
	    genPal7(a,b,ndigB,out);
	else if(ndigA == 8)
	    genPal8(a,b,ndigB,out);
	
	in.close();
	out.close();
	System.exit(0);
    }

    public static void genPal1(int a, int b, int ndigB, PrintWriter out)
    {
	int pal = a;
	while(pal < 10)
	{
	    if((pal%2)==0 || (pal%3)==0)
		++pal;
	    else
	    {
		out.println(pal);
		++pal;
	    }
	}
	if(ndigB >= 2)
	    genPal2(a,b,ndigB,out);
    }

    public static void genPal2(int a, int b, int ndigB, PrintWriter out)
    {
	for(int d1=1; d1 <= 9; d1+=2)
        {
	    int pal = d1*10 + d1;
	    if(pal > b)
		return;
	    else if(pal >= a)
	    {
		boolean isPrime = sieve(pal,a);
		if(isPrime)
		    out.println(pal);
		if(d1 == 3)
		    d1=5;
	    }
	}
	if(ndigB >= 3)
	    genPal3(a,b,ndigB,out);
    }

    public static void genPal3(int a, int b, int ndigB, PrintWriter out)
    {
	for(int d1=1; d1 <= 9; d1+=2)
	{
	    for(int d2=0; d2 <= 9; ++d2)
	    {
		int pal = d1*100 + d2*10 + d1;
		if(pal > b)
		    return;
		else if(pal >= a)
		{
		    boolean isPrime = sieve(pal,a);
		    if(isPrime)
			out.println(pal);
		}
	    }
	    if(d1 == 3)
		d1=5;
	}
	if(ndigB >= 4)
	    genPal4(a,b,ndigB,out);
    }

    public static void genPal4(int a, int b, int ndigB, PrintWriter out)
    {
	for(int d1=1; d1 <= 9; d1+=2)
	{
	    for(int d2=0; d2 <= 9; ++d2)
	    {
		int pal = d1*1000 + d2*100 + d2*10 + d1;
		if(pal > b)
		    return;
		else if(pal >= a)
	        {
		    boolean isPrime = sieve(pal,a);
		    if(isPrime)
			out.println(pal);
		}
	    }
	    if(d1 == 3)
		d1 = 5;
	}
	if(ndigB >= 5)
	    genPal5(a,b,ndigB,out);
    }

    public static void genPal5(int a, int b, int ndigB, PrintWriter out)
    {
	for(int d1=1; d1 <= 9; d1+=2)
	{
	    for(int d2=0; d2 <= 9; ++d2)
		for(int d3=0; d3 <= 9; ++d3)
		{
		    int pal = d1*10000 + d2*1000 + d3*100 + d2*10 + d1;
		    if(pal > b)
			return;
		    else if(pal >= a)
		    {
			boolean isPrime = sieve(pal,a);
			if(isPrime)
			    out.println(pal);
		    }
		}
	    if(d1 == 3)
		d1=5;
	}
	if(ndigB >= 6)
	    genPal6(a,b,ndigB,out);
    }

    public static void genPal6(int a, int b, int ndigB, PrintWriter out)
    {
	for(int d1=1; d1 <= 9; d1+=2)
	{
	    for(int d2=0; d2 <= 9; ++d2)
		for(int d3=0; d3 <= 9; ++d3)
		{
		    int pal = d1*100000 + d2*10000 + d3*1000 + d3*100 + d2*10 + d1;
		    if(pal > b)
			return;
		    else if(pal >= a)
		    {
			boolean isPrime = sieve(pal,a);
			if(isPrime)
			    out.println(pal);
		    }
		}
	    if(d1==3)
		d1=5;
	}
	if(ndigB >= 7)
	    genPal7(a,b,ndigB,out);
    }

    public static void genPal7(int a, int b, int ndigB, PrintWriter out)
    {
	for(int d1=1; d1 <= 9; d1+=2)
	{
	    for(int d2=0; d2 <= 9; ++d2)
		for(int d3=0; d3 <= 9; ++d3)
		    for(int d4=0; d4 <= 9; ++d4)
		    {
			int pal = d1*1000000 + d2*100000 + d3*10000 + d4*1000 + d3*100 + d2*10 + d1;
			if(pal > b)
			    return;
			else if(pal >= a)
			{
			    boolean isPrime = sieve(pal,a);
			    if(isPrime)
				out.println(pal);
			}
		    }
	    if(d1 == 3)
		d1=5;
	}
	if(ndigB >= 8)
	    genPal8(a,b,ndigB,out);
    }

    public static void genPal8(int a, int b, int ndigB, PrintWriter out)
    {
	for(int d1=1; d1 <= 9; d1+=2)
	{
	    for(int d2=0; d2 <= 9; ++d2)
		for(int d3=0; d3 <= 9; ++d3)
		    for(int d4=0; d4 <= 9; ++d4)
		    {
			int pal = d1*10000000 + d2*1000000 + d3*100000 + d4*10000 + d4*1000 + d3*100 + d2*10 + d1;
			if(pal > b)
			    return;
			else if(pal >= a)
			{
			    boolean isPrime = sieve(pal,a);
			    if(isPrime)
				out.println(pal);
			}
		    }
	    if(d1 == 3)
		d1=5;
	}
    }

    public static boolean sieve(int pal, int a)
    {
	int root = (int)Math.sqrt(pal);
	for(int k=3; k <= root; k+=2)
	    if((pal%k) == 0)
		return false;
	return true;
    }
}
