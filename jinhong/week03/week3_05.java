package week03;

/**
 * @see All
 * @Language Java (OpenJDK 25.02)
 * @문제번호 42860 [조이스틱]
 * @문제유형 탐욕법(Greedy)
 * @문제설명
 * 조이스틱으로 알파벳 이름을 완성하세요. 맨 처음엔 A로만 이루어져 있습니다.
 * ex) 완성해야 하는 이름이 세 글자면 AAA, 네 글자면 AAAA
 * 조이스틱을 각 방향으로 움직이면 아래와 같습니다.
 *
 *  ▲ - 다음 알파벳
 *  ▼ - 이전 알파벳 (A에서 아래쪽으로 이동하면 Z로)
 *  ◀ - 커서를 왼쪽으로 이동 (첫 번째 위치에서 왼쪽으로 이동하면 마지막 문자에 커서)
 *  ▶ - 커서를 오른쪽으로 이동 (마지막 위치에서 오른쪽으로 이동하면 첫 번째 문자에 커서)
 * 예를 들어 아래의 방법으로 "JAZ"를 만들 수 있습니다.
 *
 *  - 첫 번째 위치에서 조이스틱을 위로 9번 조작하여 J를 완성합니다.
 *  - 조이스틱을 왼쪽으로 1번 조작하여 커서를 마지막 문자 위치로 이동시킵니다.
 *  - 마지막 위치에서 조이스틱을 아래로 1번 조작하여 Z를 완성합니다.
 * 따라서 11번 이동시켜 "JAZ"를 만들 수 있고, 이때가 최소 이동입니다.
 * 만들고자 하는 이름 name이 매개변수로 주어질 때, 이름에 대해 조이스틱 조작 횟수의 최솟값을 return 하도록 solution 함수를 만드세요.
 *
 * @제한사항
 * name은 알파벳 대문자로만 이루어져 있습니다.
 * name의 길이는 1 이상 20 이하입니다.
 *
 * @TestCase(I/O)
 * | name     | return |
 * | "JEROEN" | 56     |
 * | "JAN"    | 23     |
 */

/**
 * 조건1. [상하 조작] 'A'에서 위로 올리기 vs
 *       'Z'에서 아래로 내리기 중 버튼을 더 적게 누르는 쪽을 선택한다.
 *
 * 조건2. [좌우 조작] 글자를 바꾸러 갈 때,
 *       이미 완성된 'A' 무더기는 건너뛰고 되돌아가는 게 빠른지 계산한다.
 *
 * 조건3. [최종 합산] 알파벳을 바꾼 횟수와 커서를 움직인
 *       최솟값을 더해 게임을 끝내는 최소 점수를 구한다.
 */

public class week3_05 {
    public int solution(String name) {
        int answer = 0;
        int n = name.length();

        for (int i = 0; i < n; i++) {
            char c = name.charAt(i);
            answer += Math.min(c - 'A', 'Z' - c + 1);
        }

        int minMove = n - 1;

        for (int i = 0; i < n; i++) {
            int next = i + 1;
            while (next < n && name.charAt(next) == 'A') {
                next++;
            }

            int backAndForth = Math.min(i * 2 + n - next, (n - next) * 2 + i);

            minMove = Math.min(minMove, backAndForth);
        }

        answer += minMove;
        return answer;
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
        week3_05 sol = new week3_05();

        profileTestCase("1", "56", new Runnable() {
            @Override
            public void run() {
                String name = "JEROEN";
                System.out.println("실제 출력 결과: " + sol.solution(name));
            }
        });

        profileTestCase("2", "23", new Runnable() {
            @Override
            public void run() {
                String name = "JAN";
                System.out.println("실제 출력 결과: " + sol.solution(name));
            }
        });

    }
}
