# 문제 설명
n개의 송전탑이 전선을 통해 하나의 트리 형태로 연결되어 있습니다. 당신은 이 전선들 중 하나를 끊어서 현재의 전력망 네트워크를 2개로 분할하려고 합니다. 이때, 두 전력망이 갖게 되는 송전탑의 개수를 최대한 비슷하게 맞추고자 합니다.
송전탑의 개수 n, 그리고 전선 정보 wires가 매개변수로 주어집니다. 전선들 중 하나를 끊어서 송전탑 개수가 가능한 비슷하도록 두 전력망으로 나누었을 때, 두 전력망이 가지고 있는 송전탑 개수의 차이(절대값)를 return 하도록 solution 함수를 완성해주세요.

# 문제 접근(풀이)

1. 그래프 형태이며, 전선을 하나 끊었을 때 생기는 두 연결 요소의 개수를 세는 문제라고 생각했다.
2. `wires`의 전선을 하나씩 제외하면서 DFS를 수행한다.
3. 한쪽 연결 요소의 송전탑 개수를 `left`라고 하면, 전체 송전탑은 `n`개이므로 다른 쪽은 `n - left`로 구할 수 있다.
4. 각 전선을 끊었을 때의 `|left - right|`를 저장하고, 그중 최솟값을 반환한다.
  
#sol 

def solution(n, wires):
    answer = -1    
    candidate=[]

  #하나씩 끊어가면서 dfs 연결개수 세기 해야함. 
    
    for i in range(len(wires)):
        broken_wires = wires[:i] + wires[i+1:]
        visited=(n+1)*[False]
        def dfs(node):
            visited[node]=True
        
            for a,b in broken_wires:

                    if node==a and visited[b]==False:
                        dfs(b)
                    elif node==b and visited[a]==False:
                        dfs(a) 
                
        
        dfs(1)
        cnt_true=visited.count(True)
        cnt_false=n-cnt_true
        candidate.append(abs(cnt_true-cnt_false))
        
    answer=min(candidate)    
        
        
    return answer

# 시간복잡도 
O(n^2) 
1.전선의 갯수는 n-1개이고 , 전선의 갯수를 하나씩 끊어보는데 n만큼 사용된다.
2.또한 1로 인해서 끊어진 전선그룹을  탐색하는데에 O(n)만큼 소요된다. 
  =>O(n^2) 


#공간복잡도
  
`broken_wires`, `visited`, DFS의 최대 재귀 깊이가 각각 O(N)의 공간을 사용한다.
처음에는 `broken_wires`가 O(N), DFS가 O(N)이므로 O(N²)이라고 생각했다.

하지만 공간복잡도는 각각 사용하는 공간을 곱하는 것이 아니라 동시에 사용되는 메모리의 크기를 합해서 계산한다.

O(N) + O(N) + O(N) = O(3N) = O(N)

반면 시간복잡도에서는 O(N)번의 반복마다 O(N)의 작업을 수행하므로 O(N) × O(N) = O(N²)이 된다.
  
#느낀점 
broken_wires = wires[:i] + wires[i+1:]로 전력망을 하나씩 끊을수있다는 생각을 못해서 새로운 아이디어를 얻었다. 
돌아가면서 하나씩 제외하고 싶을때 이 방법을 떠올려 보면 좋을것같다. 이런 슬라이싱은 문자열에서만 쓸 수있는 줄 알았다. 

각 요소의 갯수를 어떻게 새지?라고 생각했는데 연결요소 덩어리가 총 2개밖에 되지 않으므로 vistied=True인 갯수를 세면 자연히 각 두개의 연결요소 갯수를 셀 수있었다. 
그럼 연결요소가 3개이상일때는 어떤 컨셉을 가져갈지 궁금했다. 


#연결요소가 3개 이상일 때 각 노드의 갯수 세보기 

=>콜스택의 개념을 사용한다. 
  
dfs(1)
count = 1
│
│  dfs(2)
│  count = 1
│  │
│  │  dfs(3)
│  │  count = 1
│  │  return 1
│  │
│  count = 1 + 1 = 2
│  return 2
│
count = 1 + 2 = 3
return 3



#내 아래쪽으로 연결된 노드가 몇 개인지 받아서 더한다. 
def dfs(node):
    visited[node] = True
    count = 1  # 현재 node 자신
    for next_node in graph[node]:
        if not visited[next_node]:
            count += dfs(next_node)
    return count

groups = []

for i in range(1, n + 1):
    if not visited[i]:
        count = dfs(i)
        groups.append(count)




