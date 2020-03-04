// evenodd.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"



int _tmain(int argc, _TCHAR* argv[])
{

	int num, check;
	
	check = 1;

	while (check <= 3){

		//prompt user
		printf("\nPlease enter the number you wish to check:\n");
		scanf("%d",&num);
	
		if(num%2 == 0){
			printf("%d is an even number",num);
		}
		else{
			printf("%d is an odd number", num);		
		}
		check = check+1;
	}
	return 0;
}

