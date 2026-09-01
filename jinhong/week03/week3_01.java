package week03;

/**
 * @see All
 * @Language Java (OpenJDK 25.02)
 * @문제번호 43165 [타겟 넘버]
 * @문제유형 깊이/너비 우선 탐색(DFS/BFS)
 * @문제설명
 * n개의 음이 아닌 정수들이 있습니다. 이 정수들을 순서를 바꾸지 않고 적절히 더하거나 빼서 타겟 넘버를 만들려고 합니다.
 * 예를 들어 [1, 1, 1, 1, 1]로 숫자 3을 만들려면 다음 다섯 방법을 쓸 수 있습니다.
 *
 * -1+1+1+1+1 = 3
 * +1-1+1+1+1 = 3
 * +1+1-1+1+1 = 3
 * +1+1+1-1+1 = 3
 * +1+1+1+1-1 = 3
 *
 * 사용할 수 있는 숫자가 담긴 배열 numbers, 타겟 넘버 target이 매개변수로 주어질 때
 * 숫자를 적절히 더하고 빼서 타겟 넘버를 만드는 방법의 수를 return 하도록 solution 함수를 작성해주세요.
 *
 * @제한사항
 * 주어지는 숫자의 개수는 2개 이상 20개 이하입니다.
 * 각 숫자는 1 이상 50 이하인 자연수입니다.
 * 타겟 넘버는 1 이상 1000 이하인 자연수입니다.
 *
 * @TestCase(I/O)
 * | numbers         | target | return |
 * | [1, 1, 1, 1, 1] | 3      | 5      |
 * | [4, 1, 2, 1]    | 4      | 2      |
 */

/**
 * 조건1. 모든 값을 깊이 탐색을 이용하여 생각하면 아래와 같은 트리 구조방식(tree구조 말하지만, 결국 DFS)으로 풀수 있을 것.
 *   ex) Start
 *        ㄴㅡㅡ> 4로 시작
 *          ㄴㅡㅡ> 4 + 1 = 5 [ X ]
 *            ㄴㅡㅡ> 5 + 2 = 7 [ X ]
 *              ㄴㅡㅡ> 7 + 1 = 8 [ X ]
 *              ㄴㅡㅡ> 7 - 1 = 6 [ X ]
 *            ㄴㅡㅡ> 5 - 2 = 3 [ X ]
 *              ㄴㅡㅡ> 3 + 1 = 4 [ O - Stack 1 ]
 *              ㄴㅡㅡ> 3 - 1 = 2 [ X ]
 *          ㄴㅡㅡ> 4 - 1 = 3 [ X ]
 *            ... 내용 생략
 *        ㄴㅡㅡ> -4로 시작
 *         ... 내용 생략
 * 조건2. 즉, 위 상단의 DFS는 2^N(배열의 갯수) 만큼 해당 값을 확인해야함.
 *     - 매개변수를 "-", "+"만으로 구하라는 조건으로 인해서 2가 되는 것이며, 해당 값의 공식을 구하기 위해서는 위와 같은 공식이 작성된다.
 */

public class week3_01 {
    int answer = 0;
    public int solution(int[] numbers, int target) {
        dfs(numbers, target, 0, 0);
        return answer;
    }

    public void dfs(int[] numbers, int target, int index, int sum) {
        if (index == numbers.length) {
            if (sum == target) {
                answer++;
            }
            return;
        }

        dfs(numbers, target, index + 1, sum + numbers[index]);
        dfs(numbers, target, index + 1, sum - numbers[index]);
    }

    public static void profileTestCase(String testNumber, String expected, Runnable testAction) {
        long startTime = System.nanoTime();
        testAction.run(); 
        long endTime = System.nanoTime();

        long durationNano = endTime - startTime;
        double durationMilli = durationNano / 1000000.0;

        System.out.println("🎯 [테스트 " + testNumber + " 예상 결과]: " + expected);
        System.out.println("⏳ [처리 시간]: " + durationMilli + " ms");
        System.out.println("--------------------------------------------------\n");
    }

    public static void main(String[] args) {
        week3_01 sol = new week3_01();

        profileTestCase("1", "5", new Runnable() {
            @Override
            public void run() {
                int[] numbers = {1, 1, 1, 1, 1};
                int target = 4;
                System.out.println("실제 출력 결과: " + sol.solution(numbers, target));
            }
        });

        profileTestCase("2", "2", new Runnable() {
            @Override
            public void run() {
                int[] numbers = {4, 1, 2, 1};
                int target = 4;
                System.out.println("실제 출력 결과: " + sol.solution(numbers, target));
            }
        });

    }
}
