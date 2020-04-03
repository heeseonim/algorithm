
import java.util.*;

public class Solution_42861 {
	public static void main(String[] args) {
		System.out
				.println(solution(4, new int[][] { { 0, 1, 1 }, { 0, 2, 2 }, { 1, 2, 5 }, { 1, 3, 1 }, { 2, 3, 8 } }));
	}
	
	public static int[] p, rank; // 부모배열과 깊이배열
	
	public static class Edge implements Comparable<Edge> {
		int a; // 정점1
		int b; // 정점2
		int val; // 가중치
		
		public Edge(int a, int b, int val) {
			this.a = a;
			this.b = b;
			this.val = val;
		}

		// 간선을 가중치 기준으로 오름차순 정렬
		@Override
		public int compareTo(Edge o) {
			return this.val - o.val;
		}
	}

	public static int solution(int n, int[][] costs) {
		// Kruskal 알고리즘
		
		Edge[] G = new Edge[costs.length];
		for (int i = 0; i < costs.length; i++) {
			 G[i] = new Edge(costs[i][0], costs[i][1], costs[i][2]);
		}
		Arrays.sort(G);
		
		p = new int[n];
		rank = new int[n];
		makeSet();
		
		int answer = 0;
		int count = 0;
		for (int i = 0; i < costs.length; i++) {
			Edge e = G[i]; // 작은 가중치의 간선부터 꺼냄
			int px = findSet(e.a);
			int py = findSet(e.b);
			// 사이클이 발생하지 않을 때
			if (px != py) {
				union(px, py);
				answer += e.val;
				count++;
				if (count == n-1) {
					break;
				}
			}
		}
		
		return answer;
	}
	
	// 자기자신을 부모(대표자)로 표시
	public static void makeSet() {
		for (int i = 0; i < p.length; i++) {
			p[i] = i;
		}
	}
	
	// x가 포함된 집합의 부모(대표자)를 반환
	public static int findSet(int x) {
		if (p[x] == x) return x;
		else {
			p[x] = findSet(p[x]);
			return p[x];
		}
	}
	
	// x, y를 포함하는 두 집합을 통합
	public static void union(int x, int y) {
		if (rank[x] > rank[y])
			p[y] = x;
		else {
			p[x] = y;
			if (rank[x] == rank[y])
				rank[y]++;
		}
	}
}
