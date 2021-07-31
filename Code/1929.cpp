#include <iostream>

using namespace std;

int main()
{
	int m, n;
	cin >> m >> n;

	vector<bool> isP(n + 1, true);

	isP[0] = false;
	isP[1] = false;

	for (int i = 2; i * i <= n; i++) {
		if (!isP[i]) continue;
		for (int j = i * i; j <= n; j += i) {
			isP[j] = false;
		}
	}

	for (int i = m; i <= n; i++) {
		if (isP[i]) {
			cout << i << "\n";
		}
	}

}
