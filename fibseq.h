//program that find if a number is prime;

#include "stdafx.h"



int _tmain(int argc, _TCHAR* argv[])
{
	int check = 1;
	int len, i;
	int first = 0;
	int second = 1;
	int next;
	fib[100];
	fib[0] = first;
	fib[1] = second;
	
	
	
	while(check <= 3){
		poscheck = 1;
		while (poscheck == 1){
			poscheck = 0;
			fflush(stdin);
			printf("\nplease enter a positve number for how many entries of the fibbanoci sequence you want");
			scanf("%d", &len);
			if (len <= -1){
				fflush(stdin);
				poscheck = 1;
				printf("\nplease enter a positive number");
			}
		}
		if(len == 0){
			printf("\nthe value of the series is zero");
		}
		else if(len == 1){
			printf("\n the sequence is zero and one");
		}
		else{
			for (i = 2; i<=len, i++){
				fib[i] = fib[i-1] + fib[i -2];
			}
			printf("\nthe squence up to %d is as follows:\n",len);
			for(i = 0; i<= len, i ++){
				printf(" %d ",fib[i]);
			}
		}
			
			
		check = check + 1;
	}
		
		
		return 0;
}
		

	
	
