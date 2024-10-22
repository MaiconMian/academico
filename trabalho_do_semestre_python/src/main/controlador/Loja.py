from conexao.ProdutoDAO import ProdutoDAO
from dominio.Produto import Produto
import collections

class Loja:
    # lista de produtos que a loja possui
    produtos = []
    # nome da loja
    nomeLoja = None
    # Design Pattern: Singleton
    loja = None

    def __init__(self):
        pass

    def __init__(self, nome):
        self.nomeLoja = nome

    @staticmethod
    def getLoja():
        # Retorna a instância única da loja (Singleton)
        if Loja.loja is None:
            Loja.loja = Loja("Blue Velvet Music Store")
        return Loja.loja

    def lerProdutos(self):
        # Lê todos os produtos da base de dados utilizando o ProdutoDAO
        pdao = ProdutoDAO()
        self.produtos = []
        self.produtos = pdao.read()

    def getProdutos(self):
        # Retorna todos os produtos da loja ordenados por nome
        self.lerProdutos()
        self.produtos.sort(key=lambda x: x.getNome())
        return self.produtos

    def criarProduto(self, nome, descricao, categoria, marca, preco, custo):
        # Cria um novo produto e o adiciona à loja e ao banco de dados
        p = Produto(nome, descricao, categoria, marca, preco, custo)
        pdao = ProdutoDAO()
        pdao.create(p)

    def modificarProduto(self, ID, nome, descricao, categoria, marca, preco, custo):
        # Modifica um produto existente na loja e no banco de dados pelo ID
        pdao = ProdutoDAO()
        for p in self.produtos:
            if p.getID() == ID:
                p.setNome(nome)
                p.setDescricao(descricao)
                p.setCategoria(categoria)
                p.setMarca(marca)
                p.setPreco(preco)
                p.setCusto(custo)
                pdao.update(p)
                break

    def removerProduto(self, produto):
        # Remove um produto da loja e do banco de dados
        self.produtos = [p for p in self.produtos if p.getID() != produto.getID()]
        pdao = ProdutoDAO()
        pdao.delete(produto)

    def pesquisarProdutos(self, nome_produto):
        # Pesquisa produtos na loja pelo nome, ignorando maiúsculas/minúsculas
        produtos_encontrados = []
        for p in self.produtos:
            if p.getNome().lower() == nome_produto.lower():
                produtos_encontrados.append(p)
        return produtos_encontrados

    def getProdutoById(self, produto_id):
        # Retorna um produto da loja pelo ID
        for p in self.produtos:
            if p.getID() == produto_id:
                return p
        return None
