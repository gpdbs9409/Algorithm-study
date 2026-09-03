/**
 * @풀이
 *
 * DFS
 *
 * 각 숫자마다 + 또는 -를 선택할 수 있기 때문에 모든 경우의 수를 DFS로 탐색
 *
 *
 * @처리순서
 *
 * 1. 숫자를 앞에서부터 하나씩 확인
 *
 * 2. 현재 숫자를 더하는 경우와 빼는 경우로 나누어 DFS를 호출
 *
 * 3. 다음 숫자로 이동하면서 같은 과정을 반복
 *
 * 4. 모든 숫자를 사용했다면 현재까지의 합이 target과 같은지 확인
 *
 * 5. 합이 target과 같다면 정답을 1 증가
 *
 * 6. 모든 경우의 수를 확인하면 최종 정답을 반환
 *
 *
 * @시간복잡도
 *
 * 각 숫자마다 +와 - 두 가지 경우가 존재하므로 최대 2^N개의 경우를 확인
 * 따라서 시간복잡도는 O(2^N)
 *
 *
 * @공간복잡도
 *
 * DFS의 재귀 깊이는 숫자의 개수 N과 같으므로 O(N)의 추가 공간이 필요
 *
 */

class Solution {

    // target을 만들 수 있는 경우의 수
    int answer = 0;

    public int solution(int[] numbers, int target) {

        // DFS 시작
        //
        // index = 현재 확인할 숫자의 위치
        // sum = 현재까지 계산된 값
        dfs(numbers, target, 0, 0);

        return answer;
    }

    // DFS
    public void dfs(
            int[] numbers,
            int target,
            int index,
            int sum
    ) {

        // 모든 숫자를 사용한 경우
        if (index == numbers.length) {

            // 현재까지의 합이 target과 같다면
            // target을 만들 수 있는 경우이므로 정답 증가
            if (sum == target) {
                answer++;
            }

            return;
        }

        // 현재 숫자를 더하는 경우
        dfs(
                numbers,
                target,
                index + 1,
                sum + numbers[index]
        );

        // 현재 숫자를 빼는 경우
        dfs(
                numbers,
                target,
                index + 1,
                sum - numbers[index]
        );
    }
}
