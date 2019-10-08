import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_5052 {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static String str;
	private static int i;
	private static int res;

	static int nextInt() { // int 형 입력
		try {
			i = Integer.parseInt(br.readLine().trim());
		} catch (Exception e) {

		}
		return i;
	}

	static String nextLine() { // string 입력
		try {
			str = br.readLine().trim();
		} catch (Exception e) {

		}
		return str;
	}

	public static void main(String[] args) {
		int T = nextInt();

		for (int t = 1; t <= T; t++) {
			int N = nextInt(); // 문장의 개수

			Node root = new Node('r', 0); // 루트 초기값
			for (int n = 1; n <= N; n++) {
				Node node = root;
				String str = nextLine();

				for (int i = 0; i < str.length(); i++) {
					char c = str.charAt(i);
					if (node.nxt[c - '0'] == null) { // 원 노드의 다음노드중 해당 문자노드가 비어있음
						node.cnt++; // 원 노드에 자식이 추가됨
						node.nxt[c - '0'] = new Node(c, 0); // 자식 노드 중 해당 문자노드를 추가
					}
					node = node.nxt[c - '0']; // 다음 노드를 원 노드로 변경
				}
			}
			res = 0;
			searchLeaf(root);
			if (res != N) {
				System.out.println("NO");
			} else {
				System.out.println("YES");
			}
		}
	}

	private static void searchLeaf(Node node) {
		if (node.cnt == 0) { // 끝까지 도달했을 때
			res++; //
			return;
		}
		for (int i = 0; i < 10; i++) {
			if (node.nxt[i] != null) { // 자식 노드 중 데이터가 존재하면
				searchLeaf(node.nxt[i]); // 해당 자식 노드 탐색
			}
		}
	}

	public static class Node {
		char data;
		int cnt;
		Node[] nxt = new Node[10];

		public Node(char data, int cnt) {
			this.data = data;
			this.cnt = cnt;
		}
		
	}
}
