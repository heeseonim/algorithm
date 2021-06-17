import java.io.*;
import java.util.*;

public class M4_05_인디언합창단 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int[] p;
	static int[] rank;
	static int[] memCnt;

	public static void main(String[] args) throws Exception {
		p = new int[26];
		rank = new int[26];
		memCnt = new int[26];
		makeSet();

		int N = Integer.parseInt(br.readLine());

		int gCnt = 0;
		int pCnt = 26;

		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			int first = str.charAt(0) - 65;
			int second = str.charAt(2) - 65;
			union(first, second);
		}

		for (int m : memCnt) {
			if (m > 1) { // 그룹이 형성되어 있다면
				gCnt++;
				pCnt -= m;
			}
		}
		
		System.out.println(gCnt);
		System.out.println(pCnt);
	}

	public static void makeSet() {
		for (int i = 0; i < p.length; i++) {
			p[i] = i;
			memCnt[i] = 1;
		}
	}

	public static int findSet(int x) {
		if (p[x] == x)
			return x;
		else {
			p[x] = findSet(p[x]);
			return p[x];
		}
	}

	public static void union(int x, int y) {
		int px = findSet(x);
		int py = findSet(y);
		if (px != py)
			link(px, py);
	}

	public static void link(int px, int py) {
		if (rank[px] > rank[py]) {
			p[py] = px; // py 의 부모는 px
			memCnt[px] += memCnt[py];
			memCnt[py] = 0;
		} else {
			p[px] = py;
			if (rank[px] == rank[py])
				rank[py]++;
			memCnt[py] += memCnt[px];
			memCnt[px] = 0;
		}
	}
}
