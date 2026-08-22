```
/**
 * @see All
 * @Language Java (OpenJDK 25.02)
 * @문제설명
 * 비내림차순으로 정렬된 수열이 주어질 때, 다음 조건을 만족하는 부분 수열을 찾으려고 합니다.
 * 기존 수열에서 임의의 두 인덱스의 원소와 그 사이의 원소를 모두 포함하는 부분 수열이어야 합니다.
 * 부분 수열의 합은 k입니다.
 * 합이 k인 부분 수열이 여러 개인 경우 길이가 짧은 수열을 찾습니다.
 * 길이가 짧은 수열이 여러 개인 경우 앞쪽(시작 인덱스가 작은)에 나오는 수열을 찾습니다.
 * 수열을 나타내는 정수 배열 sequence와 부분 수열의 합을 나타내는 정수 k가 매개변수로 주어질 때, 위 조건을 만족하는 부분 수열의 시작 인덱스와 마지막 인덱스를 배열에 담아 return 하는 solution 함수를 완성해주세요. 이때 수열의 인덱스는 0부터 시작합니다.
 *
 * 제한사항
 * 5 ≤ sequence의 길이 ≤ 1,000,000
 * 1 ≤ sequence의 원소 ≤ 1,000
 * sequence는 비내림차순으로 정렬되어 있습니다.
 * 5 ≤ k ≤ 1,000,000,000
 * k는 항상 sequence의 부분 수열로 만들 수 있는 값입니다.
 *
 * @TestCase(I/O)
 * | sequence	           | k	| result
 * | [1, 2, 3, 4, 5]	   | 7	| [2, 3]
 * | [1, 1, 1, 2, 3, 4, 5] | 5	| [6, 6]
 * | [2, 2, 2, 2, 2]	   | 6	| [0, 2]
 */

/**
 * 조건1 K 값에 해당 값을 배열에 담아 출력한다.
 *.     * 결국 answer의 값은 2개(시작점, 종료지점)로 처리된다.
 * 조건2 K 값이 동일 하다면 길이(Index)가 더 짧은 값을 정답으로 처리한다.
 */
``

def solution(sequence, k):
    answer = []
    left=0
    right=0
    part=[]
    total=sequence[0]
    partdiff=[]
    while right<len(sequence):
        
        if total==k:
            part.append([left,right])
            total -= sequence[left]
            left+=1
            
        elif total<k:
            if right < len(sequence)-1:
                right+=1
                total+=sequence[right]
            else:
                break
        elif total>k:
            
            total-=sequence[left]
            left+=1
    for i in range(len(part)):
        a,b=part[i]
        partdiff.append([b-a,a,b])
            
    mini=min(partdiff)
    answer=mini[1:3]
    
    return answer
