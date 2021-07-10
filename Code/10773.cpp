#include <stdio.h>
#include <iostream>
#include <stack>
using namespace std;

int main()
{
	int k;
	stack<int> st;
	cin >> k;

	for (int i = 0; i < k; i++) {
		int n;
		cin >> n;

		if (n == 0) {
			st.pop();
		}
		else {
			st.push(n);
		}
	}

	int num = 0;
	while (!st.empty()) {
		num += st.top();
		st.pop();
	}
	cout << num;
    return 0;
}
