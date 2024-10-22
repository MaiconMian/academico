#include<stdio.h>
#include<stdlib.h>

typedef struct no_{
    int valor;
    int chave;
}no;

int busca (no L[], int tamanhoatual, int chave){
    L[tamanhoatual].chave = chave;
    int i = 0;
    while (L[i].chave < chave){ 
        i++;
    }
    if (i != tamanhoatual){
        return i;
    }
    return -1;
}

int insere (no L[], int tamanhoatual, int tamanhomax, no novo)
{
    if (tamanhoatual < tamanhomax){
        int resultado = busca(L, tamanhoatual, novo.chave); 
        if (resultado > -1){ 
            no tmp = L[resultado];
            for (int i = resultado+1; i < tamanhoatual; i++){
                no tmp2 = L[i];
                L[i] = tmp;
            }
            L[resultado] = novo;
            tamanhoatual++;
            return tamanhoatual;
        }
        return 0;
    }
    return -1;
}

no* removerr(no L[], int * tamanhoatual, int chave)
{
    no * removido = NULL;
    if ((*tamanhoatual) > 0){
        int indice = busca(L, (*tamanhoatual), chave);
        if (indice >= 0){
            removido = malloc(sizeof(no));
            (*removido) = L[indice];

            for (int i = indice; i < (*tamanhoatual)-1; i ++){
                L[i] = L[i+1];
            }

            (*tamanhoatual)--;

        }
    } 
    return removido;
}

void mostrar (no L[], int tamanhoatual)
{
    if (tamanhoatual == 0){
        printf("Lista vazia!");
    } else {
        for (int i = 0; i < tamanhoatual; i++){
        printf("%d\t", L[i]);
    }
    }
}


int main (){

    int tamanhoatual = 0, tamanhomax, continua = 1, valor, chave;
    printf("Digite o tamanho da sua lista: ");
    scanf("%d", &tamanhomax);
    no L[tamanhomax + 1];

    while (continua != 0){
        printf("Digite o que deseja fazer:\n0.Sair\n1.Inserir\n2.Remover\n3.Mostrar\n");
        scanf("%d", &continua);
        if (continua == 1){
            printf("Digite o valor que quer adicionar:");
            scanf("%d", &valor);
            printf("Digite a chave que quer adicionar:");
            scanf("%d", &chave);
            no novo;
            novo.valor = valor;
            novo.chave = chave;
            int resultado = insere(L, tamanhoatual, tamanhomax, novo);
            if (resultado == 0){
                printf("O elemento já existe na lista\n");
            } else if (resultado == -1){
                printf("A lista está cheia!\n");
            } else if (resultado > 0){
                tamanhoatual = resultado;
                printf("Adicionado com sucesso!\n");
            }
        } else if (continua == 2){
            printf("Digite a chave que quer remover:");
            scanf("%d", &chave);
            no * resultado = removerr(L, &tamanhoatual, chave);
            if (resultado == NULL){
                printf("Ou o elemento nao existe ou a fila esta vazia\n");
            } else {
                printf("O elemento removido tem chave %d e valor %d\n", (*resultado).chave, (*resultado).valor);
                printf("A lista tem tamanho %d agora\n", tamanhoatual);
                free(resultado);
            }
        } else if (continua == 3){
            mostrar(L, tamanhoatual);
        }
    }

    return 0;


}