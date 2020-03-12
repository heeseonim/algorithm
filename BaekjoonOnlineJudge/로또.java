import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_백준_6603_로또 {
	static int N;
	static int[] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			String[] str = br.readLine().split(" ");
			N = Integer.parseInt(str[0]); // 주어진 수의 개수
			arr = new int[N]; // 주어진 수를 저장할 배열

			if (N == 0)
				break;

			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(str[i + 1]);
			}
			
			backtrack(0, 0, "");
			System.out.println();

		}

	} // end of main

	static void backtrack(int index, int cnt, String result) {
		if(index == N) { // 마지막 인덱스까지 왔을 때
			if (cnt == 6) { // 총 갯수가 6개이면
				System.out.println(result); // 결과 출력
			}
		} else { // 인덱스 증가시키면서 선택
			backtrack(index+1, cnt+1, result+arr[index]+" "); // 해당 인덱스를 선택하거나
			backtrack(index+1, cnt, result); // 해당 인덱스를 선택하지 않거나
		}
	}

} // end of class
