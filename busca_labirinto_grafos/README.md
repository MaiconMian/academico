# Busca em grafos
O objetivo deste trabalho é compreender e implementar diferentes algoritmos de busca em grafos sem pesos. Além disso, também objetiva-se praticar a modelagem de grafos como listas de adjacência ou matriz de adjacência.
Neste trabalho, cada grupo deverá implementar dois diferentes algoritmos para andar em um labirinto de tamanho 10 × 10.
Abaixo segue um exemplo de um labirinto de tamanho 5 × 5. Neste labirinto, a letra E representa a entrada, S representa a saída, um X representa uma parede e um 0 denota um caminho.

```
EXXXX
000XX
0X00S
X00XX
X0XXX
```

A saída do algoritmo deverá ser padronizada. Ela deverá indicar, passo a passo, o caminho entre a entrada e a saída. Este caminho será impresso sempre em duas colunas, separadas por vírgula, como no exemplo abaixo:

```
0,4
0,3
1,3
2,3
2,2
3,2
4,2
```

Note que a casa **(0,4)** representa o símbolo E, enquanto a casa **(4,2)** denota o símbolo S. Todos os outros símbolos representam as casas que estão no caminho entre E e S no pequeno labirinto descrito.

Deverá ser entregue o código desenvolvido na linguagem C ou C++. O código deverá ser entregue em um único arquivo .zip contendo um cabeçalho com o nome dos integrantes do grupo. Todo o código deverá, obrigatoriamente, compilar com um arquivo Makefile que deverá ser enviado em conjunto com o código.

# Compilação
Todos os [labirintos disponíveis](https://github.com/iagoac/dce529/tree/main/trabalhos/tp02/labirintos) encontram-se na pasta **instancias/**. Portanto, para compilar e rodar o projeto, basta clonar o repositório:
```bash
git clone https://github.com/0xbhsu/busca_em_grafos
cd busca_em_grafos
```
E compilar/rodar com:
```bash
make clean && make run
```
Depois, basta inserir o nome do arquivo desejado a ser testado e o tipo de algoritmo:
```bash
$ make clean && make run
rm -rf obj app
gcc -Wall -Wextra -Iinclude -c -o obj/breadth_search.o src/breadth_search.c
gcc -Wall -Wextra -Iinclude -c -o obj/create_matrix.o src/create_matrix.c
gcc -Wall -Wextra -Iinclude -c -o obj/depth_search.o src/depth_search.c
gcc -Wall -Wextra -Iinclude -c -o obj/main.o src/main.c
gcc -Wall -Wextra -Iinclude -c -o obj/queue.o src/queue.c
gcc -Wall -Wextra -Iinclude -o app/labirinto_grafos obj/breadth_search.o obj/create_matrix.o obj/depth_search.o obj/main.o obj/queue.o
cd app; ./labirinto_grafos
Digite o nome do arquivo do labirinto (com .txt): labirinto3.txt
Selecione o algoritmo a ser rodado:
1 - Busca em profundidade
2 - Busca em largura
> 1
0,0
0,1
0,2
0,3
0,4
0,5
0,6
0,7
0,8
1,8
2,8
2,7
2,6
2,5
2,4
2,3
3,3
4,3
5,3
6,3
6,2
6,1
7,1
7,0
```
