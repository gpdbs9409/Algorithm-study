# 문제: 게임 맵 최단거리

## 문제 접근 및 풀이 순서 Idea

1. 시작점 `(0, 0)`에서 도착점 `(n-1, m-1)`까지 이동해야 하는 그래프 탐색 문제라고 생각했다.
2. 문제에서 **최솟값(최단거리)**을 요구하므로 DFS보다 BFS가 적합하다.
3. BFS는 시작점에서 가까운 노드부터 순서대로 탐색하기 때문에, 가중치가 없는 그래프에서는 처음 도착한 경로가 최단거리가 된다.
4. 현재 위치에서 상/하/좌/우 4방향을 확인하고,
   - 맵의 범위를 벗어나지 않고
   - 벽(`0`)이 아니며
   - 아직 방문하지 않은 곳
   만 Queue에 추가한다.
5. 각 위치까지의 거리를 저장하면서 BFS를 진행하고, 목적지에 도달할 수 없다면 `-1`을 반환한다.

---

# sol1

처음에는 BFS로 탐색하면서 이동 횟수만 증가시키면 된다고 생각했다.

```python
from collections import deque

def solution(maps):
    n = len(maps)
    m = len(maps[0])

    queue = deque([(0, 0)])
    visited = [[False] * m for _ in range(n)]
    visited[0][0] = True

    count = 1

    while queue:
        x, y = queue.popleft()

        if x == n - 1 and y == m - 1:
            return count

        dx = [-1, 1, 0, 0]
        dy = [0, 0, -1, 1]

        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]

            if 0 <= nx < n and 0 <= ny < m:
                if maps[nx][ny] == 1 and visited[nx][ny] == False:
                    visited[nx][ny] = True
                    queue.append((nx, ny))

        count += 1

    return -1
```

## 틀린 이유

`count`가 while문이 한 번 실행될 때마다 증가한다고 해서 현재 위치까지의 거리가 되는 것은 아니다.

BFS에서는 같은 거리의 여러 좌표가 Queue에 동시에 들어갈 수 있다.

예를 들어,

```text
거리 1 : (0,0)

거리 2 : (0,1), (1,0)

거리 3 : (0,2), (1,1), (2,0)
```

처럼 같은 거리에 있는 여러 좌표를 하나씩 `popleft()`하게 된다.

따라서 `while`문 실행 횟수와 실제 이동 거리는 같지 않다.

각 좌표가 시작점으로부터 얼마나 떨어져 있는지를 별도로 저장해야 한다.

---

# sol2

각 좌표까지의 거리를 Queue에 같이 저장하도록 수정했다.

```python
from collections import deque

def solution(maps):
    n = len(maps)
    m = len(maps[0])

    queue = deque([(0, 0, 1)])
    visited = [[False] * m for _ in range(n)]

    dx = [-1, 1, 0, 0]
    dy = [0, 0, -1, 1]

    while queue:
        x, y, distance = queue.popleft()
        visited[x][y] = True

        if x == n - 1 and y == m - 1:
            return distance

        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]

            if 0 <= nx < n and 0 <= ny < m:
                if maps[nx][ny] == 1 and visited[nx][ny] == False:
                    queue.append((nx, ny, distance + 1))

    return -1
```

## 틀린 이유

방문 처리를 Queue에서 꺼낼 때 하고 있다.

```python
x, y, distance = queue.popleft()
visited[x][y] = True
```

이렇게 하면 하나의 좌표가 Queue에 들어간 상태에서도 아직 `visited=False`이므로 다른 경로를 통해 같은 좌표가 Queue에 중복으로 들어갈 수 있다.

BFS에서는 **Queue에 넣는 순간 방문 처리**를 하는 것이 안전하다.

```python
visited[nx][ny] = True
queue.append((nx, ny, distance + 1))
```

또한 시작점 `(0, 0)`도 처음부터 방문 처리해야 한다.

---

# sol3 최종정답

```python
from collections import deque

def solution(maps):
    n = len(maps)
    m = len(maps[0])

    queue = deque([(0, 0, 1)])
    visited = [[False] * m for _ in range(n)]
    visited[0][0] = True

    dx = [-1, 1, 0, 0]
    dy = [0, 0, -1, 1]

    while queue:
        x, y, distance = queue.popleft()

        if x == n - 1 and y == m - 1:
            return distance

        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]

            if 0 <= nx < n and 0 <= ny < m:
                if maps[nx][ny] == 1 and visited[nx][ny] == False:
                    visited[nx][ny] = True
                    queue.append((nx, ny, distance + 1))

    return -1
```


---

# 시간복잡도

**O(N × M)**

맵의 크기가 N × M일 때 각 칸은 최대 한 번 Queue에 들어가고 한 번 방문된다.
각 칸에서는 상/하/좌/우 4방향을 확인하므로 O(4NM)의 연산이 필요하고,
상수는 제외하므로 최종 시간복잡도는 O(NM)이다.

# 공간복잡도

**O(N × M)**

방문 여부를 저장하는 `visited` 배열이 N × M 크기이므로 O(NM)의 공간을 사용한다.

또한 BFS 탐색을 위한 Queue에는 최악의 경우 맵의 칸 수에 비례하는 좌표가 저장될 수 있으므로 O(NM)의 공간을 사용한다.

`dx`, `dy`와 좌표 및 거리 변수들은 입력 크기와 관계없이 일정한 크기이므로 O(1)이다.

따라서

O(NM) + O(NM) + O(1) = O(NM)

이므로 최종 공간복잡도는 **O(NM)**이다.

---

# 배운점 및 느낀점

문제에서 **최단거리**를 요구하는 것을 보고 BFS를 떠올려야 한다.

DFS는 한 경로를 끝까지 탐색하기 때문에 처음 목적지에 도착한 경로가 최단거리라는 보장이 없다.

반면 BFS는 Queue를 사용해서 시작점에서 가까운 위치부터 탐색한다.

```text
거리 1
→ 거리 2
→ 거리 3
→ 거리 4
...
```

따라서 가중치가 없는 그래프에서는 목적지에 처음 도착했을 때의 거리가 최단거리가 된다.

또 처음에는 while문이 반복될 때마다 `count += 1`을 하면 거리를 셀 수 있을 것이라고 생각했지만, BFS에서는 같은 거리에 있는 여러 노드가 Queue에서 각각 꺼내지므로 **while문의 반복 횟수와 이동 거리는 같지 않다.**

따라서 Queue에 현재 좌표뿐만 아니라 현재까지의 거리도 함께 저장해야 한다.

```python
(x, y, distance)
```

또한 `visited`는 Queue에서 꺼낼 때가 아니라 **Queue에 넣을 때 True로 변경해야 한다.**

이미 Queue에 들어간 노드를 방문 처리하지 않으면 다른 노드에서 같은 좌표를 다시 Queue에 넣을 수 있기 때문이다.

한 줄 정리:

**가중치 없는 최단거리 → BFS / Queue에 넣을 때 방문 처리 / 좌표와 거리를 함께 관리**
