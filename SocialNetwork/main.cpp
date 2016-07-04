#include <iostream>
#include <cstdio>
#include <cstring>
#include <cassert>
#include <stdbool.h>
#include <stdlib.h>
#define ALPHABET_SIZE 26
using namespace std;

char friendship[20000][20000];
char names[20000][100];
int idx,pidx;
int path[20000];

typedef struct node
{
    int isEndOfWord;
    struct node* next[ALPHABET_SIZE];
}Node;


Node* createNode()
{
    int i=0;
    Node* newNode=(Node*)malloc(sizeof(Node));
    for(i=0;i<ALPHABET_SIZE;i++)
    {
        newNode->next[i]=NULL;
    }
    newNode->isEndOfWord=-1;
    return newNode;
}

Node *head;

int addName(char word[])
{
    int len=strlen(word),i;
    Node *temp=head;
    Node *newNode;
    for(i=0;i<len;i++)
    {

        if(temp->next[(word[i]-'a')]==NULL)
        {
            newNode=createNode();
            temp->next[(word[i]-'a')]=newNode;
        }
        temp=temp->next[(word[i]-'a')];

    }
    temp->isEndOfWord=idx;
    strcpy(names[idx], word);
    return idx++;
    //printf("Word '%s' is inserted!",word);
}

int getIndex(char word[])
{
    int len=strlen(word),i;
    Node* temp=head;
    for(i=0;i<len;i++)
    {
        if(temp->next[(word[i]-'a')]==NULL)
        {
            return -1;
        }
        temp=temp->next[(word[i]-'a')];
    }
    return temp->isEndOfWord;
}

/*
int addName(char name[])
{
    strcpy(names[idx], name);
    return idx++;
}

int getIndex(char name[])
{
    for(int i=0;i<idx;i++)
    {
        if(strcmp(names[i],name)==0)
            return i;
    }
    return -1;
}
*/
int addFriendship(char name1[],char name2[])
{
    int idx1,idx2;
    if((idx1=getIndex(name1))==-1)
    {
        idx1=addName(name1);
    }
    if((idx2=getIndex(name2))==-1)
    {
        idx2=addName(name2);
    }
    if(friendship[idx1][idx2]==1)
        return 0;
    friendship[idx1][idx2]=1;
    friendship[idx2][idx1]=1;
    return 1;
}

void listFriends(char name[])
{
    int idx = getIndex(name);
    if(idx==-1)
    {
        printf("\nNo user with such name available! :O\n");
        return;
    }
    printf("\n%s's Friends:\n",name);
    for(int i=0;i<20000;i++)
    {
        if(friendship[idx][i]==1)
            printf("%s\n",names[i]);
    }
}

void findMutualFriends(char name1[],char name2[])
{
    int idx1 = getIndex(name1);
    int idx2 = getIndex(name2);
    if(idx1==-1 || idx2==-1)
        return;
    printf("\nFollowing are %s & %s 's mutual-friends:\n",name1,name2);
    for(int i=0;i<20000;i++)
    {
        if(friendship[idx1][i]==1 && friendship[idx2][i]==1)
            printf("%s\n",names[i]);
    }
}

void findStats()
{
    int lcnt=20000,mcnt=0,cnt=0;
    int idxl,idxm;
    for(int i=0;i<20000;i++)
    {
        for(int j=0;j<20000;j++)
        {
            if(friendship[i][j]==1)
                cnt++;
        }
        if(cnt<=lcnt){
            lcnt=cnt;
            idxl=i;
        }
        if(cnt>=mcnt){
            mcnt=cnt;
            idxm=i;
        }
        cnt = 0;
    }
    printf("\nMost-Social: %s :D\n\nLeast-Social: %s :|\n",names[idxm],names[idxl]);
}

void findPathInternal(bool visited[],int s,int d)
{
    if(s==d)
    {
        for(int i=0;i<pidx;i++)
        {
            if(i==0)
                printf("\n%s",names[path[i]]);
            else
                printf("->%s",names[path[i]]);
        }
        printf("->%s\n",names[d]);
    }
    if(visited[s])
        return;
    visited[s]=true;
    path[pidx++]=s;
    for(int i=0;i<20000;i++)
    {
        if(friendship[s][i]==1)
            findPathInternal(visited,i,d);
    }
    visited[s]=false;
    pidx--;
}

void findPathBetween(char name1[],char name2[])
{
    int s = getIndex(name1);
    int d = getIndex(name2);
    if(s==-1 || d==-1)
        return;
    bool visited[20000];
    pidx=0;
    memset(visited,false,sizeof(visited));
    findPathInternal(visited,s,d);
}

int showMenu()
{
    printf("\nMENU\n");
    printf("1. Add Friendship\n");
    printf("2. List Friends\n");
    printf("3. Mutual Friends\n");
    printf("4. Path Between Two\n");
    printf("5. Stats\n");
    printf("6. Exit\n\n");

    printf("Enter your Choice: ");
    int ch;
    scanf("%d", &ch);
    return ch;
}
int main()
{

    int choice;
    char name1[100],name2[100];
    head = createNode();
    FILE *fp = fopen("friendship.txt", "r");
    assert(fp!=NULL);
    while(!feof(fp))
    {
        fscanf(fp, "%s %s\n", name1, name2);
        addFriendship(name1, name2);
    }
    fclose(fp);
    while(true)
    {
        choice = showMenu();
        switch(choice)
        {
        case 1:
            scanf("%s",name1);
            scanf("%s",name2);
            if(addFriendship(name1,name2))
                printf("\nFriendship-Added-Sucessfully! :p\n");
            else
                printf("\nAlready Friends! :/\n");
            break;

        case 2:
            scanf("%s",name1);
            listFriends(name1);
            break;

        case 3:
            scanf("%s",name1);
            scanf("%s",name2);
            findMutualFriends(name1,name2);
            break;

        case 4:
            scanf("%s",name1);
            scanf("%s",name2);
            findPathBetween(name1,name2);
            break;

        case 5:
            findStats();
            break;

        case 6:
            return 0; // exit(0);
        }
    }
    return 0;
}
