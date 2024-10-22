#include <stdio.h>
#include <stdlib.h>

typedef struct no_
{
    int chave;
    int valor;
    int freq; //adicionei a frequencia
    struct no_ * prox;
} no;

//o busca agora não é mais ordenado
void buscar (no * ptlista, no ** ant, no ** pont, int chave)
{
    (*ant) = ptlista;
    while (ptlista != NULL && (*ptlista).chave != chave)
    {
      (*ant) = ptlista;
      ptlista = (*ptlista).prox;
    }
    (*pont) = ptlista;
}

//Calcula a frequencia e ordena conforme necessário
void Freq (no * ptlista, no * encontrado, no * anterior){
  (*encontrado).freq++;
  no * ant = ptlista;
  no * aux = (*ptlista).prox;
  while (aux != NULL && (*aux).freq >= (*encontrado).freq){
    ant = aux;
    aux = (*aux).prox;
  }
  if (ant != encontrado){
    (*anterior).prox = (*encontrado).prox;
    (*encontrado).prox = (*ant).prox;
    (*ant).prox = encontrado;
  }
}

//criei outro busca pra ele nao contabilizar as buscas pra dicionar
void buscarFreq (no * ptlista, int chave)
{ 
  no * ant, * pont; 
  buscar(ptlista, &ant, &pont, chave);
  if ((*pont).chave == chave){
    Freq(ptlista, pont, ant);
  } 
}

no * inserir (no * ptlista, no * novono)
{
    no * ant, * pont;
    buscar(ptlista, &ant, &pont, (*novono).chave);
    if (pont == NULL){
        (*novono).prox = (*ant).prox;
        (*ant).prox = novono;
        return NULL;
    } else {
        return pont;
    }
}

no * remover (no * ptlista, int chave)
{   
    no * ant, * pont;
    no * remover = NULL;
    buscar(ptlista, &ant, &pont, chave);
    if(pont != NULL){
        (*ant).prox = (*pont).prox;
        return pont;
    } else {
        return NULL;
    }
}

void mostrar (no * ptlista)
{
    no * pont = (*ptlista).prox;
    while (pont != NULL){
        printf("%d\t", (*pont).valor);
        pont = (*pont).prox;
    }
    printf("\n");
}

no * inicia ()
{
    no * lista = malloc(sizeof(no));
    (*lista).chave = 0;
    (*lista).valor = 0;
    (*lista).freq = 0;
    (*lista).prox = NULL;
    return lista;
}

int main()
{
    no * ptlista = inicia();
    int continua = 1;

    while (continua != 0){
        printf("Digite o que deseja fazer:\n1.Inserir\n2.Remover\n3.Buscar\n0.Sair\n");
        scanf("%d", &continua);
        if (continua == 1){
            no * novono = malloc(sizeof(no));
            printf("Digite o valor que deseja:");
            scanf("%d", &(*novono).valor);
            printf("Digite a chave que deseja:");
            scanf("%d", &(*novono).chave);
            no * resultado = inserir(ptlista, novono);
            if (resultado == NULL){
                printf("Valor inserido com sucesso!\n");
            } else {
                printf("A chave ja existe sendo %d e o valor %d\n", (*resultado).chave, (*resultado).valor);
            }
        } else if (continua == 2){
            printf("Digite a chave que deseja remover:");
            scanf("%d", &continua);
            if ((*ptlista).prox == NULL){
                printf("Lista Vazia!\n");
            } else {
                no * resultado = remover(ptlista, continua);
                if (resultado == NULL){
                    printf("Não existe elemento com essa chave!\n");
                } else {
                    printf("O no com chave %d e valor %d foi removido com sucesso!\n",  (*resultado).chave, (*resultado).valor);
                }
                free(resultado);
            }
            continua = 2;
        } else if (continua == 3){
            if ((*ptlista).prox == NULL){
                printf("Lista vazia\n");
            } else {
              printf("Digite a chave que deseja buscar:");
              scanf("%d", &continua);
              buscarFreq(ptlista, continua);
              mostrar(ptlista);
            }
          continua = 3;
        }
    }

    return 0 ;
}