import tkinter as tk
from tkinter import messagebox, ttk
from controlador.Loja import Loja
from dominio.Produto import Produto

class PagEdicaoControlador:
    def __init__(self, parent, loja, main_app):
        # Inicializa o controlador da página de edição de produtos
        self.parent = parent  # Frame pai onde os widgets serão colocados
        self.loja = loja  # Instância da loja para acessar métodos de edição de produtos
        self.main_app = main_app  # Instância da aplicação principal para controle de navegação
        self.produto_id = None  # Armazena o ID do produto que está sendo editado (inicialmente nenhum)

        # Labels e Entradas para os dados do produto
        self.lblNome = tk.Label(parent, text="Nome:")
        self.lblNome.pack()
        self.entryNome = tk.Entry(parent)
        self.entryNome.pack()

        self.lblDescricao = tk.Label(parent, text="Descrição:")
        self.lblDescricao.pack()
        self.entryDescricao = tk.Entry(parent)
        self.entryDescricao.pack()

        self.lblMarca = tk.Label(parent, text="Marca:")
        self.lblMarca.pack()
        self.entryMarca = tk.Entry(parent)
        self.entryMarca.pack()

        self.lblCategoria = tk.Label(parent, text="Categoria:")
        self.lblCategoria.pack()
        self.entryCategoria = tk.Entry(parent)
        self.entryCategoria.pack()

        self.lblPreco = tk.Label(parent, text="Preço:")
        self.lblPreco.pack()
        self.entryPreco = tk.Entry(parent)
        self.entryPreco.pack()

        self.lblCusto = tk.Label(parent, text="Custo:")
        self.lblCusto.pack()
        self.entryCusto = tk.Entry(parent)
        self.entryCusto.pack()

        # Frame para botões de ação
        self.frame_botoes = tk.Frame(parent)
        self.frame_botoes.pack(pady=10)

        # Botões Salvar e Cancelar
        self.btnSalvar = tk.Button(self.frame_botoes, text="Salvar", command=self.salvarProduto)
        self.btnSalvar.pack(side=tk.LEFT, padx=10)

        self.btnVoltar = tk.Button(self.frame_botoes, text="Cancelar", command=self.voltarPaginaInicial)
        self.btnVoltar.pack(side=tk.LEFT, padx=10)

    def clean(self):
        # Limpa os campos de entrada e o ID do produto
        self.entryNome.delete(0, tk.END)
        self.entryDescricao.delete(0, tk.END)
        self.entryMarca.delete(0, tk.END)
        self.entryCategoria.delete(0, tk.END)
        self.entryPreco.delete(0, tk.END)
        self.entryCusto.delete(0, tk.END)
        self.produto_id = None  # Limpa o ID do produto sendo editado

    def setProdutoEditar(self, produto):
        # Preenche os campos de entrada com os dados do produto a ser editado
        self.clean()  # Limpa os campos antes de preencher
        self.produto_id = produto.getID()  # Armazena o ID do produto sendo editado

        self.entryNome.insert(0, produto.getNome())
        self.entryDescricao.insert(0, produto.getDescricao())
        self.entryMarca.insert(0, produto.getMarca())
        self.entryCategoria.insert(0, produto.getCategoria())
        self.entryPreco.insert(0, str(produto.getPreco()))
        self.entryCusto.insert(0, str(produto.getCusto()))

    def salvarProduto(self):
        # Obtém os dados dos campos de entrada e tenta salvar o produto
        nome = self.entryNome.get()
        descricao = self.entryDescricao.get()
        marca = self.entryMarca.get()
        categoria = self.entryCategoria.get()

        preco_str = self.entryPreco.get()
        custo_str = self.entryCusto.get()

        # Verifica se os valores de preço e custo são numéricos
        try:
            if preco_str:
                preco = float(preco_str)
            else:
                preco = 0.0

            if custo_str:
                custo = float(custo_str)
            else:
                custo = 0.0
        except ValueError:
            messagebox.showerror("Erro", "Digite apenas números válidos para Preço e Custo.")
            return

        # Verifica se o nome e o preço foram preenchidos
        if nome and preco_str:
            if self.produto_id is not None:
                # Modifica o produto existente com os novos dados
                self.loja.modificarProduto(self.produto_id, nome, descricao, categoria, marca, preco, custo)
                messagebox.showinfo("Sucesso", "Produto modificado com sucesso!")
            else:
                messagebox.showerror("Erro", "Nenhum produto selecionado para edição!")
            self.clean()  # Limpa os campos após salvar
            self.main_app.mostrarPagina("pagInicial")  # Retorna para a página inicial
        else:
            messagebox.showerror("Erro", "Preencha o nome e o preço!")

    def voltarPaginaInicial(self):
        # Retorna para a página inicial sem salvar alterações
        self.main_app.mostrarPagina("pagInicial")
