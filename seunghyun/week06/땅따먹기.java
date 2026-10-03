 /**
  * @풀이
  *
  * DP
  *
  *
  * @처리순서
  *
  * 1. 각 행에서 얻을 수 있는 최대 점수를 DP 배열에 저장
  *
  * 2. 첫 번째 행은 그대로 저장
  *    -> 첫 번째 행에서는 어떤 열을 선택해도 상관없음
  *
  * 3. 두 번째 행부터 마지막 행까지 반복
  *
  * 4. 현재 행의 각 열을 선택했을 때 이전 행에서 선택할 수 있는 최대 점수를 더함
  *    단, 같은 열은 연속해서 선택할 수 없음
  *
  * 5. 따라서 현재 열을 선택할 때 이전 행의 같은 열을 제외한 나머지 3개 열 중 최댓값을 선택
  * 
  *    DP[i][j] = land[i][j] + 이전 행의 다른 열 중 최댓값
  *
  * 6. 마지막 행에서 가장 큰 값을 반환
  *
  *
  * @시간복잡도
  *
  * O(N)
  *
  *
  * @공간복잡도
  *
  * O(N)
  *
  */

class Solution {

    public int solution(int[][] land) {

        // 각 행에서 얻을 수 있는 최대 점수를 저장
        int[][] dp = new int[land.length][4];

        // 첫 번째 행 초기화
        for (int i = 0; i < 4; i++) {
            dp[0][i] = land[0][i];
        }

        // 두 번째 행부터 계산
        for (int i = 1; i < land.length; i++) {

            // 현재 행의 각 열을 선택
            for (int j = 0; j < 4; j++) {
                // 이전 행에서 현재 열을 제외한
                // 나머지 열 중 최댓값을 찾음
                int max = 0;

                for (int k = 0; k < 4; k++) {
                    if (j != k) {
                        max = Math.max(max, dp[i - 1][k]);
                    }
                }

                // 현재 칸의 점수 + 이전 행의 최대 점수
                dp[i][j] = land[i][j] + max;
            }
        }

        // 마지막 행에서 가장 큰 값 반환
        int answer = 0;

        for (int i = 0; i < 4; i++) {
            answer = Math.max(answer, dp[land.length - 1][i]);
        }

        return answer;
    }
}
