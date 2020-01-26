import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class Main_9202 {

	static final int SIZE = 26;

	static class Node {
		Node[] child;
		boolean isTerminal;

		Node() {
			isTerminal = false;
			child = new Node[SIZE];
		}
	}

	static class Trie {
		Node root;

		Trie() {
			root = new Node();
		}

		// 단어를 트리에 저장하는 함수
		void insert(String key) {
			Node current = root;

			for (int i = 0; i < key.length(); i++) {
				int next = key.charAt(i) - 'A';

				if (current.child[next] == null)
					current.child[next] = new Node();

				current = current.child[next];
			}

			current.isTerminal = true;
		}

		// 해당 문자열이 단어사전에 있는지 확인하는 함수
		boolean check(String key) {
			Node current = root;

			for (int i = 0; i < key.length(); i++) {
				int next = key.charAt(i) - 'A';

				if (current.child[next] == null)
					return false;

				current = current.child[next];
			}

			return current.isTerminal;
		}
	}

	static int size;
	static char[][] cube;
	static boolean[][] visit;
	static int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 }, { -1, 1 }, { 1, -1 }, { 1, 1 }, { -1, -1 } };
	static HashSet<String> hs;
	static Trie trie;
	static char[] arr;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		trie = new Trie();
		for (int i = 0; i < N; i++)
			trie.insert(br.readLine());

		br.readLine(); // 한 줄 입력 처리

		N = Integer.parseInt(br.readLine());
		StringBuilder result = new StringBuilder();

		for (int k = 0; k < N; k++) {
			cube = new char[4][4];
			visit = new boolean[4][4];
			hs = new HashSet<String>();

			for (int i = 0; i < 4; i++) {
				String s = br.readLine();
				for (int j = 0; j < 4; j++) {
					cube[i][j] = s.charAt(j);
				}
			}

			size = 1;
			while (size <= 8) {
				arr = new char[size];

				for (int i = 0; i < 4; i++) {
					for (int j = 0; j < 4; j++) {
						arr[0] = cube[i][j];
						visit[i][j] = true;
						dfs(i, j, 1);
						visit[i][j] = false;
					}
				}
				size++;
			}

			int maxScore = 0;
			String maxWord = "";
			ArrayList<String> list = new ArrayList<>(hs);
			Collections.sort(list);

			for (String s : list) {
				maxScore += calc(s);
				if (maxWord.length() < s.length())
					maxWord = s;
			}

			result.append(maxScore).append(' ').append(maxWord).append(' ').append(hs.size()).append('\n');

			if (k != N - 1)
				br.readLine();
		}

		System.out.println(result.toString());
	}

	static void dfs(int r, int c, int depth) {
		if (depth == size) {
			String s = String.copyValueOf(arr);
			if (trie.check(s) && !hs.contains(s)) {
				hs.add(s);
			}
			return;
		}

		for (int i = 0; i < 8; i++) {
			int nr = r + dir[i][0];
			int nc = c + dir[i][1];

			if (!isIn(nr, nc) || visit[nr][nc])
				continue;

			arr[depth] = cube[nr][nc];
			visit[nr][nc] = true;
			dfs(nr, nc, depth + 1);
			visit[nr][nc] = false;
		}
	}

	static int calc(String s) {
		int length = s.length();

		if (length <= 2)
			return 0;
		else if (length == 3)
			return 1;
		else if (length <= 6)
			return length - 3;
		else if (length == 7)
			return 5;
		else
			return 11;
	}

	static boolean isIn(int r, int c) {
		return r >= 0 && r < 4 && c >= 0 && c < 4;
	}
}
