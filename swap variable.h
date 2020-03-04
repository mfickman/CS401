//program that swaps two variables

#include "stdafx.h"



int _tmain(int argc, _TCHAR* argv[])
{
	int a, b, temp;
	int check = 1;
	while (check <= 3){
		printf("\nplease enter the two numbers for a and b:\n");
		printf("a:");
		scanf("%d", &a);
		printf("\nb:");
		scanf("%d", &b);
		
		temp = a;
		a = b;
		b = temp;
		
		printf("\nthe value of a is now %d and the value of b is now %d",a,b);
		check = check + 1;
	}
	return 0;
}
