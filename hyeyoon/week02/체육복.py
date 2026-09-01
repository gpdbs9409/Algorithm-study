# 문제 설명

 점심시간에 도둑이 들어, 일부 학생이 체육복을 도난당했습니다.
 다행히 여벌 체육복이 있는 학생이 이들에게 체육복을 빌려주려 합니다.

 학생들의 번호는 체격 순으로 매겨져 있어,
 바로 앞번호의 학생이나 바로 뒷번호의 학생에게만 체육복을 빌려줄 수 있습니다.
 예를 들어, 4번 학생은 3번 학생이나 5번 학생에게만 체육복을 빌려줄 수 있습니다.

 체육복이 없으면 수업을 들을 수 없기 때문에
 체육복을 적절히 빌려 최대한 많은 학생이 체육수업을 들어야 합니다.

 전체 학생의 수 n,
 체육복을 도난당한 학생들의 번호가 담긴 배열 lost,
 여벌의 체육복을 가져온 학생들의 번호가 담긴 배열 reserve가 매개변수로 주어질 때,
 체육수업을 들을 수 있는 학생의 최댓값을 return 하도록 solution 함수를 작성해주세요.

# 제한사항
 - 전체 학생의 수는 2명 이상 30명 이하입니다.
 - 체육복을 도난당한 학생의 수는 1명 이상 n명 이하이고 중복되는 번호는 없습니다.
 - 여벌의 체육복을 가져온 학생의 수는 1명 이상 n명 이하이고 중복되는 번호는 없습니다.
 - 여벌 체육복이 있는 학생만 다른 학생에게 체육복을 빌려줄 수 있습니다.
 - 여벌 체육복을 가져온 학생이 체육복을 도난당했을 수 있습니다.
   이때 이 학생은 체육복을 하나만 도난당했다고 가정하며,
   남은 체육복이 하나이기에 다른 학생에게는 체육복을 빌려줄 수 없습니다.




## 문제 접근

앞번호의 친구에게 무조건 먼저 체육복을 빌려준다.

현재 학생이 앞번호의 친구에게 먼저 빌려주는 순간순간의 최선의 선택이 전체적으로도 최선의 선택으로 이어지기 때문에 그리디 알고리즘으로 접근했다.


## 어려웠던(헷갈렸던) 점

처음에 `lost`와 `reserve`에 모두 포함된 학생은 본인의 여벌 체육복을 입으면 된다는 조건을 누락해서 틀렸다.


## 배운 점

`set` 집합 자료구조를 평소에 자주 쓰지 않아서 `add`, `remove` 같은 기본 문법도 잊어버렸다.  
집합 자료구조도 잊어버리지 않도록 계속 공부해야겠다는 생각이 들었다.

`sol2`의 더 쉬운 풀이를 보면 사실 `student`라는 집합을 따로 정의하지 않아도 된다.

`student` 전체를 직접 추가하거나 삭제하면서 관리할 필요 없이, 전체 학생 수 `n`은 고정해두고 마지막에 체육복이 없는 학생의 수만 빼면 되기 때문이다.

처음에 자료구조를 선언할 때 `set`, `list`, `queue` 등을 일단 만드는 것이 아니라,

"이 자료구조를 왜 쓰는지?"
"굳이 단순 변수가 아니라 자료구조로 관리해야 하는지?"

를 먼저 생각해봐야겠다. 



#원래풀이  sol1

def solution(n, lost, reserve):
    answer = 0
    lost = set(lost)
    reserve = set(reserve)

    both = lost & reserve
    
    # 전체 학생
    student = set()

    lost = lost - both
    reserve = reserve - both

    # 전체 학생에 대한 집합을 만듦
    for i in range(1, n + 1):
        student.add(i)

    student -= lost

    # 여분의 학생들을 돌아가면서 빌려줌
    for i in reserve:
        if i + 1 in lost:
            will_add = i + 1
            student.add(will_add)
            lost.remove(will_add)

        elif i - 1 in lost:
            will_add = i - 1
            student.add(will_add)
            lost.remove(will_add)

    return len(student)


#더 쉬운 풀이 sol2
def solution(n, lost, reserve):
    lost = set(lost)
    reserve = set(reserve)

    # 잃어버렸지만 여벌도 있는 학생
    both = lost & reserve
    lost -= both
    reserve -= both

    # 여벌 체육복 빌려주기
    for i in sorted(reserve):
        if i - 1 in lost:
            lost.remove(i - 1)
        elif i + 1 in lost:
            lost.remove(i + 1)

    return n - len(lost)
