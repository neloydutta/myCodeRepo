#include <iostream>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#define ALPHABET_SIZE 26
using namespace std;

typedef struct node
{
    bool isEndOfWord;
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
    newNode->isEndOfWord=false;
    return newNode;
}

Node *head;

void insertWord(char word[])
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
    temp->isEndOfWord=true;
    //printf("Word '%s' is inserted!",word);
}

bool hasWord(char word[])
{
    int len=strlen(word),i;
    Node* temp=head;
    for(i=0;i<len;i++)
    {
        if(temp->next[(word[i]-'a')]==NULL)
        {
            return false;
        }
        temp=temp->next[(word[i]-'a')];
    }
    return temp->isEndOfWord;
}


char table[10][10];
bool visted[10][10];
char word[100];
int r,c;
int wrdi=0;
int cnt=0;
void generateAndPrintWords(int,int);

int main()
{
    char word[30];
    head=createNode();

    FILE *fp=fopen("dictionary.txt","r");
    if(fp==NULL)
    {
        printf("File Not Found!");
    }
    while(!feof(fp))
    {
        fscanf(fp,"%s",word);
        insertWord(word);
    }
    fclose(fp);
    printf("Number of Rows: ");
    scanf("%d",&r);
    c=r;
    for(int i=0;i<r;i++)
    {
        for(int j=0;j<c;j++){
            scanf("%c ",&table[i][j]);
            visted[i][j]=false;
        }
    }
    generateAndPrintWords(0,0);
    printf("\n%d",cnt);
    return 0;
}

void generateAndPrintWords(int k,int l)
{
    for(int i=k-1;i<=k+1;i++)
        for(int j=l-1;j<=l+1;j++){
            if((i>=0&&i<r)&&(j>=0&&j<c)){
                if(!visted[i][j]){
                    word[wrdi++]=table[i][j];
                    word[wrdi]='\0';
                    visted[i][j]=true;
                }
                else
                    continue;
            if(strlen(word)>=3 && hasWord(word))
                printf("%s\n",word);
            if(strlen(word)>=3)
                cnt++;
            generateAndPrintWords(i,j);
            visted[i][j]=false;
            wrdi--;
            word[wrdi]='\0';
            }
        }

}
