import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class M1_추가5_카운팅4단계 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws Exception {
		int arr[] = new int[] { 3, 2, 1, 7, 4, 5 };
		int target = 7;
		boolean flag = false;
		
		for(int a : arr) {
			if (a == target)
				flag = true;
		}

		System.out.println(flag ? "존재" : "안존재");
	}
}
