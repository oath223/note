#include<windows.h> 

#include <stdio.h>
#include <stdlib.h>
/**
 *@隐藏执行
 */
void main() {
	int num;
	num=10;
	scanf("%d",&num);
	char str[50];
	//sprintf(str,"for /l %%i in (1,1,%d ) do @echo china",num);
	sprintf(str,"for /l %%i in (1,1,%d ) do start notepad",num);
	//start 异步执行
	//@ 是隐藏方式执行，只有结果，没有过程
	system(str);//执行指令
	system("pause");//暂停
	/*
	为什么一定要初始化
	在老版本的编译器，并不需要初始化，
	但是新版本的，都加上了安全检查。
	*/
}
