package week02;
/**
 * @see All
 * @Language Java (OpenJDK 25.02)
 * @문제번호 42883
 * @문제설명
 * 어떤 숫자에서 k개의 수를 제거했을 때 얻을 수 있는 가장 큰 숫자를 구하려 합니다.
 * 예를 들어, 숫자 1924에서 수 두 개를 제거하면 [19, 12, 14, 92, 94, 24] 를 만들 수 있습니다.
 * 이 중 가장 큰 숫자는 94 입니다.
 *
 * 문자열 형식으로 숫자 number와 제거할 수의 개수 k가 solution 함수의 매개변수로 주어집니다.
 * number에서 k 개의 수를 제거했을 때 만들 수 있는 수 중 가장 큰 숫자를 문자열 형태로 return 하도록 solution 함수를 완성하세요.
 *
 * @제한사항
 * number는 2자리 이상, 1,000,000자리 이하인 숫자입니다.
 * k는 1 이상 number의 자릿수 미만인 자연수입니다.
 *
 * @TestCase(I/O)
 * | number	      | k   | return   |
 * | "1924"	      | 2   | "94"     |
 * | "1231234"    | 3   | "3234"   |
 * | "4177252841" | 4   | "775841" |
 */

/**
 * 조건1. 자릿수 n개를 제거한 후 가장 큰수 구하기
 *
 * @description
 * 추가 조건 발생
 * 조건2. 자리수가 부족할 경우에는 해당 자리수는 뒤에서 가장 큰수로 채워줘야한다.
 * 예제2) 총갯수 = 7 - 3  즉, 4자리가 들어와야함
 *       [1,X,X,X] => [2,X,X,X] => [3,X,X,X] : 3이 1보다 크기때문에 1번째 자리 종료
 *
 *       문제 발생
 *       남은 자릿수 - 3개(1번째 자리 진행중) | 남은 숫자 - 1 / 2 / 3 / 4
 *       [3,1,X,X] = (본인 수보다 큼) => [3,2,X,X] = (본인 수보다 큼) => [3,3,X,X] = (본인 수보다 큼) => [3,4,X,X] : error 발생
 *
 *       남은 자릿수 - 2개(1번째 자리 진행중) | 남은 숫자 - 2 / 3 / 4
 *       [3,1,X,X] = (본인 수보다 큼) => [3,2,X,X] = (본인 수보다 크지만 채워둘 자릿수 부족) => [3,2,3,4]
 */

class week2_03 {

    public String solution(String number, int k) {
        char[] arr = new char[number.length() - k];

        int top = 0;
        for(int i=0; i< number.length(); i++) {
            char current = number.charAt(i);

            // k가 아직 감소될 수 있는 상태라면, top이 비교할 숫자가 있는지, 만약 이전 숫자가 현재 숫자보다 작다면 제거
            while(k > 0 && top > 0 && arr[top-1] < current) {
                top--;
                k--;
            }

            if (top < arr.length) {
                // 배열에 자리에 채울 공간이 있다면 탐색된 숫자를 삽입
                arr[top++] = current;
            } else {
                // 배열이 풀로 찬 경우 숫자가 남아 있다면, 현재 숫자 삭제
                k--;
            }
        }
        return new String(arr);
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
        // 현재 클래스인 week2_03의 객체를 생성합니다.
        week2_03 sol = new week2_03();

        profileTestCase("1", "94", new Runnable() {
            @Override
            public void run() {
                String num1 = "1924";
                int k1 = 2;
                System.out.println("실제 출력 결과: " + sol.solution(num1, k1));
            }
        });

        profileTestCase("2", "3234", new Runnable() {
            @Override
            public void run() {
                String num2 = "1231234";
                int k2 = 3;
                System.out.println("실제 출력 결과: " + sol.solution(num2, k2));
            }
        });

        profileTestCase("3", "775841", new Runnable() {
            @Override
            public void run() {
                String num3 = "4177252841";
                int k3 = 4;
                System.out.println("실제 출력 결과: " + sol.solution(num3, k3));
            }
        });
    }
}