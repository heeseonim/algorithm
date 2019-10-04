import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main_백준_16637_괄호추가하기 {
	public static int N;
	public static int[] totCalNum;
	public static int[] selCalNum;
	public static int max;
	public static String[] str;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); // 수식의 길이
		str = br.readLine().split(""); // 수식
		System.out.println(Arrays.toString(str));
		int calNum = N / 2; // 연산자 개수
		totCalNum = new int[calNum]; // 연산자 인덱스 저장 배열
		selCalNum = new int[Math.round(calNum / 2)]; // 뽑힌 연산자 인덱스 저장 배열
		int index = 0;
		for (int i = 1; i < N; i += 2) {
			totCalNum[index] = i;
			index++;
		}
		System.out.println(Arrays.toString(totCalNum));

		int cnt = 0;
		// 뽑을 수 있는 연산자 개수 : 0, 1, 2 ...
		while (cnt <= selCalNum.length) {
			comb(totCalNum.length, cnt);
			cnt++;
		}

	} // end of main

	public static void comb(int n, int r) {
		if (r == 0) {
			// 괄호가 연속되도록 뽑아졌다면 return 해주기
			for (int i = 0; i < selCalNum.length - 1; i++) {
				if (selCalNum[i + 1] - selCalNum[i] == 2) {
					return;
				}
			}
			System.out.println("선택");
			System.out.println(Arrays.toString(selCalNum));
			// 계산함수 호출

		} else if (n < r) { // 잘못된 경우
			return;
		} else {
			selCalNum[r - 1] = totCalNum[n - 1];
			comb(n - 1, r - 1);
			comb(n - 1, r);
		}
	}

	// 큐에 넣어주는 함수
	public static void insertQ() {
		Queue<String> q = new LinkedList<>();
		int index = 0;
		// 원소 하나씩 확인
		for (int i = 0; i < N; i+=2) {
			if (selCalNum[index++] > 0) {
				
			} else {
				q.add(str[i]);
			}
		}
	}
} // end of class
