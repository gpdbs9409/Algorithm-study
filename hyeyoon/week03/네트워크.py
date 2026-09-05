# 문제 설명
네트워크란 컴퓨터 상호 간에 정보를 교환할 수 있도록 연결된 형태를 의미합니다. 예를 들어, 컴퓨터 A와 컴퓨터 B가 직접적으로 연결되어있고, 컴퓨터 B와 컴퓨터 C가 직접적으로 연결되어 있을 때 컴퓨터 A와 컴퓨터 C도 간접적으로 연결되어 정보를 교환할 수 있습니다. 따라서 컴퓨터 A, B, C는 모두 같은 네트워크 상에 있다고 할 수 있습니다.

컴퓨터의 개수 n, 연결에 대한 정보가 담긴 2차원 배열 computers가 매개변수로 주어질 때, 네트워크의 개수를 return 하도록 solution 함수를 작성하시오.

제한사항
컴퓨터의 개수 n은 1 이상 200 이하인 자연수입니다.
각 컴퓨터는 0부터 n-1인 정수로 표현합니다.
i번 컴퓨터와 j번 컴퓨터가 연결되어 있으면 computers[i][j]를 1로 표현합니다.
computer[i][i]는 항상 1입니다.
입출력 예
n	computers	return
3	[[1, 1, 0], [1, 1, 0], [0, 0, 1]]	2
3	[[1, 1, 0], [1, 1, 1], [0, 1, 1]]	1

# 풀이 아이디어(왜 이렇게 풀었는지)
이 문제는 그래프의 연결 요소(Connected Component) 개수를 구하는 문제이다.
DFS 또는 BFS를 이용해 하나의 노드에서 연결된 모든 노드를 탐색한다.
모든 노드를 순회하면서 아직 방문하지 않은 노드를 발견하면 DFS를 시작한다.
DFS 한 번을 수행=그래프 전부 탐색 =연결요소 갯수와 동일하다. 
DFS를 호출할 때마다 answer += 1 한다.
    
# 정답 
def solution(n, computers):
    
    answer = 0
    visited=n*[False]
    
    def dfs(node):
        visited[node]=True
        for idx,computer in enumerate(computers[node]):
            if visited[idx]==False and computer==1:
                dfs(idx)
                
    for i in range(len(computers)):
        if visited[i]==False:
            dfs(i)
            answer+=1
       
    return answer



# 공간복잡도
O(n)의 공간이 필요하다. 
1. visited = n * [False] 에서 n의 공간이 필요하다.
2. dfs는 stack구조로 쌓이면서 최대한 N만큼 호출되므로 n의 공간복잡도가 필요하다.
3. for in range(len(computers))라는 또 다른 for문이 있긴 하지만 2번이 동시에 실행되는 것은 아니므로 무관 


#시간 복잡도
O(N^2)이다. 

1.각 컴퓨터를 방문할때마다 인접행렬을 모두 확인 computers[node]
2.dfs하나를 호출-> ?? 

아직 잘 모르곘음. 


# 느낀점
1.연결요소문제는 다 비슷비슷해서 패턴만 암기하면 큰 문제가 없을 것 같다.
2. 다만 그래프 표현하는 방식이 문제마다 다른데, 가장 기본형은 이차원배열로 자신이 연결된 노드를 [a,b,c]이런식으로 주는 형태였다면 이건 연결 여부를 0,1,로 나타내는 방식이라 조금 생소했는데, 그냥 연결된 것을 어떻게 표현할것인가만 좀 고민하면 되는것이었다. 
3. index와 값을 같이 뽑아야하는 경우이므로 enumerate를 꼭 알아둬야 풀 수있었다. enumerate문법을 잊지 않도록하자
