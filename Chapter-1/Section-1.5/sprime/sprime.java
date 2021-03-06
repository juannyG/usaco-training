/*
ID: jmg20482
LANG: JAVA
TASK: sprime
*/

import java.io.*;
import java.lang.*;
import java.util.*;

public class sprime
{
    public static void main(String[] args) throws IOException
    {
	BufferedReader in = new BufferedReader(new FileReader("sprime.in"));
	PrintWriter out = new PrintWriter(new FileWriter("sprime.out"));

	int n = Integer.parseInt(in.readLine());
	int[] primes = {1,2,3,5,7,9};

	if(n==1)
	    superPrime1(out,primes);
	else if(n==2)
	    superPrime2(out,primes);
	else if(n==3)
	    superPrime3(out,primes);
	else if(n==4)
	    superPrime4(out,primes);
	else if(n==5)
	    superPrime5(out,primes);
	else if(n==6)
	    superPrime6(out,primes);
	else if(n==7)
	    superPrime7(out,primes);
	else
	    superPrime8(out,primes);

	in.close();
	out.close();
	System.exit(0);
    }

    public static void superPrime1(PrintWriter out, int[] primes)
    {
	for(int i=1; i < primes.length; ++i)
	    out.println(primes[i]);
	return;
    }

    public static void superPrime2(PrintWriter out, int[] primes)
    {
	for(int i=1; i < 5; ++i)
	{
	    int d1=primes[i];
	    for(int j=0; j < 6; ++j)
	    {
		int d2=primes[j];
		int sPrime = d1*10 + d2;
		if(sieve(sPrime))
		    out.println(sPrime);
	    }
	}
    }

    public static void superPrime3(PrintWriter out, int[] primes)
    {
	for(int i=1; i < 5; ++i)
	{
	    int d1=primes[i];
	    for(int j=0; j < 6; ++j)
	    {
		int d2=primes[j];
		for(int k=0; k < 6; ++k)
		{
		    int d3=primes[k];
		    int rib1=d1*100 + d2*10 + d3;
		    int rib2=d1*10 + d2;
		    if(sieve(rib1) && sieve(rib2) && d3!=2)
			out.println(rib1);
		}
	    }
	}
    }

    public static void superPrime4(PrintWriter out, int[] primes)
    {
	for(int i=1; i < 5; ++i)
	{
	    int d1=primes[i];
	    for(int j=0; j < 6; ++j)
	    {
		int d2=primes[j];
		for(int k=0; k < 6; ++k)
		{
		    int d3=primes[k];
		    for(int l=0; l < 6; ++l)
		    {
			int d4=primes[l];
			int rib1=d1*1000 + d2*100 + d3*10 + d4;
			int rib2=d1*100 + d2*10 + d3;
			int rib3=d1*10 + d2;
			if(sieve(rib3) && sieve(rib2) && sieve(rib1) && d4!=2)
			    out.println(rib1);
		    }
		}
	    }
	}
    }

    public static void superPrime5(PrintWriter out, int[] primes)
    {
	for(int i=1; i < 5; ++i)
	{
	    int d1=primes[i];
	    for(int j=0; j < 6; ++j)
	    {
		int d2=primes[j];
		for(int k=0; k < 6; ++k)
		{
		    int d3=primes[k];
		    for(int l=0; l < 6; ++l)
		    {
			int d4=primes[l];
			for(int m=0; m < 6; ++m)
			{
			    int d5=primes[m];
			    int rib1=d1*10000 + d2*1000 + d3*100 + d4*10 + d5;
			    int rib2=d1*1000 + d2*100 + d3*10 + d4;
			    int rib3=d1*100 + d2*10 + d3;
			    int rib4=d1*10 + d2;
			    if(sieve(rib4) && sieve(rib3) && sieve(rib2) && sieve(rib1) && d5!=2)
				out.println(rib1);
			}
		    }
		}
	    }
	}
    }

    public static void superPrime6(PrintWriter out, int[] primes)
    {
	for(int i=1; i < 5; ++i)
	{
	    int d1=primes[i];
	    for(int j=0; j < 6; ++j)
	    {
		int d2=primes[j];
		for(int k=0; k < 6; ++k)
		{
		    int d3=primes[k];
		    for(int l=0; l < 6; ++l)
		    {
			int d4=primes[l];
			for(int m=0; m < 6; ++m)
			{
			    int d5=primes[m];
			    for(int n=0; n < 6; ++n)
			    {
				int d6=primes[n];
				int rib1=d1*100000 + d2*10000 + d3*1000 + d4*100 + d5*10 + d6;
				int rib2=d1*10000 + d2*1000 + d3*100 + d4*10 + d5;
				int rib3=d1*1000 + d2*100 + d3*10 + d4;
				int rib4=d1*100 + d2*10 + d3;
				int rib5=d1*10 + d2;
				if(sieve(rib5) && sieve(rib4) && sieve(rib3) && sieve(rib2) && sieve(rib1) && d6!=2)
				    out.println(rib1);
			    }
			}
		    }
		}
	    }
	}
    }

    public static void superPrime7(PrintWriter out, int[] primes)
    {
	for(int i=1; i < 5; ++i)
	{
	    int d1=primes[i];
	    for(int j=0; j < 6; ++j)
	    {
		int d2=primes[j];
		for(int k=0; k < 6; ++k)
		{
		    int d3=primes[k];
		    for(int l=0; l < 6; ++l)
		    {
			int d4=primes[l];
			for(int m=0; m < 6; ++m)
			{
			    int d5=primes[m];
			    for(int n=0; n < 6; ++n)
			    {
				int d6=primes[n];
				for(int p=0; p < 6; ++p)
				{
				    int d7=primes[p];
				    int rib1=d1*1000000 + d2*100000 + d3*10000 + d4*1000 + d5*100 + d6*10 + d7;
				    int rib2=d1*100000 + d2*10000 + d3*1000 + d4*100 + d5*10 + d6;
				    int rib3=d1*10000 + d2*1000 + d3*100 + d4*10 + d5;
				    int rib4=d1*1000 + d2*100 + d3*10 + d4;
				    int rib5=d1*100 + d2*10 + d3;
				    int rib6=d1*10 + d2;
				    if(sieve(rib6) && sieve(rib5) && sieve(rib4) && sieve(rib3) && sieve(rib2) && sieve(rib1) && d7!=2)
					out.println(rib1);
				}
			    }
			}
		    }
		}
	    }
	}
    }

    public static void superPrime8(PrintWriter out, int[] primes)
    {
	for(int i=1; i < 5; ++i)
	{
	    int d1=primes[i];
	    for(int j=0; j < 6; ++j)
	    {
		int d2=primes[j];
		for(int k=0; k < 6; ++k)
		{
		    int d3=primes[k];
		    for(int l=0; l < 6; ++l)
		    {
			int d4=primes[l];
			for(int m=0; m < 6; ++m)
			{
			    int d5=primes[m];
			    for(int n=0; n < 6; ++n)
			    {
				int d6=primes[n];
				for(int p=0; p < 6; ++p)
				{
				    int d7=primes[p];
				    for(int q=0; q < 6; ++q)
				    {
					int d8=primes[q];
					int rib1=d1*10000000 + d2*1000000 + d3*100000 + d4*10000 + d5*1000 + d6*100 + d7*10 + d8;
					int rib2=d1*1000000 + d2*100000 + d3*10000 + d4*1000 + d5*100 + d6*10 + d7;
					int rib3=d1*100000 + d2*10000 + d3*1000 + d4*100 + d5*10 + d6;
					int rib4=d1*10000 + d2*1000 + d3*100 + d4*10 +d5;
					int rib5=d1*1000 + d2*100 + d3*10 + d4;
					int rib6=d1*100 + d2*10 + d3;
					int rib7=d1*10 + d2;
					if(sieve(rib7) && sieve(rib6) && sieve(rib5) && sieve(rib4) && sieve(rib3) && sieve(rib2) && sieve(rib1) &&  d5!=2)
					    out.println(rib1);
				    }
				}
			    }
			}
		    }
		}
	    }
	}
    }

    public static boolean sieve(int sPrime)
    {
	int root=(int)Math.sqrt(sPrime);
	for(int k=2; k <= root; ++k)
	    if((sPrime%k)==0)
		return false;
	    else if(k > 2)
		++k;
	return true;
    }
}
