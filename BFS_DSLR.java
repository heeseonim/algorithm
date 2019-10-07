import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_9019_DSLR {
	public static int sNum, eNum;
	public static boolean[] visited;
	public static String result;
	
	public static class Node {
		int num;
		String com;

		public Node(int num, String com) {
			this.num = num;
			this.com = com;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			result = "";
			visited = new boolean[10000];
			StringTokenizer st = new StringTokenizer(br.readLine());
			sNum = Integer.parseInt(st.nextToken());
			eNum = Integer.parseInt(st.nextToken());

			bfs();
			System.out.println(result);
		}
	}

	public static void bfs() {
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(new Node(sNum, ""));

		while (!queue.isEmpty()) {
			int curNum = queue.peek().num; // 현재 큐에 저장되어 있는 숫자
			String curCom = queue.peek().com; // 현재 큐에 저장되어 있는 명령어
			visited[curNum] = true;
			queue.poll();

			if(curNum == eNum) {
				result = curCom;
				return;
			}
			
			// D
			int changeNum = (2 * curNum) % 10000;
			if (!visited[changeNum]) {
				visited[changeNum] = true;
				queue.add(new Node(changeNum, curCom + "D"));
			}

			// S
			changeNum = (curNum - 1) < 0 ? 9999 : curNum - 1;
			if (!visited[changeNum]) {
				visited[changeNum] = true;
				queue.add(new Node(changeNum, curCom + "S"));
			}

			// L
			changeNum = (curNum % 1000) * 10 + curNum / 1000;
			if (!visited[changeNum]) {
				visited[changeNum] = true;
				queue.add(new Node(changeNum, curCom + "L"));
			}

			// R
			changeNum = (curNum % 10) * 1000 + curNum / 10;
			if (!visited[changeNum]) {
				visited[changeNum] = true;
				queue.add(new Node(changeNum, curCom + "R"));
			}
		}
	}
}
