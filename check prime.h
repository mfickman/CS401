//program that find if a number is prime;

#include "stdafx.h"



int _tmain(int argc, _TCHAR* argv[])
{
	int check, num,i, x, numcheck;
	check = 1;

	
	
	while (check <= 3){
		numcheck = 1;
		while (numcheck ==1){
			numcheck = 0;
			printf("\nplease enter a number you want to check:");
			scanf("%d",&num);
			if (num <= -1){
				printf("\nplease enter a positive number!!!!");
				numcheck = 1;
			}
		}
		
		i = 1;
		x = 0;
		while (i <= (num)){
			if (num%i == 0){
				x = x+1;
			}
			i = i+1;
		}
		
		if(i == 2){
			printf("\n%d is a prime number",num);
		}
		else{
			printf("\n%d is NOT a prime number", num);
		}
		
		check = check +1;
		fflush(stdin);
	}
	
	
	
	
	return 0;
}