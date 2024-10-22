#include<stdio.h>
#include<stdlib.h>
// implemente as 3 estruturas autoajustáveis 
typedef struct no_
{
    int chave;
    int valor;
    int freq;
} no;

void MudaFrente (no L[], int indice)
{
    no aux = L[indice];
    for (int i = indice; i > 0; i--){
        L[i] = L[i-1];
    }
    L[0] = aux;
}

void Transposicao (no L[], int indice)
{
    if (indice > 0){
      no aux = L[indice];
      L[indice] = L[indice - 1];
      L[indice - 1] = aux;
    }
    
}

void ContadorFreq  (no L[], int indice)
{
    L[indice].freq = L[indice].freq + 1;
    while (indice != 0 && L[indice-1].freq < L[indice].freq){
      no aux = L[indice];
      L[indice] = L[indice - 1];
      L[indice - 1] = aux;
      indice--;
    }
}


int buscarFreq (no L[], int n, int x){
    L[n].chave = x;

    int i = 0;
    while(L[i].chave != x){
        i = i+1;
    }
    printf("entrei aqui\n");
    if(i != n){
      ContadorFreq(L, i);
        return 1;
    } else {
        return -1;
    }

}

int buscar (no L[], int n, int x){
    L[n].chave = x;

    int i = 0;
    while(L[i].chave != x){
        i = i+1;
    }

    if(i != n){
        return i;
    }
    return -1;
}

int inserir (no L[], int n, int m, no d){
    if(n < m){
        if(buscar(L, n, d.chave) == -1){
            L[n].chave = d.chave;
            L[n].valor = d.valor;
            return n + 1; //retorna 
        }
        return 0; // o item já existe na listta
    }
    return -1; // lista cheia 
}

no* remover(int x, no L[], int *n){
    no * retorno = NULL;
    if(n != 0){
        int indice = buscar(L, *n, x);
        if(indice >= 0){
            retorno = malloc(sizeof(no));
            (*retorno).chave = L[indice].chave;
            (*retorno).valor = L[indice].valor;

            for(int i = indice; i < (*n)-1; i++){
                L[i].chave = L[i+1].chave;
                L[i].valor = L[i+1].valor;
            }
            *n = (*n)-1;
        }    
    }

    return retorno;
}

void imprimir(no L[], int n){
    if(!n){
        printf("<lista vazia!>");
    }
    for(int i = 0; i < n; i++){
        printf("%d: %d freq %d\n", L[i].chave, L[i].valor, L[i].freq);
        printf("=========\n");
    }
}


void ler_menu(int * resposta){
    printf("\n-----------------------\n");
    printf("escolha uma das opcoes:\n");
    printf("0 - sair\n");
    printf("1 - inserir\n");
    printf("2 - remover\n");
    printf("3 - imprimir\n");
    scanf("%d", resposta);
    printf("-----------------------\n\n");
}

void ler_no(no * novo_no){
    printf("informe a chave: ");
    scanf("%d", &(*novo_no).chave);
    printf("informe o valor: ");
    scanf("%d", &(*novo_no).valor);
    printf("\n");

}

void main(){
    int m;
    int n = 0;
    int resposta = 1;

    printf("informe o tamanho desejado p/ a lista: ");
    scanf("%d", &m);
    no L[m + 1];

    while(resposta != 0){
        ler_menu(&resposta);
        if(resposta == 0){
            //sair
            return ;
        } if(resposta == 1){
            //inserir
            no novo_no;
            ler_no(&novo_no);
            novo_no.freq = 0;
            int valor_retornado = inserir (L, n, m, novo_no);
            if(valor_retornado == -1){
                printf("lista cheia\n");
            }else if(valor_retornado == 0){
                printf("elemento ja existente\n");
            }else{
                n = valor_retornado;
                printf("elemento inserido\ntamanho da lista: %d\n",n);
            }
        } else if(resposta == 2){
            //remover
            int chave;
            printf("informe a chave a remover: ");
            scanf("%d", &chave);

            no * no_removido = remover(chave, L, &n);
            if(no_removido != NULL){
                printf("conteudo do no removido:\n");
                printf("%d: %d\n",(*no_removido).chave, (*no_removido).valor);
                printf("tamanho da lista: %d\n", n);
                free(no_removido);
            }else{
                printf("elemento inexistente\n");
            }
        }else if(resposta == 3){
            printf("Digite o item que você deseja buscar:");
            int valor;
            scanf("%d", &valor);
            int resultado = buscarFreq(L, n, valor);
            printf("aaaaa\n");
            if (resultado == -1){
                printf("Não foi encontrado!\n");
            } else {
                imprimir(L, n);
            }
        }else {
            printf("Opcao invalida\n");
        }
    }
}