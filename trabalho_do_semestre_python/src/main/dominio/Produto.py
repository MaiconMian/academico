class Produto:
    def __init__(self, nome="", descricao="", categoria="", marca="", preco=0.0, custo=0.0):
        # Construtor inicializa atributos do produto
        self.nome = nome
        self.descricao = descricao
        self.categoria = categoria
        self.marca = marca
        self.preco = preco
        self.custo = custo
        self.ID = None  # ID inicialmente indefinido

    def setID(self, ID):
        # Define o ID do produto
        self.ID = ID

    def getID(self):
        # Retorna o ID do produto
        return self.ID

    # Métodos para acessar e modificar atributos do produto
    def getNome(self):
        return self.nome

    def setNome(self, nome):
        self.nome = nome

    def getDescricao(self):
        return self.descricao

    def setDescricao(self, descricao):
        self.descricao = descricao

    def getMarca(self):
        return self.marca

    def setMarca(self, marca):
        self.marca = marca

    def getCategoria(self):
        return self.categoria

    def setCategoria(self, categoria):
        self.categoria = categoria

    def getPreco(self):
        return self.preco

    def setPreco(self, preco):
        self.preco = preco

    def getCusto(self):
        return self.custo

    def setCusto(self, custo):
        self.custo = custo

    def __lt__(self, other):
        # Comparação de produtos baseada no nome (para ordenação)
        return self.nome < other.nome

    def __eq__(self, other):
        # Verifica se dois produtos são iguais pelo nome
        return self.nome == other.nome
