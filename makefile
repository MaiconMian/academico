simples: lexico.l sintatico.y utils.c;\
		flex -t lexico.l > lexico.c;\
		bison -v -d sintatico.y -o sintatico.c;\
		gcc sintatico.c -o simples;

limpa: ;\
	rm -f lexico.c sintatico.c sintatico.output sintatico.h simples\