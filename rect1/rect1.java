/*
ID: jmg20482
LANG: JAVA
TASK: rect1
*/

import java.io.*;
import java.lang.*;
import java.util.*;

public class rect1
{
    public static void main(String[] args) throws IOException
    {
	BufferedReader in = new BufferedReader(new FileReader("rect1.in"));
	PrintWriter out = new PrintWriter(new FileWriter("rect1.out"));

	String text = in.readLine();
	StringTokenizer tokenizer = new StringTokenizer(text);
       
	int a = Integer.parseInt(tokenizer.nextToken());
	int b = Integer.parseInt(tokenizer.nextToken());
	int n = Integer.parseInt(tokenizer.nextTokne());

	final int N = n;
	final int coordLen=2*N;

	int[][] colorTotals = new int[N][2];
	int[][] coord = new int[coordLen][2];

	coord[0][0]=0;
	coord[0][1]=0;
	coord[1][0]=n;
	coord[1][1]=n;

	colorTotals[0][0]=1;
	colorTotals[0][1]=n*n;

	int j=2;
	for(int i=1; i < n; ++i)
	{
	    text = in.readLine();
	    tokenizer = new StringTokenizer(text);

	    coord[j][0] = tokenizer.nextToken();
	    coord[j][1] = tokenizer.nextToken();
	    ++j;
	    coord[j][0] = tokenizer.nextToken();
	    coord[j][1] = tokenizer.nextToken();
	    ++j;
	    colorTotals[i][0] = Integer.parseInt(tokenizer.nextToken());
	    colorTotals[i][1] = (coord[j-1][0]-coord[j-2][0])*(coord[j-1][1]-coord[j-2]);

	    if(i > 1)
	    {
		int llx=coord[j-2][0];
		int urx=coord[j-1][0];
		int lly=coord[j-2][1];
		int ury=coord[j-1][1];
		for(int x=j-3; x >= 1; --x)
		{
		    if(lly < coord[x-1][1])
			if(llx < coord[x-1][0] && coord[x-1][0]< urx)
			{
			    int rectLost=(urx-coord[x-1][0])*(ury-coord[x-1][1]);
			    colorTotals[i-1][1]-=rectLost;
			}
		    else if(lly < coord[x][1])
		    {
			
		    }

		    else if(coord[x-1][1] < lly)
		    {
		    }

		    else if(coord[x-1][1] < ury)
		    {
		    }
		}
	    }
	    else
	    {
		colorTotals[1][1] = (coord[1][0]-coord[0][0])*(coord[1][1]-coord[0][1]);
		colorTotals[0][1]-=colorTotals[1][1];
	    }
	}    
    }
}
