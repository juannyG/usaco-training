/*
ID: jmg20482
LANG: JAVA
TASK: milk
*/

import java.io.*;
import java.lang.*;
import java.util.*;

public class milk
{
    public static void main(String[] args) throws IOException
    {
		BufferedReader in = new BufferedReader(new FileReader("milk.in"));
		PrintWriter out = new PrintWriter(new FileWriter("milk.out"));

		String text = in.readLine();
		StringTokenizer tokenizer = new StringTokenizer(text);
		int n = Integer.parseInt(tokenizer.nextToken());
		int m = Integer.parseInt(tokenizer.nextToken());

		final int ARRAY = m;

		farmer[] farms = new farmer[ARRAY];

		for(int i=0; i < m; ++i)
		{
			text = in.readLine();
			tokenizer = new StringTokenizer(text);
			int price = Integer.parseInt(tokenizer.nextToken());
			int milk = Integer.parseInt(tokenizer.nextToken());
			farms[i] = new farmer(price,milk);
		}

		int minPrice = greedyMilk(farms,n,m);
		out.println(minPrice);
	
		out.close();
		in.close();
		System.exit(0);
    }

    public static int greedyMilk(farmer[] farms, int n, int m)
    {
		int temp = n;
		int totalCost=0;

		while(temp > 0)
		{
			int minCost=1000;
			int i=0;
			for(int j=0; j < m; ++j)
			if(!(farms[j].flag))
			{
				if(farms[j].p <= minCost)
				{    
					minCost=farms[j].p;
					i=j;
				}
			}
			farms[i].flag = true;
			if(temp <= farms[i].m)
			{
			totalCost+=(temp*farms[i].p);
			temp-=temp;
			}
			else
			{
			totalCost+=(farms[i].m*farms[i].p);
			temp-=farms[i].m;
			}
		}
		return totalCost;
    }
}

class farmer
{
    boolean flag;
    int p;
    int m;
    
    public farmer(int price, int gallons)
    {
		p=price;
		m=gallons;
		flag=false;
    }
}
