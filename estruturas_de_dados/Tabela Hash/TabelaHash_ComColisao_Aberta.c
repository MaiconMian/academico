#include <stdlib.h>
#include <stdio.h>
#include <string.h>

//Maicon Almeida Mian

typedef struct no{
  int chave;
  char nome[40];
}no_;

typedef struct arvore{
  int atual, max;
  no_ ** vetor;
}hash;

// FUNÇÕES HASH

int CongruenciaLinear (int chave, int tamanhomax)
{
  if (chave < 0)
    chave *= -1;
  return chave % tamanhomax;
}

int CongruenciaMul (int chave, int tamanhomax)
{
  if (chave < 0)
    chave *= -1;
  double A = 0.1298347;
  double var = chave * A;
  var = (int)var - var;
  return (int)(var * tamanhomax);
}

int Dobra (int chave, int tamanhomax)
{
  if (chave < 0)
    chave *= -1;
  if (chave < tamanhomax)
    return chave;
  int inicio = 0;
  int fim = tamanhomax;
  int numero[10];
  int div = chave;
  do{
    int resto = div % 10;
    numero[inicio] = resto;
    if (numero[inicio] != 0)
        fim = inicio;
    inicio++;
    div = div / 10;
  } while (inicio < 10);

  inicio = 0;
  while (chave > tamanhomax)
  {
    while (fim > inicio){
        numero[inicio] = (numero[inicio] + numero [fim]) % 10;
        numero[fim] = 0;
        fim--;
        inicio++;
    }
    inicio = chave = 0;
    int indice = 1;
    while (inicio <= fim){
        chave = chave + numero[inicio] * 10;
        indice = indice * 10;
        inicio++;
    }
    inicio = 0;
  }

  return chave;

}

// TRATENTO DE COLISÕES

int SondagemLinear (int posicao, int i , int tamanhomax){
  return (posicao + i) % tamanhomax;
}

int SondagemQuadratica (int posicao, int i , int tamanhomax){
  posicao = posicao + 2 * i + 5 * i * i;
  return posicao % tamanhomax;
}

int DuploHash(int H1, int i, int tamanhomax){
  int H2 = SondagemQuadratica(H1, i, tamanhomax);
  return (H1 * i + H2) % tamanhomax;
}

// FUNÇÕES PADRÃO

int Adiciona (hash * tabela, no_ * novo){
  if (tabela == NULL || (*tabela).max == (*tabela).atual)
  {
    return 0;
  }
  int posicao = Dobra((*novo).chave, (*tabela).max);
  for (int i = 0; i < (*tabela).max; i++)
  {
    posicao = DuploHash(posicao, i, (*tabela).max);
    if ((*tabela).vetor[posicao] == NULL){
        (*tabela).vetor[posicao] = novo;
        (*tabela).atual++;
        return 1;
    }
  }
  return 0;
}

char * Busca (hash * tabela, int chave){
  if (tabela == NULL){
    return NULL;
  }
  int posicao = Dobra(chave, (*tabela).max);
  for (int i = 0; i < (*tabela).max; i++)
  {
    posicao = DuploHash(posicao, i, (*tabela).max);
    if ((*tabela).vetor[posicao] != NULL && (*(*tabela).vetor[posicao]).chave == chave){
        return &(*(*tabela).vetor[posicao]).nome[0];
    }
  }
  return NULL;
}

char * Remove (hash * tabela, int chave){
  if (tabela == NULL || (*tabela).atual == 0)
  {
    return NULL;
  }
  int posicao = Dobra(chave, (*tabela).max);
  for (int i = 0; (*tabela).vetor[posicao] != NULL; i++){
    posicao = DuploHash(posicao, i, (*tabela).max);
    if ((*(*tabela).vetor[posicao]).chave == chave){
      char * nome_copia = strdup((*(*tabela).vetor[posicao]).nome); 
      free((*tabela).vetor[posicao]); 
      (*tabela).vetor[posicao] = NULL; 
      return nome_copia;
    }
  }
  return NULL;
}

hash* cria (int tamanho)
{
  hash * tabela = (hash *) malloc(sizeof(hash));
  if (tabela != NULL){
    (*tabela).vetor = malloc(sizeof(no_ *) * tamanho);
    (*tabela).max = tamanho;
    (*tabela).atual = 0;
    for (int i = 0; i < tamanho; i++){
    (*tabela).vetor[i] = NULL;
    }
  }
  return tabela;
}

int main(){
  int tamanho, continua = 1, valor;
  char nome[40];
  printf("Digite o tamanho da sua tabela Hash:");
  scanf("%d", &tamanho);
  hash * tabela = cria(tamanho);

  while (continua != 0){
    printf("O que você deseja fazer: 1. Adicionar | 2. Buscar | 3.Remover | 0. Sair>");
    scanf("%d", &continua);
    if (continua == 1){
      printf("Digite o nome para adicionar:");
      scanf("%s", &nome);
      printf("Digite o valor para chave:");
      scanf("%d", &valor);
      no_ * novo = malloc(sizeof(no_));
      (*novo).chave = valor;
      strcpy((*novo).nome, nome);
      Adiciona(tabela, novo);
    } else if (continua == 2){
      printf("Digite o valor para chave que deseja buscar:");
      scanf("%d", &valor);
      char * retorno = Busca(tabela, valor);
      if (retorno != NULL){
        printf("O nome retornado nessa chave eh: %s\n", retorno);
      } else {
        printf("Achei nao\n");
      }
    } else if (continua == 3){
      printf("Digite o valor para chave que deseja remover:");
      scanf("%d", &valor);
      char * retorno = Remove(tabela, valor);
      if (retorno != NULL){
        printf("O valor removido retornado nessa chave eh: %s\n", retorno);
        free(retorno);
      } else {
        printf("Achei nao\n");
      }
    }
  }
}