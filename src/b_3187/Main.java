package b_3187;
import java.util.*;

public class Main {
    static char[][] map;
    static boolean[][] visited;  
    static int M, N;             
    static int[] dx = {-1, 1, 0, 0}; 
    static int[] dy = {0, 0, -1, 1}; 
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        M = sc.nextInt();
        N = sc.nextInt();
        map = new char[M][N];      
        visited = new boolean[M][N];

        for (int i = 0; i < M; i++) {
            String line = sc.next();
            for (int j = 0; j < N; j++) {
            	map[i][j] = line.charAt(j);
            }
        } // 맵 입력완료
        
        int totalSheep = 0;
        int totalWolves = 0;
        
        // 모든 셀을 탐색하여 각 구역의 양과 늑대 수를 계산
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                // 벽이 아니고 방문하지 않은 셀에 대해 DFS 실행
                if (map[i][j] != '#' && !visited[i][j]) {
                    // 해당 구역의 양과 늑대 수를 계산
                    int[] counts = dfs(i, j);
                    // 양이 늑대보다 많으면 양의 수를 총합에 추가
                    if (counts[0] > counts[1]) {
                        totalSheep += counts[0];
                    } else {
                        // 늑대가 양보다 많으면 늑대의 수를 총합에 추가
                        totalWolves += counts[1];
                    }
                }
            }
        }
        
        // 총 양과 늑대의 수를 출력
        System.out.println(totalSheep + " " + totalWolves);
    }
    
    static int[] dfs(int x, int y) {
        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{x, y});
        int sheep = 0;  // 구역 내 양의 수
        int wolves = 0; // 구역 내 늑대의 수
        
        while (!stack.isEmpty()) {
            int[] current = stack.pop();
            x = current[0];
            y = current[1];
            
            // 범위를 벗어남 or 이미방문 or 벽인 경우 넘어감
            if (x < 0 || x >= M || y < 0 || y >= N || visited[x][y] || map[x][y] == '#') {
                continue;
            }
            // 방문 처리
            visited[x][y] = true;
            
            // 양 또는 늑대 ++
            if (map[x][y] == 'k') {
                sheep++;
            } else if (map[x][y] == 'v') {
                wolves++;
            }
            
            // 상하좌우로 인접한 셀을 스택에 추가하여 탐색
            for (int i = 0; i < 4; i++) {
                int newX = x + dx[i];
                int newY = y + dy[i];
                stack.push(new int[]{newX, newY});
            }
        }
        
        // 양과 늑대의 수를 배열에 담아 반환
        return new int[]{sheep, wolves};
    }
}