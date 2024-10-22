#include <stdio.h>
#include <stdlib.h>

typedef struct no_
{
    int chave;
    int valor;
    struct no_ * prox;
} no;

void buscar (no * ptlista, no ** ant, no ** pont, int chave)
{
    (*ant) = ptlista;
    (*pont) = NULL;
    no * tmp = (*ptlista).prox;
    while (tmp != NULL){
        if ((*tmp).chave < chave){
            (*ant) = tmp;
            tmp = (*tmp).prox;
        } else {
            if ((*tmp).chave == chave){
                (*pont) = tmp;
            }
            tmp = NULL;
        }
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
    buscar(ptlista, &ant, &pont, chave);
    if (pont == NULL){
        return NULL;
    } else {
        (*ant).prox = (*pont).prox;
        return pont;
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
    (*lista).prox = NULL;
    return lista;
}

int main()
{
    no * ptlista = inicia();
    int continua = 1;
    
    while (continua != 0){
        printf("Digite o que deseja fazer:\n1.Inserir\n2.Remover\n3.Mostrar\n0.Sair\n");
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
                mostrar(ptlista);
            }
        }
    }

    return 0 ;
}
