import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class M2_추가1_금나와라황금보자기 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		int N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			pq.add(new Node(true, Integer.parseInt(st.nextToken())));
		}

		int cnt = 0;
		while (!pq.isEmpty()) {
			Node first = pq.poll();
			if (!first.type)
				break;
			cnt++;
			
			Node second = pq.poll();
			if (!second.type)
				break;
			cnt++;

			pq.add(new Node(false, second.weight * 2));
		}

		System.out.println(cnt);
	}

	public static class Node implements Comparable<Node> {
		boolean type; // true : gold, false : stone
		int weight;

		public Node(boolean type, int weight) {
			this.type = type;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) {
			if (this.weight == o.weight) {
				if (this.type) // gold 먼저
					return -1;
				else
					return 1;
			}
			return this.weight - o.weight;
		}
	}
}
