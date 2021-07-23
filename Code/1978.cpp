#include <stdio.h>
#include <iostream>
using namespace std;

int main()
{   
    int n;
    int arr[100];
    
    cin >> n;
    
    for(int i = 0; i < n; i++){
        cin >> arr[i];
    }
    
    int count = 0;
    for(int i = 0; i < n; i++){
        bool isP = true;
        
        if(arr[i] == 1) isP = false;
        
        for(int num = 2; num*num <= arr[i]; num++){
            if(arr[i] % num == 0){
                isP = false;
                break;
            }
        }
        if(isP) count++;
    }
    
    cout << count;
    
}
