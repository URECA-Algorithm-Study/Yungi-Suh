package b_17359;

// https://www.acmicpc.net/problem/17359 실2 전구 길만 걷자

import java.io.*;
import java.util.*;

public class Main {
    static String[] bulbs;      
    static int N;
    static int minChanges = Integer.MAX_VALUE; // 최소 상태 변화 횟수를 저장할 변수
    static int[] internalChanges; // 각 전구 묶음 내부의 상태 변화 횟수를 저장할 배열

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); 
        bulbs = new String[N];  // 전구 묶음 상태 배열 초기화
        internalChanges = new int[N]; // 각 전구 묶음의 내부 상태 변화 횟수를 저장할 배열 초기화

        // 각 전구 묶음의 상태를 입력받고 내부 변화 횟수를 계산
        for (int i = 0; i < N; i++) {
            bulbs[i] = br.readLine();
            internalChanges[i] = countInternalChanges(bulbs[i]); // 각 전구 묶음의 내부 상태 변화 횟수 계산
        }
        br.close(); //

        // 모든 전구 묶음의 순열을 백트래킹을 통해 탐색
        permute(0, new boolean[N], new ArrayList<>(), 0);
        

        System.out.println(minChanges);
    }

    /**
     * 백트래킹을 사용하여 모든 전구 묶음의 순열을 탐색
     *
     * @param depth 현재 탐색 깊이
     * @param visited 전구 묶음 사용 여부를 기록하는 배열
     * @param current 현재까지 선택된 전구 묶음 목록
     * @param currentChanges 현재까지의 상태 변화 횟수
     */
    private static void permute(int depth, boolean[] visited, List<String> current, int currentChanges) {
        if (depth == N) { // 모든 전구 묶음을 선택한 경우
            minChanges = Math.min(minChanges, currentChanges); // 최소 상태 변화 횟수 갱신
            return;
        }

        for (int i = 0; i < N; i++) {
            if (visited[i]) continue; // 이미 사용된 전구 묶음은 제외

            visited[i] = true; // 현재 전구 묶음을 사용한다고 표시
            current.add(bulbs[i]); // 현재 전구 묶음을 현재 목록에 추가

            // 현재 전구 묶음을 추가했을 때의 상태 변화 횟수 계산
            int changes = currentChanges + internalChanges[i];
            if (depth > 0) { // 첫 번째 전구 묶음은 제외
                changes += countConnectionChanges(current.get(depth - 1), bulbs[i]); 
            } // 이전 전구 묶음과 현재 전구 묶음 간의 상태 변화 횟수 추가
            
            permute(depth + 1, visited, current, changes); // 다음 전구 묶음을 선택하기 위해 재귀 호출
            
            current.remove(current.size() - 1); // 현재 전구 묶음을 목록에서 제거
            visited[i] = false; // 현재 전구 묶음의 사용 표시를 제거
        }
    }

    /**
     * 전구 묶음의 문자열 내에서 상태 변화 횟수를 계산합니다.
     *
     * @param s 전구 묶음의 상태를 나타내는 문자열
     * @return 상태 변화 횟수
     */
    private static int countInternalChanges(String s) {
        int changes = 0;
        char prev = s.charAt(0); // 문자열의 첫 문자를 이전 상태로 설정
        for (int i = 1; i < s.length(); i++) { // 문자열의 나머지 부분을 순회
            if (s.charAt(i) != prev) { // 현재 문자와 이전 문자가 다를 경우
                changes++; // 상태 변화 횟수 증가
                prev = s.charAt(i); // 이전 상태 업데이트
            }
        }
        return changes; // 총 상태 변화 횟수 반환
    }

    /**
     * 두 전구 묶음 간의 상태 변화 횟수를 계산합니다.
     *
     * @param a 첫 번째 전구 묶음의 상태를 나타내는 문자열
     * @param b 두 번째 전구 묶음의 상태를 나타내는 문자열
     * @return 두 전구 묶음 간의 상태 변화 횟수
     */
    private static int countConnectionChanges(String a, String b) {
        // 첫 전구 묶음의 끝 문자와 두 번째 전구 묶음의 시작 문자가 다를 경우 상태 변화가 발생
        return (a.charAt(a.length() - 1) != b.charAt(0)) ? 1 : 0;
    }
}