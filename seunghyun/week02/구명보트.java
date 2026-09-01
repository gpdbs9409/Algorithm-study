/**
 * @풀이
 *
 * 그리디와 투 포인터 동시 사용
 * 
 * @처리순서
 *
 * 1. people을 오름차순으로 정렬
 *
 * 2. left는 가장 가벼운 사람을 가리키고, right는 가장 무거운 사람을 가리키도록 처리
 *
 * 3. 가장 무거운 사람과 가장 가벼운 사람의 몸무게 합을 확인
 *
 * 4. 두 사람의 몸무게 합이 limit 이하라면 두 사람을 한 보트에 태울 수 있음
 *    따라서 left를 오른쪽으로 이동
 *
 * 5. 두 사람의 몸무게 합이 limit을 초과한다면 가장 무거운 사람은 누구와도 함께 탈 수 없으므로
 *    가장 무거운 사람을 혼자 태우도록 처리
 *
 * 6. 가장 무거운 사람은 두 경우 모두 보트를 하나 사용하므로 right를 왼쪽으로 한 칸 이동
 *
 * 7. 위 과정을 left <= right인 동안 반복
 *
 * 8. 사용한 보트의 개수가 최종 정답
 *
 *
 * @시간복잡도
 *
 * people을 정렬하는 데 O(N log N)이 필요
 *
 * 정렬 이후에는 left와 right를 사용하여 배열을 한 번 순회하므로 O(N)이 필요
 *
 * 따라서 전체 시간 복잡도는 O(N log N)
 *
 *
 * @공간복잡도
 *
 * 별도의 배열을 생성하지 않고 주어진 people 배열을 정렬하여 사용
 *
 * left와 right 등의 변수만 사용하므로 추가 공간 복잡도는 O(1)
 */
class Solution {

    public int solution(int[] people, int limit) {

        // 몸무게를 오름차순으로 정렬
        Arrays.sort(people);

        // 가장 가벼운 사람
        int left = 0;

        // 가장 무거운 사람
        int right = people.length - 1;

        // 필요한 구명보트 개수
        int answer = 0;

        // 모든 사람이 보트를 탈 때까지 반복
        while (left <= right) {

            // 가장 무거운 사람과 가장 가벼운 사람이
            // 함께 탈 수 있는 경우
            if (people[left] + people[right] <= limit) {

                // 가장 가벼운 사람도 함께 태움
                left++;
            }

            // 가장 무거운 사람은
            // 위 경우와 관계없이 반드시 보트를 하나 사용함
            right--;

            // 구명보트 하나 사용
            answer++;
        }

        return answer;
    }
}
