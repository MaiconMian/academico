#include <stdio.h>
#include <stdlib.h>

typedef struct {
    int valor;
    int chave;
}pilha;

//-1 pilha cheia
int inserir(int * topo, pilha novo, pilha PILHA[], int tamanhomax){
    if ((*topo)+1 < tamanhomax){
        (*topo)++;
        PILHA[(*topo)] = novo;
        return (*topo);
    }
    return -1;
}

//nul pilha vazia
pilha * remover (int * topo, pilha PILHA[], int tamanhomax){
   pilha * retorno = NULL;
   if ((*topo) > -1){
        retorno = &PILHA[(*topo)];
        (*topo)--;
   } 
   return retorno;
}

void mostrar (int topo, pilha PILHA[]){
    if (topo >= 0){
        for (int i = 0; i <= topo; i++){
            printf("A chave e %d e o valor e %d\n", PILHA[i].chave, PILHA[i].valor);
        }
    } else {
        printf("Lista vazia!");
    }
}

int main(){
    int tamanhomax, topo = -1, continua;
    printf("Digite o tamanho da pilha:");
    scanf("%d", &tamanhomax);
    pilha PILHA[tamanhomax];
    printf("Digite o que deseja fazer: \n1.empilhar\n2.desempilhar\n3.mostrar\n0.sair\n");
    scanf("%d", &continua);
    while (continua != 0){
        if (continua == 1){
            pilha novo;
            printf("Digite o valor que deseja adicionar:");
            scanf("%d", &novo.valor);
            printf("Digite a chave que deseja adicionar:");
            scanf("%d", &novo.chave);
            int resultado = inserir(&topo, novo, PILHA, tamanhomax);
            if (resultado == -1){
                printf("Pilha cheia!");
            } else {
                printf("Adicionado com sucesso na posicao %d", resultado);
            }
        } else if (continua == 2){
            pilha * resultado = remover(&topo, PILHA, tamanhomax);
            if (resultado != NULL){
                printf("item com chave %d e valor %d removido com sucesso!", (*resultado).chave, (*resultado).valor);
            } else {
                printf("Pilha Vazia!");
            }
        } else if (continua == 3){
            mostrar(topo, PILHA);
        }
        printf("Digite o que deseja fazer: \n1.empilhar\n2.desempilhar\n3.mostrar\n0.sair\n");
        scanf("%d", &continua);
    }
    return 0;
}