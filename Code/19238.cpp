#include <iostream>
#include <vector>
#include <queue>
#include <tuple>

using namespace std;

int map[21][21];
int rider[21][21];
vector <pair<int, int>> start;
vector <pair<int, int>> dest;

int dr[4] = { 0, 1, 0, -1 };
int dc[4] = { 1, 0, -1, 0 };

int main()
{
	int n, m, fuel;
	int x, y;

	cin >> n >> m >> fuel;	
	
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			cin >> map[i][j];
		}
	}
	
	cin >> x >> y;
	x--; y--;

	for (int i = 0; i < n; i++) {
		fill(rider[i], rider[i] + n, -1);
	}

	for (int i = 0; i < m; i++) {
		int sx, sy, ex, ey;
		cin >> sx >> sy >> ex >> ey;

		pair<int, int> s = make_pair(sx - 1, sy - 1);
		start.push_back(s);
		rider[sx - 1][sy - 1] = i;

		pair<int, int> e = make_pair(ex - 1, ey - 1);
		dest.push_back(e);
	}


	while (m > 0 && fuel > 0) {
		int dist[21][21];
		for (int i = 0; i < n; i++) {
			fill(dist[i], dist[i] + n, -1);
		}

		dist[x][y] = 0;
		queue <pair<int, int>> q;
		q.push(make_pair(x, y));		
		
		int cost1 = 2 * n + 1;
		vector<tuple<int, int, int>> rlist;

		if (rider[x][y] >= 0) { // 출발지(이전 도착지가)에 사람 있음
			q.pop();
			rlist.push_back(make_tuple(x, y, rider[x][y]));
			cost1 = 0;
		}

		while (!q.empty()) {
			pair<int, int> cur = q.front();
			q.pop();

			int r = cur.first;
			int c = cur.second;
			
			if (dist[r][c] > cost1) continue;

			for (int i = 0; i < 4; i++) {
				int newR = r + dr[i];
				int newC = c + dc[i];

				if (newR < 0 || newR >= n) continue;
				if (newC < 0 || newC >= n) continue;
				if (map[newR][newC] == 1) continue;
				if (dist[newR][newC] > -1) continue;

				dist[newR][newC] = dist[r][c] + 1;
				q.push(make_pair(newR, newC));

				if (rider[newR][newC] >= 0) {
					if (cost1 > dist[newR][newC]) {
						cost1 = dist[newR][newC];
						rlist.push_back(make_tuple(newR, newC, rider[newR][newC]));
					}
					else if (cost1 == dist[newR][newC]) {
						rlist.push_back(make_tuple(newR, newC, rider[newR][newC]));
					}
				}
			}
		}
    
		if (rlist.size() == 0) break;

		x = get<0>(rlist[0]);
		y = get<1>(rlist[0]);
		int idx = get<2>(rlist[0]);

		for (int i = 0; i < rlist.size(); i++) {
			if (get<0>(rlist[i]) < x) {
				x = get<0>(rlist[i]);
				y = get<1>(rlist[i]);
				idx = get<2>(rlist[i]);
			}
			else if (get<0>(rlist[i]) == x && get<1>(rlist[i]) < y) {
				x = get<0>(rlist[i]);
				y = get<1>(rlist[i]);
				idx = get<2>(rlist[i]);
			}
		}
		rider[x][y] = -1;

		// to destination
		for (int i = 0; i < n; i++) {
			fill(dist[i], dist[i] + n, -1);
		}

		dist[x][y] = 0;
		queue <pair<int, int>> nxtq;
		nxtq.push(make_pair(x, y));

		int cost2 = 0;
		int destx = dest[idx].first;
		int desty = dest[idx].second;
		bool drive = true;

		while (!nxtq.empty() && drive) {
			pair<int, int> cur = nxtq.front();
			nxtq.pop();

			int r = cur.first;
			int c = cur.second;

			for (int i = 0; i < 4; i++) {
				int newR = r + dr[i];
				int newC = c + dc[i];

				if (newR < 0 || newR >= n) continue;
				if (newC < 0 || newC >= n) continue;
				if (map[newR][newC] == 1) continue;
				if (dist[newR][newC] > -1) continue;

				dist[newR][newC] = dist[r][c] + 1;
				nxtq.push(make_pair(newR, newC));
				if (newR == destx && newC == desty) {
					cost2 = dist[newR][newC];
					drive = false;
					break;
				}
			}
		}

		if (drive) break;

		fuel = fuel - cost1 - cost2;
		if (fuel < 0) break;

		// success
		fuel += cost2 * 2;
		m--;
		x = destx;
		y = desty;
	}

	if (m > 0) cout << -1;
	else cout << fuel;

    return 0;
}

