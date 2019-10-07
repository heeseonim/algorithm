import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Solution_1228_암호문1 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for (int tc = 1; tc <= 10; tc++) {
			int N = Integer.parseInt(br.readLine());
			LinkedList<String> list = new LinkedList<>();
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				list.add(st.nextToken());
			}

			int M = Integer.parseInt(br.readLine());
			String[] pArr = br.readLine().split("I ");
			for(int i=1; i<=M; i++) {
				String[] pNum = pArr[i].split(" ");
				int index = Integer.parseInt(pNum[0]); // 명령어 삽입 인덱스
				int num = Integer.parseInt(pNum[1]); // 명령어 갯수
				for (int j = 0; j < num; j++) {
					list.add(index++, pNum[j+2]);					
				}
			}

			System.out.print("#" + tc + " ");
			for(int i=0; i<10; i++) {
				System.out.print(list.get(i) + " ");
			}
			System.out.println();
			
		}
	}

}
