package swea1248;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {

	static int[] parent, level;
	static List<Integer>[] adj;
	static boolean[] visited;
	static int count;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=null;
		
		int T=Integer.parseInt(br.readLine());
		for (int tc=1; tc<=T; tc++) {
			
			count=0;
			Arrays.fill(visited, false);
			
			st=new StringTokenizer(br.readLine()," ");
			int V=Integer.parseInt(st.nextToken());
			int E=Integer.parseInt(st.nextToken());
			int A=Integer.parseInt(st.nextToken());
			int B=Integer.parseInt(st.nextToken());
			
			visited=new boolean[V+1];
			parent=new int[V+1];
			level = new int [V+1];
			adj= new ArrayList[V+1];
			
			for (int i=1; i<V+1; i++)
				adj[i]=new ArrayList<>();
			
			st=new StringTokenizer(br.readLine(), " ");
			for (int i=0; i<E; i++) {
				
				int from=Integer.parseInt(st.nextToken());
				int to=Integer.parseInt(st.nextToken());
				
				adj[from].add(to);
				adj[to].add(from);
			}
	
			setTree(1, 0);
			int ac=lca(A,B);
			childCheck(ac);
			
			System.out.println("#"+tc+" "+ac+" "+count);
		}

	}
	
	static void childCheck (int node) {
		visited[node]=true;
		count++;
		
		for (int child : adj[node]) {
			if (!visited[child]) {
				childCheck(child);
			}
		}
	}
	
	static void setTree (int node, int pnode) {
		parent[node]=pnode;
		level[node]=level[pnode]+1;
		
		for (int child : adj[node]) {
			if (child!=pnode) 
				setTree(child, node);
		}
	}
	
	static int lca (int a, int b) {
		if (level[a]<level[b]) {
			int tmp=a;
			a=b;
			b=tmp;
		}
		
		while (level[a]!=level[b])
			a=parent[a];
		
		while (a!=b) {
			a=parent[a];
			b=parent[b];
		}
		return a;
	}

}
