//program that finds greatest of ten numbers

#include "stdafx.h"



int _tmain(int argc, _TCHAR* argv[])
{
	int x[10];
	int check = 1;
	int i, greatest, numcheck, len;
	while (check <= 3){
		numcheck = 1;
		while (numcheck == 1){
		numcheck = 0;
		fflush(stdin);
	    printf("\nplease enter a positive number for the amount of numbers you wish to check:");
		scanf("%d", &len);
			if (len <= 0){
				numcheck = 1;
				printf("you need to enter a positive number!!!!");
			}
		}
		 printf("\nplease enter %d integers:\n",len);
		 for(i=0;i<len;i++){
			 scanf("%d",&x[i]); 
		 }
		 
		 greatest = x[0];
		 
		 for (i=0;i<len;i++){
			 if(x[i]>greatest){
				 greatest = x[i];
			 }
		 }
		printf("\nthe greatest value is %d",greatest);
		check = check + 1;
	}


	return 0;
}