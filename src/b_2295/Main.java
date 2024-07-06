package b_2295;
//https://www.acmicpc.net/problem/2295 골드4 세 수의 합
import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
	    int[] arr = new int[n];
	    
	    for (int i = 0; i < n; i++) {
	    	arr[i] = sc.nextInt();
	    }
	    
	    Arrays.sort(arr); // 이분탐색을 위해 정렬
	    
	    int[] sumArr = new int[n*n];
	    int idx = 0;
	    for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				sumArr[idx++]=arr[i]+arr[j];
			}
		}
	    Arrays.sort(sumArr); // binarySearch 메서드를 사용하기 위해 정렬
	    
	    int result = 0;
	    outer : 
	    for (int i = n-1; i >= 0; i--) { // 값이 최대인 것 부터 i 
			for (int j = 0; j < i; j++) { // 음수 값 안생기기 + 최대값 지정
				if(Arrays.binarySearch(sumArr, arr[i]-arr[j]) >=0) {
					result = arr[i];
					break outer;
				}
			}
		}
	    System.out.println(result);
	}
}
