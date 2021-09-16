import java.io.*;
import java.util.*;

public class Kruskal_4386_별자리만들기 {
	public static int n;
	public static Node[] arr;
	public static List<Edge> G;
	public static int[] p;
	public static int[] rank;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new Node[n];
		G = new ArrayList<Edge>();
		p = new int[n];
		rank = new int[n];

		init();

		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			double x = Double.parseDouble(st.nextToken());
			double y = Double.parseDouble(st.nextToken());
			arr[i] = new Node(x, y);
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (i == j)
					continue;
				G.add(new Edge(i, j, distance(arr[i], arr[j])));
			}
		}

		Collections.sort(G);
		Double result = 0d;
		for (Edge cur : G) {
			int px = find(cur.s);
			int py = find(cur.e);
			if (px != py) {
				link(px, py);
				result += cur.val;
			}
		}
		
		System.out.println(String.format("%.2f", result));
	}

	public static void init() {
		for (int i = 0; i < n; i++) {
			p[i] = i;
		}
	}

	public static int find(int x) {
		if (x == p[x])
			return x;
		else
			return p[x] = find(p[x]);
	}

	public static void link(int px, int py) {
		if (rank[px] > rank[py])
			p[py] = px;
		else {
			p[px] = py;
			if (rank[px] == rank[py])
				rank[py]++;
		}
	}

	// 두 점 사이의 거리
	public static double distance(Node n1, Node n2) {
		return Math.sqrt(Math.pow(n1.x - n2.x, 2) + Math.pow(n1.y - n2.y, 2));
	}

	public static class Node {
		double x, y;

		public Node(double x, double y) {
			this.x = x;
			this.y = y;
		}
	}

	public static class Edge implements Comparable<Edge> {
		int s, e;
		double val;

		public Edge(int s, int e, double val) {
			this.s = s;
			this.e = e;
			this.val = val;
		}

		@Override
		public int compareTo(Edge o) {
			return Double.compare(val, o.val);
		}
	}

}