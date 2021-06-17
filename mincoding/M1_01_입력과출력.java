import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class M1_01_입력과출력 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	public static void main(String[] args) throws Exception {
		int type = Integer.parseInt(br.readLine());
		int n = 0;
		int m = 0;
		if (type == 3) {
			st = new StringTokenizer(br.readLine(), " ");
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
		} else {
			n = Integer.parseInt(br.readLine());
		}

		if (type == 1) {
			long sum = 0;
			long gob = 1;

			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < n; i++) {
				int cur = Integer.parseInt(st.nextToken());
				sum += cur;
				gob *= cur;
			}

			bw.write(sum + " " + gob);
		} else if (type == 2) {
			String minS = br.readLine();
			String maxS = minS;
			int minCnt = minS.length();
			int maxCnt = maxS.length();

			for (int i = 0; i < n - 1; i++) {
				String s = br.readLine();
				if (minCnt > s.length()) {
					minS = s;
					minCnt = s.length();
				}
				if (maxCnt < s.length()){
					maxS = s;
					maxCnt = s.length();
				}
			}

			bw.write(maxS + "\n" + minS);
		} else {
			int min = Integer.MAX_VALUE;
			int cnt = 0;
			
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < m; j++) {
					int cur = Integer.parseInt(st.nextToken());
					if (min > cur) {
						min = cur;
						cnt = 1;
					} else if (min == cur){
						cnt++;
					}
				}
			}
			
			bw.write(min + "\n" + cnt + "개");
		}
		
		bw.flush();
	}
}
