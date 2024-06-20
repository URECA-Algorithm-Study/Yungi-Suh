package b18353;
//https://www.acmicpc.net/problem/18353 실2 병사 배치하기 - 이해?

import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int[] power = new int[n];
		
		for (int i = 0; i < n; i++) {
			power[i]=sc.nextInt();
		} //입력완료
		
		int[] dp = new int[n];
		Arrays.fill(dp, 1);
		
		int max=1;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < i; j++) {
				if(power[j] > power[i]) {
					dp[i]=Math.max(dp[i],dp[j]+1);
					System.out.println(i+" "+j+" "+dp[i]);
				}
			}
			max = Math.max(max, dp[i]);
		}
		
		
		System.out.println(n-max);
		
	}
}
