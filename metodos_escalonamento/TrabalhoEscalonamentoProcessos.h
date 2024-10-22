/*+-------------------------------------------------------------+
 | UNIFAL – Universidade Federal de Alfenas.                    |
 | BACHARELADO EM CIENCIA DA COMPUTACAO.                        |
 | Trabalho..: Metodos de Escalonamento                         |
 | Disciplina: Algoritmos e Estrutura de Dados II – Pratica     |
 | Professor.: Fellipe Rey                                      |
 | Aluno(s)..: Maicon Almeida Mian 2023.1.08.013                |
 |             Caio Henrique Costa de Matos 2023.1.08.002       |
 |             Nycole Paulino dos Santos 2023.1.08.044          |
 | Data......: 13/12/2023                                       |
 +-------------------------------------------------------------+*/

#ifndef TrabalhoEscalonamentoProcessos
#define TrabalhoEscalonamentoProcessos

//nó que guarda o processo
typedef struct no{
    int Processo; //o número do processo para identificação
    int tamanho; //o tamanho dele
    struct no *proximo; //o próximo dele
}No;

//Struct da Fila
typedef struct fila{
    No * primeiro, * ultimo; //ponteiros para o primeiro e o ultimo processo da fila
    int Qant_Processos; //a quantidade de processos já criados 
}Fila;

typedef struct lista{
    No * nos; //lista de processos 
    No * executando; //o processo atualmente executado (usado no método SJF)
    int Qant_Processos, Quantum; //a quantidade de processos e o quantum (usado no RR)
}Lista;

int Cria_Tamanho_Processo ();
int Probabilidade ();
No * Inicia_Novo_No (int tamanho);

void Inicia_Fila (Fila * fila);
void Remove_Fila (Fila * fila);
No * Insere_Fila (Fila * fila, int tamanho);
No * FCFS (Fila * fila);

void Inicia_Lista(Lista * lista);
No * Insere_Lista_Ordenada (Lista * lista, int tamanho);
No * Remove_Lista (Lista * lista);
No * SJF (Lista * lista);

void Transposicao_Lista (Lista * lista, No * novo);
No * Buscar_Ultimo_Lista (Lista * lista);
No * Insere_Lista (Lista * lista, int tamanho);
No * RR (Lista * lista);

#endif