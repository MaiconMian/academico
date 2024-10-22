# Metodos de Escalonamento
Metodos de Escalonamento de Processos implementado em C como trabalho da classe de AED's || com filas e listas encadeadas.

## Métodos Implementados

**First Come, First Served (FCFS)** - O primeiro processo a ser criado é executado, e assim por diante, não importando seu tamanho.  
**Shortest Job First (SJF)** - Após a execução do processo, os restantes são ordenados de modo que sempre o menor será executado primeiro.  
**Roud-Robin (RR)** - A execução conta com um Quantum (tempo de execução) e cada processo é executado dentro desse tempo, voltando para lista caso não tenha sido suficiente.  

## Implementação

A implementação foi feita por meio de listas e filas encadeadas em C, de modo que a execução do processo fosse vista a cada "iteração". A cada iteração, o código tem uma chance de 30% de gerar um  novo processo indo de 1 a 20 de tamanho. Inicialmente, ele não começa com nenhum processo em execução, podendo ter o número de iterações escolhido pelo usuário, com opção de rodar infinitamente.

## Instalação

Execute o seguinte comando criar o executável (que conta com um arquivo .h):
```bash
gcc TrabalhoEscalonamentoProcessos.c -o EscalonamentoProcessos
```
## Contato

Se você tiver dúvidas, entre em contato em maicon.mian@sou.unifal-mg.edu.br

