/*
ID: jmg20482
LANG: JAVA
TASK: checker
*/

import java.io.*;
import java.lang.*;
import java.util.*;

public class checker
{
    public static void main(String[] args) throws IOException
    {
	BufferedReader in = new BufferedReader(new FileReader("checker.in"));
	PrintWriter out = new PrintWriter(new FileWriter("checker.out"));

	int n = Integer.parseInt(in.readLine());

	final int N = n;
	
	int[] solution = new int[N];
	boolean[] rowok=new boolean[N];

	for(int i=0; i < n; ++i)
	    solution[i]=(-1);

	int[] numSolution=new int[1];
	numSolution[0]=0;

	search(0,n,solution,numSolution,out,rowok);

	out.println(numSolution[0]);
	
	in.close();
	out.close();
	System.exit(0);
    }

    public static void search(int col, int max, int[] solution, int[] numSolution, PrintWriter out, boolean[] rowok)
    {
	if(col == max)
	{
	    ++numSolution[0];
	    if(numSolution[0] > 3)
		return;
	    else
	    {
		for(int i=0; i < max-1; ++i)
		    out.print((solution[i])+1 + " ");
		out.print(solution[max-1]+1);
		out.println();
		return;
	    }
	}

	for(int row=0; row < max; ++row)
	{
	    if(!(rowok[row]) && canPlaceQueen(row,col,solution,max))
	    {
		solution[col]=row;
		rowok[row]=true;
		search(col+1,max,solution,numSolution,out,rowok);
		solution[col]=-1;
		rowok[row]=false;
	    }
	}
    }

    public static boolean canPlaceQueen(int row, int col, int[] solution, int n)
    {
	int diag1 = n-row+col;
	int diag2 = row+col;

	for(int i=0; i < col; ++i)
	{
	    int solDiag1 = n-solution[i]+i;
	    int solDiag2 = solution[i]+i;
	    if(diag1 == solDiag1)
		return false;
	    else if(diag2 == solDiag2)
		return false;
	}
	
	return true;
    }
}
