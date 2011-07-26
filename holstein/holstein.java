/*
ID: jmg20482
LANG: JAVA
TASK: holstein
*/

import java.io.*;
import java.lang.*;
import java.util.*;

public class holstein {
    
    public int g;
    public int v;
    public int[] min_vit_reqs;
    public int[][] feed_vit_val;
    public int scoops;
    public List<Integer> feed_path;
    
    public holstein() {
        this.scoops = Integer.MAX_VALUE;
        this.feed_path = new ArrayList();
    }
    
    private void print_state() {}
    
    private void kick_start_search() {
        for (int i=0; i < this.g; ++i) {
            int[] min_vit_copy = this.min_vit_reqs.clone();
            this.search_variations(min_vit_copy, i, new ArrayList(), 0, new ArrayList());
            System.out.println();
        }
    }
    
    /* 
        We need to also keep track of the "winning" vit_val. Why?
        
        5
        10 20 30 40 50
        5
        10 10 10 10 10
        0 10 10 10 10
        0 0 10 10 10
        0 0 0 10 10
        0 0 0 0 10
        
        yields a result of "5 1 2 3 4 5"
        While "5 1 1 1 1 1" is valid, this results in excess values for vitamins 1 - 4.

        Also, we need to check solutions of the same minimum length, because, again,
        the solution may yield a better distribution of vitamin consumption, therefore
        if (scoops > this.scoops) { return; } was changed to if (scoops >= this.scoops) { return; }
        The final check for overriding a given solution is that we're using "the smallest feedtype numbers"
    */
    private void search_variations(int[] vit_val, int feed_type, List<Integer> feed_path, int scoops, List<Integer> feed_zero_skipped) {

        ++scoops;
        if (scoops > this.scoops) { return; }
        
        int[] vit_val_copy = vit_val.clone();
        this.apply_feed(vit_val_copy, feed_type);

        feed_path.add(feed_type);
        List<Integer> feed_list = this.copy_list(feed_path);

        for (Integer p : feed_list) { System.out.print(p+" "); }
        System.out.println();

        if (this.finished(vit_val_copy, feed_type) == true) {
            if (this.zero_check(vit_val_copy, feed_type) == true) {
                this.scoops = scoops;
                this.feed_path = this.copy_list(feed_path);
                return;
            } else { feed_zero_skipped.add(feed_type); }
        }

        for (int i=0; i < this.g; ++i) {
            if (feed_zero_skipped.contains(i) == true) { continue; }
            int[] vit_val_recursive_copy = vit_val_copy.clone();
            List<Integer> recursive_feed_list = this.copy_list(feed_list);
            List<Integer> recursive_zero_skipped = this.copy_list(feed_zero_skipped);
            this.search_variations(vit_val_recursive_copy, i, recursive_feed_list, scoops, recursive_zero_skipped);
        }
        
        if (feed_path.size() > 0) { feed_path.remove(feed_path.size()-1); }
        if (feed_zero_skipped.size() > 0) { feed_zero_skipped.remove(feed_zero_skipped.size()-1); }
    }
    
    private List copy_list(List<Integer> list) {
        List<Integer> l = new ArrayList();
        for (Integer i : list) { l.add(i); }
        return l;
    }
    
    private boolean finished(int[] vit_val, int feed_type) {
        for (int i=0; i < vit_val.length; ++i) {
            if (this.feed_vit_val[feed_type][i] == 0) { continue; }
            if (vit_val[i] > 0) { return false; }
        }
        return true;
    }
    
    private boolean zero_check(int[] vit_val, int feed_type) {
        for (int i=0; i < this.feed_vit_val[feed_type].length; ++i) {
            if (this.feed_vit_val[feed_type][i] > 0) { continue; }
            if (vit_val[i] > 0) { return false; }
        }
        return true;
    }
    
    private void apply_feed(int[] vit_val, int feed_type) {
        for (int i=0; i < this.feed_vit_val[feed_type].length; ++i) {
            vit_val[i] -= this.feed_vit_val[feed_type][i];
        }
    }
    
    public static void main(String[] args) throws IOException {
    	BufferedReader in = new BufferedReader(new FileReader("holstein.in"));
    	PrintWriter out = new PrintWriter(new FileWriter("holstein.out"));
        
        holstein h = new holstein();
        
        h.v = Integer.parseInt(in.readLine());
        final int V = h.v;
        h.min_vit_reqs = new int[V];
        
        StringTokenizer t = new StringTokenizer(in.readLine());
        for (int i=0; i < h.v; ++i) { h.min_vit_reqs[i] = Integer.parseInt(t.nextToken()); }
        
        h.g = Integer.parseInt(in.readLine());
        final int G = h.g;
        h.feed_vit_val = new int[G][V];
        
        for (int i=0; i < h.g; ++i) {
            t = new StringTokenizer(in.readLine());
            for (int j=0; j < h.v; ++j) { h.feed_vit_val[i][j] = Integer.parseInt(t.nextToken()); }
        }
        
        h.kick_start_search();
        out.print(h.scoops);
        for (int i=0; i < h.feed_path.size(); ++i) {
            out.print(" "+(h.feed_path.get(i)+1));
        }
        out.println();

        in.close();
        out.close();
        System.exit(0);
    }
}