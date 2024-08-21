package b_1743;
import java.util.*;

public class Main {
    static int[][] grid;
    static boolean[][] visited;
    static int M, N;
    static int[] dx = {-1, 1, 0, 0}; // 상, 하, 좌, 우
    static int[] dy = {0, 0, -1, 1};
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // 입력 처리
        M = sc.nextInt();
        N = sc.nextInt();
        int K = sc.nextInt();
        
        grid = new int[M][N];
        visited = new boolean[M][N];
        
        for (int i = 0; i < K; i++) {
            int r = sc.nextInt() - 1;
            int c = sc.nextInt() - 1;
            grid[r][c] = 1;
        }
        
        int maxArea = 0;
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    int area = dfs(i, j);
                    maxArea = Math.max(maxArea, area);
                }
            }
        }
        
        System.out.println(maxArea);
    }
    
    static int dfs(int x, int y) {
        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{x, y});
        int area = 0;
        
        while (!stack.isEmpty()) {
            int[] current = stack.pop();
            x = current[0];
            y = current[1];
            
            if (x < 0 || x >= M || y < 0 || y >= N || grid[x][y] == 0 || visited[x][y]) {
                continue;
            }
            
            visited[x][y] = true;
            area++;
            
            for (int i = 0; i < 4; i++) {
                int newX = x + dx[i];
                int newY = y + dy[i];
                stack.push(new int[]{newX, newY});
            }
        }
        
        return area;
    }
}