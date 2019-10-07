import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1809 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int num = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] stick = new int[num];
		int[] result = new int[num];
		
		for (int i = 0; i < num; i++) {
			stick[i] = Integer.parseInt(st.nextToken());
		}

		int top = num - 1; // 인덱스 저장
		while (top > 0) {
			int rtop = top; // 현재의 자리(저장될 위치)를 저장
			int num2 = stick[top]; // 현재 값
			int num1 = stick[top - 1]; // 이전 값
			if (num1 > num2) {
				result[rtop] = top; // 탑의 번호 : 큰값인 num1 위치(top-1) + 1
			} else if (num1 < num2) {
				top--; // 이전의 값을 계산하기 위해 top--
				while (top > -1 && stick[top] < stick[rtop]) { // 이전의 값도 현재 값보다 작다면
					top--; // 아래로 더 이동
				}
				if (top == -1) { // 끝까지 이동했다면
					result[rtop] = 0; // 해당 위치의 결과에 0을 넣어줌
				} else {
					result[rtop] = top + 1; // 현재의 값보다 큰 값이 존재한다면 (큰값위치+1)인 탑의 번호를 넣어줌
				}
				top = rtop; // 차례로 검사하기 위해 다시 현재의 값 저장
			}
			top--; // 아래로 이동
		}
		for (int i = 0; i < result.length; i++) {
			System.out.print(result[i] + " ");
		}

	}

}
