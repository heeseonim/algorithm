import java.io.*;
import java.util.*;

public class M3_08_안나와엘사M {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int N;
	static char[][] map;
	static Queue<Node> queue;
	static int[][] dir = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 }, { 0, 0 } };
	static boolean[][][][] visited;

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		visited = new boolean[N][N][N][N];
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}

		st = new StringTokenizer(br.readLine(), " ");
		queue = new LinkedList<>();
		int er = Integer.parseInt(st.nextToken());
		int ec = Integer.parseInt(st.nextToken());
		int ar = Integer.parseInt(st.nextToken());
		int ac = Integer.parseInt(st.nextToken());
		visited[er][ec][ar][ac] = true;
		queue.add(new Node(er, ec, ar, ac, 0));

		bfs();
	}

	public static void bfs() {
		while (!queue.isEmpty()) {
			Node n = queue.poll();

			if (n.ar == n.er && n.ac == n.ec) {
				System.out.println(n.level); // 같으면 출력
				return;
			}

			for (int i = 0; i < dir.length; i++) {
				int ner = n.er + dir[i][0];
				int nec = n.ec + dir[i][1];

				if (!isIn(ner, nec) || map[ner][nec] == '#')
					continue;

				for (int j = 0; j < dir.length; j++) {
					int nar = n.ar + dir[j][0];
					int nac = n.ac + dir[j][1];

					if (!isIn(nar, nac) || map[nar][nac] == '#' || visited[ner][nec][nar][nac])
						continue;

					visited[ner][nec][nar][nac] = true;
					queue.add(new Node(ner, nec, nar, nac, n.level + 1));
				}
			}
		}
	}

	public static class Node {
		int er, ec, ar, ac, level;

		public Node(int er, int ec, int ar, int ac, int level) {
			this.er = er;
			this.ec = ec;
			this.ar = ar;
			this.ac = ac;
			this.level = level;
		}
	}

	public static boolean isIn(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}
}
