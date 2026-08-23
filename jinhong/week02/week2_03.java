/**
 * @see All
 * @Language Java (OpenJDK 25.02)
 * @프로그래머스 42883
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
 * | number	      | k	| return   |
 * | "1924"	      | 2	| "94"     |
 * | "1231234"    | 3	| "3234"   |
 * | "4177252841" | 4	| "775841" |
 */

/**
 * 조건1. 자릿수 n개를 제거한 후 가장 큰수 구하기
 *
 * @description
 * 추가 조건 발생
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

class Solution {
    public String solution(String number, int k) {
        char[] arr = new char[number.length() - k];

        int top = 0;
        for(int i=0; i< number.length(); i++) {
            char current = number.charAt(i);

            while(k > 0 && top > 0 && arr[top-1] < current) {
                top--;
                k--;
            }

            if (top < arr.length) {
                arr[top++] = current;
            } else {
                k--;
            }
        }
        return new String(arr);
    }
}