import tkinter as tk
from tkinter import messagebox, ttk
from controlador.Loja import Loja
from dominio.Produto import Produto

class PagInicialControlador:
    def __init__(self, parent, loja, main_app):
        self.parent = parent
        self.loja = loja
        self.main_app = main_app
        self.current_page = 0
        self.items_per_page = 10
        self.sort_column = None
        self.sort_reverse = False

        # Frame para pesquisa e botões
        self.frame_top = tk.Frame(self.parent)
        self.frame_top.pack(fill=tk.X, pady=(0, 20))

        # Área de pesquisa
        self.txtPesquisa = tk.Entry(self.frame_top, font=("Arial", 19), fg='grey')
        self.txtPesquisa.insert(0, "Pesquisar Produto...")
        self.txtPesquisa.bind("<FocusIn>", self.clear_placeholder)
        self.txtPesquisa.bind("<FocusOut>", self.add_placeholder)
        self.txtPesquisa.pack(side=tk.LEFT, padx=27)

        self.btnPesquisar = tk.Button(self.frame_top, text="Pesquisar", command=self.pesquisarProduto)
        self.btnPesquisar.pack(side=tk.LEFT, padx=10)

        # Frame para botões de ação
        self.frame_buttons = tk.Frame(self.frame_top)
        self.frame_buttons.pack(side=tk.RIGHT, padx=27)

        self.btnNovoProduto = tk.Button(self.frame_buttons, text="Novo Produto", command=self.novoProduto)
        self.btnNovoProduto.pack(side=tk.LEFT, padx=5)

        self.btnEditarProduto = tk.Button(self.frame_buttons, text="Editar Produto", command=self.editarProduto)
        self.btnEditarProduto.pack(side=tk.LEFT, padx=5)

        self.btnRemoverProduto = tk.Button(self.frame_buttons, text="Remover Produto", command=self.removerProduto)
        self.btnRemoverProduto.pack(side=tk.LEFT, padx=5)

        # Frame para Treeview e Scrollbar
        self.frame_tree = tk.Frame(self.parent)
        self.frame_tree.pack(pady=10, fill=tk.BOTH, expand=True)

        # Treeview com Scrollbar
        self.tree = ttk.Treeview(self.frame_tree, columns=('ID', 'Nome', 'Descricao', 'Marca', 'Categoria', 'Preco', 'Custo'), show='headings')
        for col in self.tree['columns']:
            self.tree.heading(col, text=col, command=lambda c=col: self.sort_by_column(c))

        # Definindo os textos dos cabeçalhos das colunas
        self.tree.heading('#2', text='Nome')
        self.tree.heading('#3', text='Descrição')
        self.tree.heading('#4', text='Marca')
        self.tree.heading('#5', text='Categoria')
        self.tree.heading('#6', text='Preço')
        self.tree.heading('#7', text='Custo')

        # Definindo a largura das colunas
        self.tree.column('#1', width=0, stretch=tk.NO)  
        self.tree.column('#2', width=100)
        self.tree.column('#3', width=200)
        self.tree.column('#4', width=100)
        self.tree.column('#5', width=100)
        self.tree.column('#6', width=70)
        self.tree.column('#7', width=70)

        # Scrollbars
        self.scrollbar_y = ttk.Scrollbar(self.frame_tree, orient=tk.VERTICAL, command=self.tree.yview)
        self.scrollbar_y.pack(side=tk.RIGHT, fill=tk.Y)
        self.scrollbar_x = ttk.Scrollbar(self.frame_tree, orient=tk.HORIZONTAL, command=self.tree.xview)
        self.scrollbar_x.pack(side=tk.BOTTOM, fill=tk.X)

        self.tree.configure(yscroll=self.scrollbar_y.set, xscroll=self.scrollbar_x.set)
        self.tree.pack(fill=tk.BOTH, expand=True)

        # Botões de paginação
        self.frame_pagination = tk.Frame(self.parent)
        self.frame_pagination.pack(pady=10)

        self.btnAnterior = tk.Button(self.frame_pagination, text="Anterior", command=self.paginaAnterior)
        self.btnAnterior.pack(side=tk.LEFT, padx=10)

        self.btnProximo = tk.Button(self.frame_pagination, text="Próximo", command=self.proximaPagina)
        self.btnProximo.pack(side=tk.LEFT, padx=10)

        self.atualiza()

    def atualiza(self):
        produtos = self.loja.getProdutos()
        if self.sort_column:
            produtos = sorted(produtos, key=lambda x: getattr(x, f'get{self.sort_column}')(), reverse=self.sort_reverse)
        self.num_pages = (len(produtos) + self.items_per_page - 1) // self.items_per_page

        start_index = self.current_page * self.items_per_page
        end_index = start_index + self.items_per_page
        produtos_pagina = produtos[start_index:end_index]

        for item in self.tree.get_children():
            self.tree.delete(item)

        for produto in produtos_pagina:
            self.tree.insert('', 'end', values=(produto.getID(), produto.getNome(), produto.getDescricao(), produto.getMarca(), produto.getCategoria(), produto.getPreco(), produto.getCusto()))

        self.update_buttons()

    def update_buttons(self):
        if self.current_page == 0:
            self.btnAnterior.config(state=tk.DISABLED)
        else:
            self.btnAnterior.config(state=tk.NORMAL)

        if self.current_page >= self.num_pages - 1:
            self.btnProximo.config(state=tk.DISABLED)
        else:
            self.btnProximo.config(state=tk.NORMAL)

    def paginaAnterior(self):
        if self.current_page > 0:
            self.current_page -= 1
            self.atualiza()

    def proximaPagina(self):
        if self.current_page < self.num_pages - 1:
            self.current_page += 1
            self.atualiza()

    def pesquisarProduto(self):
        texto = self.txtPesquisa.get().strip()

        if not texto:
            self.current_page = 0
            self.atualiza()
        else:
            produtos_encontrados = self.loja.pesquisarProdutos(texto)
            for item in self.tree.get_children():
                self.tree.delete(item)
            if produtos_encontrados:
                for produto in produtos_encontrados:
                    self.tree.insert('', 'end', values=(produto.getID(), produto.getNome(), produto.getDescricao(), produto.getMarca(), produto.getCategoria(), produto.getPreco(), produto.getCusto()))
                self.btnAnterior.config(state=tk.DISABLED)
                self.btnProximo.config(state=tk.DISABLED)
            else:
                # se não tem produtos com esse nome, a tabela é limpa
                self.tree.delete(*self.tree.get_children())

    def novoProduto(self):
        self.main_app.mostrarPagina("pagCadastro")

    def editarProduto(self):
        selected_items = self.tree.selection()
        if selected_items:
            item = selected_items[0]
            produto_id = self.tree.item(item, 'values')[0]  # Obtém o ID do produto
            produto = self.loja.getProdutoById(int(produto_id))
            if produto:
                self.main_app.mostrarPagina("pagEdicao", produto)
            else:
                messagebox.showerror("Erro", f"Produto não encontrado com ID: {produto_id}")
        else:
            messagebox.showwarning("Seleção Inválida", "Por favor, selecione um produto para editar.")

    def removerProduto(self):
        selected_items = self.tree.selection()
        if selected_items:
            item = selected_items[0]
            produto_id = int(self.tree.item(item, 'values')[0])  # Obtém o ID do produto
            produto = self.loja.getProdutoById(produto_id)
            if produto:
                resposta = messagebox.askyesno("Confirmação", "Você tem certeza que deseja remover o produto?")
                if resposta:
                    self.loja.removerProduto(produto)
                    messagebox.showinfo("Sucesso", "Produto removido com sucesso!")
                    self.atualiza()
            else:
                messagebox.showerror("Erro", f"Produto não encontrado com ID: {produto_id}")
        else:
            messagebox.showwarning("Seleção Inválida", "Por favor, selecione um produto para remover.")

    def clear_placeholder(self, event):
        if self.txtPesquisa.get() == "Pesquisar Produto...":
            self.txtPesquisa.delete(0, tk.END)
            self.txtPesquisa.config(fg='black')

    def add_placeholder(self, event):
        if not self.txtPesquisa.get():
            self.txtPesquisa.insert(0, "Pesquisar Produto...")
            self.txtPesquisa.config(fg='grey')

    def sort_by_column(self, col):
        if self.sort_column == col:
            self.sort_reverse = not self.sort_reverse
        else:
            self.sort_reverse = False
        self.sort_column = col
        self.atualiza()

