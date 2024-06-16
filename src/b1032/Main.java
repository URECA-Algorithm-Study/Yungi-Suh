package b1032;
//https://www.acmicpc.net/problem/1032 브1 명령프롬프트
import java.util.Scanner;
// 2차원 배열로 가능한가? 
// 2차원배열로 선언했을 때 각 알파벳 하나씩에 인덱스를 확인 가능한가? 
// StringBuilder
public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		String[] file = new String[N];
		
		for (int i = 0; i < N; i++) {
			file[i] = sc.next();
		}			// 입력 완료
		
		boolean compChar;

		for (int i = 0; i < file[0].length(); i++) { // 입력받은 문자열의 길이만큼 범위지정
			compChar = true;
			for (int j = 0; j < N-1; j++) { // 범위를 N-1 을 해줘야 정확한 범위에 비교 가능
				if(file[j].charAt(i)!=file[j+1].charAt(i)) {
					compChar = false;
					break; // 하나라도 틀리면 더이상 for문을 돌릴 필요가 없기에 브레이크문 선언
				}
			}
			if(compChar) {
				System.out.print(file[0].charAt(i)); // 다 같다면 대표로 file[0]에 있는 문자배열 하나씩 출력
			}else {
				System.out.print("?"); // compChar가 거짓이라면 바로 ? 출력 
			}
		}		
	}
}
