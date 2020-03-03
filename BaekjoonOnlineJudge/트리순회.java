import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Main_1991 {
	public static char[] tree;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		tree = new char[(int) Math.pow(2, N)];
		HashMap<Character, Integer> hm = new HashMap<Character, Integer>();

		hm.put('A', 1); // 'A'를 루트 노드로 지정
		tree[1] = 'A';

		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			char root = s.charAt(0);
			char left = s.charAt(2);
			char right = s.charAt(4);

			int position = hm.get(root); // 루트의 위치를 불러옴

			if (left != '.') {
				tree[position * 2] = left;
				hm.put(left, position * 2);
			}

			if (right != '.') {
				tree[position * 2 + 1] = right;
				hm.put(right, position * 2 + 1);
			}
		}

		preorder(1);
		System.out.println();
		inorder(1);
		System.out.println();
		postorder(1);
	}

	public static void preorder(int index) { // 전위순회 루트-left-right
		System.out.print(tree[index]); // 현재 조회하고 있는 노드를 출력
		if (index * 2 < tree.length && tree[index * 2] != 0) // 0이 아니라면
			preorder(index * 2);
		if (index * 2 + 1 < tree.length && tree[index * 2 + 1] != 0)
			preorder(index * 2 + 1);
	}

	public static void inorder(int index) { // 중위순회 left-루트-right
		if (index * 2 < tree.length && tree[index * 2] != 0)
			inorder(index * 2);
		System.out.print(tree[index]);
		if (index * 2 + 1 < tree.length && tree[index * 2 + 1] != 0)
			inorder(index * 2 + 1);
	}

	public static void postorder(int index) { // 후위순회 left-right-루트
		if (index * 2 < tree.length && tree[index * 2] != 0)
			postorder(index * 2);
		if (index * 2 + 1 < tree.length && tree[index * 2 + 1] != 0)
			postorder(index * 2 + 1);
		System.out.print(tree[index]);
	}
}
