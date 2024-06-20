package b_2502;
//https://www.acmicpc.net/problem/2502 실1 떡 먹는 호랑이
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int D = sc.nextInt(); // 넘어온 날
		int K = sc.nextInt(); // 마지막날 떡 갯수
		
		int[] ddeok = new int[D];
		ddeok[D-1] = K; // 떡 배열의 마지막 = 마지막날 떡 갯수
		
		int start = 1;
		
		while(true) {
			ddeok[0]=start;
			for (int i = start; i < K; i++) {
				ddeok[1]= i;
				for (int j = 2; j < D-1; j++) {
					ddeok[j] = ddeok[j-1]+ddeok[j-2];
				}
				
				if(ddeok[D-1]==ddeok[D-2]+ddeok[D-3]) {
					System.out.println(ddeok[0]);
					System.out.println(ddeok[1]);
					return; // 값을 찾는 동시에 종료
				}
			
			}
			start++;
		}
	
	}

}
