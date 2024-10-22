import tkinter as tk
from tkinter import messagebox, ttk
from controlador.Loja import Loja
from dominio.Produto import Produto
from tela.PagInicialControlador import PagInicialControlador
from tela.PagCadastroControlador import PagCadastroControlador
from tela.PagEdicaoControlador import PagEdicaoControlador

class MainApp:
    def __init__(self, root):
        # Inicialização da janela principal
        self.root = root
        self.root.title("Controle de Produtos")  # Define o título da janela
        self.root.geometry("1000x540")  # Define as dimensões da janela
        self.root.resizable(False, False)  # Impede que a janela seja redimensionada pelo usuário

        # Inicialização da instância da Loja
        self.loja = Loja.getLoja()

        # Criação e configuração de rótulos na janela principal
        self.descricaoLabel = tk.Label(root, text="Blue Velvet Music Store", font=("Helvetica", 16))
        self.descricaoLabel.pack(pady=10)

        self.descricaoLabel = tk.Label(root, text="Controle de Produtos", font=("Helvetica", 16))
        self.descricaoLabel.pack(pady=10)

        # Criação de frames para diferentes páginas da aplicação
        self.pagInicialFrame = tk.Frame(root)
        self.pagCadastroFrame = tk.Frame(root)
        self.pagEdicaoFrame = tk.Frame(root)

        # Inicialização dos controladores para cada página
        self.pagInicialControlador = PagInicialControlador(self.pagInicialFrame, self.loja, self)
        self.pagCadastroControlador = PagCadastroControlador(self.pagCadastroFrame, self.loja, self)
        self.pagEdicaoControlador = PagEdicaoControlador(self.pagEdicaoFrame, self.loja, self)

        # Empacotamento inicial dos frames das páginas na janela principal
        self.pagInicialFrame.pack()
        self.pagCadastroFrame.pack()
        self.pagEdicaoFrame.pack()

        # Mostra a página inicial ao iniciar a aplicação
        self.mostrarPagina("pagInicial")

    def mostrarPagina(self, pagina, produto=None):
        # Método para mostrar a página desejada na aplicação
        if pagina == "pagInicial":
            self.root.title("Controle de Produtos")  # Define o título da janela para a página inicial
            self.pagInicialControlador.atualiza()  # Atualiza o conteúdo da página inicial
            self.pagInicialFrame.pack()  # Mostra o frame da página inicial
            self.pagCadastroFrame.pack_forget()  # Esconde o frame de cadastro, se estiver visível
            self.pagEdicaoFrame.pack_forget()  # Esconde o frame de edição, se estiver visível
        elif pagina == "pagCadastro":
            self.root.title("Cadastro de Produtos")  # Define o título da janela para a página de cadastro
            self.pagCadastroControlador.clean()  # Limpa os campos de entrada na página de cadastro
            self.pagCadastroFrame.pack()  # Mostra o frame de cadastro
            self.pagInicialFrame.pack_forget()  # Esconde o frame inicial, se estiver visível
            self.pagEdicaoFrame.pack_forget()  # Esconde o frame de edição, se estiver visível
        elif pagina == "pagEdicao":
            self.root.title("Edição de Produtos")  # Define o título da janela para a página de edição
            self.pagEdicaoControlador.setProdutoEditar(produto)  # Define o produto a ser editado na página de edição
            self.pagEdicaoFrame.pack()  # Mostra o frame de edição
            self.pagInicialFrame.pack_forget()  # Esconde o frame inicial, se estiver visível
            self.pagCadastroFrame.pack_forget()  # Esconde o frame de cadastro, se estiver visível

if __name__ == "__main__":
    root = tk.Tk()  # Cria a instância da janela principal
    app = MainApp(root)  # Inicializa a aplicação passando a janela principal como argumento
    root.mainloop()  # Inicia o loop principal da aplicação tkinter
