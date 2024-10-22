#ifndef _H_PASSEIO
#define _H_PASSEIO

//instruct que guarda a posição da tabela
typedef struct {
    int x; 
    int y;
} xy;

//struct que guarda o elemento da pilha
typedef struct pilhas {
    //casa atual
    xy casa;
    //acessados
    int acessados;
} PILHA;

bool testemovimento (int novalinha, int novacoluna, int n, int m, bool ** tabuleiro); //bool que testa o movimento
void andar (int topo, int linhateste, int colunateste, PILHA * pilha); //void que anda com o tabuleiro
bool **ler_instancia(int instancia_num);
void computa_passeios(bool **tabuleiro);

#endif // _H_PASSEIO