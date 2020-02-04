import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_5670 {
	public static final int SIZE = 26;
	public static int cnt;

	public static class TrieNode { // 노드의 세부정보 저장
		TrieNode[] children = new TrieNode[SIZE]; // 만들어지는 노드마다 26개의 소문자 배열 생성
		boolean isTerminal;
		int nChild = 0;

		TrieNode() {
			isTerminal = false;
			for (int i = 0; i < SIZE; i++)
				children[i] = null; // 초기화
		}
	}

	public static class Trie {
		TrieNode root;

		Trie() {
			root = new TrieNode();
		}

		void insert(String key) {
			TrieNode currentNode = root; // 첫 시작은 root 에서

			for (int i = 0; i < key.length(); i++) {
				int next = key.charAt(i) - 'a';
				if (currentNode.children[next] == null) { // 존재하지 않는다면
					currentNode.children[next] = new TrieNode(); // 노드 생성
					currentNode.nChild++; // 가지고 있는 자식 노드 수 증가
				}
				currentNode = currentNode.children[next]; // 자식 노드로 이동
			}

			currentNode.isTerminal = true; // 삽입 작업이 다 끝나면 터미널 처리
		}

		void check(TrieNode node, int ret) {
			if (node.isTerminal) // 현재 노드가 터미널 노드라면
				cnt += ret; // 전체 수에 더해줌

			if (node.nChild >= 2) // 자식이 두개 이상이라면
				ret++; // 눌러줘야 하니까 수 증가

			if (node.isTerminal && node.nChild == 1) // 터미널노드인데 자식하나 달려있으면
				ret++; // 눌러줘야 하니까 수 증가

			for (int i = 0; i < SIZE; i++) // 자식 노드들 돌면서 재귀탐색
				if (node.children[i] != null)
					check(node.children[i], ret);
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String line;
		while ((line = br.readLine()) != null) { // 입력이 존재할 때 반복
			Trie trie = new Trie(); // 트라이 생성
			int N = Integer.parseInt(line); // 단어의 개수

			for (int i = 0; i < N; i++)
				trie.insert(br.readLine()); // 단어마다 트라이에 삽입작업

			cnt = 0;
			for (int i = 0; i < SIZE; i++) // 소문자 다 돌면서
				if (trie.root.children[i] != null) // 현재 root의 자식들이 비어있지 않다면
					trie.check(trie.root.children[i], 1); // 자식노드로 이동함, 처음은 무조건 눌러줘야 하니까 1로 시작

			System.out.printf("%.2f\n", (double) cnt / N); // 평균을 소수점 둘째 자리까지 반올림하여 출력
		}
	}
}
