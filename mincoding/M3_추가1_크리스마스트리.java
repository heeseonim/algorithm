import java.io.*;
import java.util.*;

public class M3_추가1_크리스마스트리 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static Node root;

	public static void main(String[] args) throws Exception {
		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			create(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()));
		}
		inOrder(root);
		sb.append('\n');
		preOrder(root);
		sb.append('\n');
		postOrder(root);
		sb.append('\n');
		System.out.println(sb);
	}

	public static void preOrder(Node node) {
		if (node != null) {
			sb.append(node.data).append(' ');
			if (node.left != null)
				preOrder(node.left);
			if (node.right != null)
				preOrder(node.right);
		}
	}

	public static void inOrder(Node node) {
		if (node != null) {
			if (node.left != null)
				inOrder(node.left);
			sb.append(node.data).append(' ');
			if (node.right != null)
				inOrder(node.right);
		}
	}

	public static void postOrder(Node node) {
		if (node != null) {
			if (node.left != null)
				postOrder(node.left);
			if (node.right != null)
				postOrder(node.right);
			sb.append(node.data).append(' ');
		}
	}

	public static void create(int data, int leftData, int rightData) {
		if (root == null) {
			root = new Node(data); // 초기 루트 생성
			if (leftData != -1)
				root.left = new Node(leftData);
			if (rightData != -1)
				root.right = new Node(rightData);
		} else { // 들어갈 자리 찾기
			search(root, data, leftData, rightData);
		}
	}

	public static void search(Node node, int data, int leftData, int rightData) {
		if (node == null)
			return;
		else if (node.data == data) { // 들어갈 위치를 찾았다면
			if (leftData != -1)
				node.left = new Node(leftData);
			if (rightData != -1)
				node.right = new Node(rightData);
		} else { // 찾지 못했다면
			search(node.left, data, leftData, rightData); // 왼쪽 탐색
			search(node.right, data, leftData, rightData); // 오른쪽 탐색
		}
	}

	public static class Node {
		int data;
		Node left, right;

		public Node(int data) {
			this.data = data;
		}
	}
}
