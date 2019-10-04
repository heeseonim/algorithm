import java.util.Arrays;

public class 정렬_가장큰수 {
	public static void main(String[] args) {
		int[] numbers = { 6, 10, 2 };
		System.out.println(solution(numbers));
	}

	public static String solution(int[] numbers) {
		String[] arr = new String[numbers.length]; // 전달받은 숫자 배열만큼 크기 할당
		
		for (int i = 0; i < numbers.length; i++) // 숫자를 스트링으로 변환하여 저장
			arr[i] = String.valueOf(numbers[i]);

		Arrays.sort(arr, (o1, o2) -> (o2 + o1).compareTo(o1 + o2)); // 두개씩 비교하며 스트링형식으로 더한 값을 내림차순으로 정렬
		
		if(arr[0].equals("0")) return "0"; // 맨 앞이 0이라면 모두 0이므로 반환되는 전체 문자열을 0으로 리턴한다.
		
		StringBuilder sb = new StringBuilder(); // 문자열 배열을 문자열로 만들기 위해 
		for (int i = 0; i < arr.length; i++) {
			sb.append(arr[i]); // 배열 원소 하나씩 붙여줌
		}
		
		return sb.toString(); // 완성된 string builder를 스트링으로 변환하여 리턴
	}
}
