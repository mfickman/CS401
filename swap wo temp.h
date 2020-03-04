//program that swaps two variables without temp variable

#include "stdafx.h"



int _tmain(int argc, _TCHAR* argv[])
{

	int x, y, check;
	check = 1;
	
	while (check <= 3){
		printf("\n\nplease enter the original values of x and y");
		printf("\nx:");
		scanf("%d",&x);
		printf("\ny:");
		scanf("%d",&y);
		printf("\nthe original vaules of x is %d and the original value of y is %d",x,y);
		
		x = x + y;
		y = x - y;
		x = x - y;
		
		printf("\nthe new value of x is %d and the new value of y is %d",x,y);
		
		
		
		check = check + 1;
	}




	return 0;
}