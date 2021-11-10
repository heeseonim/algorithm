import java.io.*;
import java.util.*;

// 64 이하의 자연수를 2진수로 나타냈을 때 1의 개수
public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int X = Integer.parseInt(br.readLine());
		int cnt = 0;
		while (X > 0) {
			if (X % 2 == 1) // 나머지가 1이면 cnt 증가
				cnt++;
			X /= 2; // 반 나눠주기
		}
		System.out.println(cnt);
	}
}
