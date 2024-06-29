package b_1926;
//https://www.acmicpc.net/problem/1926 실1 그림
import java.util.*;

public class Main {

	static int n, m;
	static int[][] map;
	static boolean[][] visited;
	static int[] dx = {-1, 1, 0, 0}; // 상 하
	static int[] dy = {0, 0, -1, 1}; // 좌 우
	static int nowSize;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		m = sc.nextInt();
		
		map = new int[n][m];
		visited = new boolean[n][m];
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				map[i][j] = sc.nextInt();
			}
		} // 입력 받기
		sc.close();
		
		int cntPic = 0;
		int maxSize = 0;
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if(map[i][j]==1 && !visited[i][j]) { // map[?][?]=1 && visited[?][?]=0 라면
					nowSize = 0; // 반복문 들어올때 마다 각 그림의 사이즈 체크를 위해 초기화
					dfs(i,j);
					cntPic++; // 그림 갯수 확인
					if(nowSize > maxSize) {
						maxSize = nowSize; // 맥스값 최신화
					}
				}
			}
		}
		System.out.println(cntPic);
		System.out.println(maxSize);
		
	}
	
	
	public static void dfs(int x, int y) {
		visited[x][y] = true;
		nowSize++; // 현재 사이즈 확인
		
		for (int i = 0; i < 4; i++) { // 상하좌우 적용
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(nx>=0 && nx<n && ny>=0 && ny < m) { // 범위내 확인
				if(map[nx][ny]==1&&!visited[nx][ny]) { // map[?][?]=1 + visited[?][?]=false 라면
					dfs(nx,ny); //재귀 호출
				}
			}
		}
	}
}
