/**
 * @문제설명
 * 비내림차순으로 정렬된 수열이 주어질 때, 다음 조건을 만족하는 부분 수열을 찾으려고 합니다.
 *
 * 1. 기존 수열에서 임의의 두 인덱스의 원소와 그 사이의 원소를 모두 포함하는
 *    부분 수열이어야 합니다.
 * 2. 부분 수열의 합은 k입니다.
 * 3. 합이 k인 부분 수열이 여러 개인 경우 길이가 짧은 수열을 찾습니다.
 * 4. 길이가 짧은 수열이 여러 개인 경우 앞쪽(시작 인덱스가 작은)에 나오는
 *    수열을 찾습니다.
 *
 * 수열을 나타내는 정수 배열 sequence와 부분 수열의 합을 나타내는 정수 k가
 * 매개변수로 주어질 때, 위 조건을 만족하는 부분 수열의 시작 인덱스와
 * 마지막 인덱스를 배열에 담아 return 합니다.
 *
 * 이때 수열의 인덱스는 0부터 시작합니다.
 *
 *
 * @제한사항
 * 5 ≤ sequence의 길이 ≤ 1,000,000
 * 1 ≤ sequence의 원소 ≤ 1,000
 * sequence는 비내림차순으로 정렬되어 있습니다.
 *
 * 5 ≤ k ≤ 1,000,000,000
 * k는 항상 sequence의 부분 수열로 만들 수 있는 값입니다.
 *
 * @풀이
 *
 * 이 문제는 수열의 원소가 모두 양수라는 점을 이용하여
 * 투 포인터(Two Pointer) 알고리즘으로 해결할 수 있습니다.
 *
 * 왼쪽 포인터(left)와 오른쪽 포인터(right)를 사용하여
 * 현재 부분 수열의 범위를 관리합니다.
 *
 * 현재 구간의 합을 sum이라고 하면 다음과 같이 처리합니다.
 *
 * 1. sum < k
 *    → 합이 부족하므로 right를 오른쪽으로 이동시켜
 *      새로운 원소를 추가합니다.
 *
 * 2. sum == k
 *    → 조건을 만족하는 부분 수열입니다.
 *      현재 구간의 길이를 확인하고 최적의 구간인지 비교합니다.
 *      이후 더 짧은 구간이 있는지 확인하기 위해 left를 이동시킵니다.
 *
 * 3. sum > k
 *    → 합이 너무 크므로 left를 오른쪽으로 이동시켜
 *      가장 왼쪽의 원소를 제거합니다.
 *
 * 모든 원소가 양수이기 때문에
 * right가 증가하면 sum은 반드시 증가하고,
 * left가 증가하면 sum은 반드시 감소합니다.
 *
 * 따라서 모든 구간을 하나씩 확인하는 것처럼 보여도
 * left와 right가 각각 최대 N번만 이동하므로
 * O(N)에 해결할 수 있습니다.
 *
 *
 * @예시
 *
 * sequence = [1, 2, 3, 4, 5]
 * k = 7
 *
 * right를 이동시키면서 합을 확인합니다.
 *
 * [1]       → 1
 * [1, 2]    → 3
 * [1, 2, 3] → 6
 * [1, 2, 3, 4] → 10
 *
 * sum이 k보다 커졌으므로 left를 이동합니다.
 *
 * [2, 3, 4] → 9
 * [3, 4]    → 7
 *
 * 합이 7이 되었으므로 [2, 3]을 후보로 저장합니다.
 *
 *
 * @최적의 구간을 선택하는 방법
 *
 * 현재 합이 k인 구간을 발견했을 때
 * 현재 구간의 길이와 기존에 저장한 구간의 길이를 비교합니다.
 *
 * 현재 구간의 길이가 더 짧다면 결과를 갱신합니다.
 *
 * 길이가 같은 경우에는 시작 인덱스를 비교합니다.
 *
 * 문제 조건상 앞쪽의 수열을 선택해야 하므로
 * 시작 인덱스가 작은 구간을 유지합니다.
 *
 *
 * @시간복잡도
 * O(N)
 *
 * left와 right 포인터가 각각 최대 N번 이동합니다.
 * 따라서 전체 시간 복잡도는 O(N)입니다.
 *
 *
 * @공간복잡도
 * O(1)
 *
 * 별도의 배열이나 자료구조를 사용하지 않고
 * 포인터와 합, 결과 인덱스만 저장하므로 O(1)입니다.
 */
class Solution {

    public int[] solution(int[] sequence, int k) {

        int left = 0;
        int right = 0;

        long sum = 0;

        int answerLeft = 0;
        int answerRight = sequence.length - 1;

        while (right < sequence.length) {

            // 오른쪽 원소 추가
            sum += sequence[right];

            // 합이 k보다 크면 왼쪽 원소 제거
            while (sum > k && left <= right) {
                sum -= sequence[left];
                left++;
            }

            // 합이 k인 경우
            if (sum == k) {

                int currentLength = right - left;
                int answerLength = answerRight - answerLeft;

                // 더 짧은 구간이라면 결과 갱신
                if (currentLength < answerLength) {
                    answerLeft = left;
                    answerRight = right;
                }

                // 길이가 같은 경우에는
                // 시작 인덱스가 작은 구간을 선택
                else if (currentLength == answerLength && left < answerLeft) {
                    answerLeft = left;
                    answerRight = right;
                }
            }

            right++;
        }

        return new int[]{answerLeft, answerRight};
    }
}
