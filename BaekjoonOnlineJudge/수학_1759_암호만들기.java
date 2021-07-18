import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 수학_1759_암호만들기 {
	static int L, C;
	static String[] arr;
	static boolean[] check;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		arr = br.readLine().split(" ");
		check = new boolean[C];
		
		Arrays.sort(arr); // 오름차순 표기

		backtrack(0, 0, 0, 0);
	}

	static void backtrack(int index, int cnt, int ja, int mo) {
		if (index == C) {
			if (cnt == L)
				if (ja >= 2 && mo >= 1)
					print();
			return;
		} else {
			boolean flag = jamo(arr[index]); // 모음이면 true
			check[index] = true; // 사용함
			backtrack(index + 1, cnt + 1, ja + (flag ? 0 : 1), mo + (flag ? 1 : 0));
			check[index] = false; // 사용안함
			backtrack(index + 1, cnt, ja, mo);
		}
	}

	static void print() {
		for (int i = 0; i < C; i++)
			if (check[i])
				System.out.print(arr[i]);
		System.out.println();
	}

	static boolean jamo(String str) {
		if (str.equals("a") || str.equals("e") || str.equals("i") || str.equals("o") || str.equals("u"))
			return true;
		return false;
	}

}
