import java.io.BufferedReader;
import java.io.InputStreamReader;

public class M1_추가7_카운팅6단계 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws Exception {
		int arr[] = new int[] { 1, 2, 3, 1, 2, 3, 2, 1 };
		boolean flag = true;

		for (int i = 0; i < arr.length - 1; i++) {
			if (arr[i] == 1 && arr[i + 1] != 2) {
				flag = false;
				break;
			}
			if (arr[i] == 2 && arr[i + 1] != 3) {
				flag = false;
				break;
			}
			if (arr[i] == 3 && arr[i + 1] != 1) {
				flag = false;
				break;
			}
		}

		System.out.println(flag ? "올바른 패턴 O" : "올바른 패턴 X");
	}
}
