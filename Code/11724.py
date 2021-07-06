N, M = map(int, input().split())

vertex = []
for i in range(N+1):
    vertex.append([])

for i in range(M):
    v1, v2 = map(int, input().split())
    vertex[v1].append(v2)
    vertex[v2].append(v1)

visited = []
count = 0

for i in range(1, N+1):
    if i in visited:
        continue

    count += 1
    queue = [i]

    while len(queue) > 0:
        v = queue.pop(0)
        visited.append(v)

        for e in vertex[v]:
            if e in visited:
                continue

            queue.append(e)
            visited.append(e)

print(count)
