from tkinter import messagebox
from conexao.FabricaConexao import FabricaConexao  
from dominio.Produto import Produto  
import mysql.connector  

class ProdutoDAO:
    # Método para inserir um novo produto no banco de dados
    def create(self, p):
        con = FabricaConexao.getConection()  # Obtém a conexão com o banco de dados
        stmt = None

        try:
            stmt = con.cursor()
            # Define a query SQL para inserção de um novo produto
            sql = "INSERT INTO PRODUTOS (NOME, DESCRICAO, MARCA, CATEGORIA, PRECO, CUSTO) VALUES (%s, %s, %s, %s, %s, %s)"
            val = (p.nome, p.descricao, p.marca, p.categoria, p.preco, p.custo)
            stmt.execute(sql, val)  
            con.commit()  # Realiza o commit da transação no banco de dados
        except mysql.connector.Error as e:
            FabricaConexao.trataProblemaBancoDados(e)  # Trata erros específicos do banco de dados
        finally:
            FabricaConexao.closeConnectionWithStmt(con, stmt)  # Fecha a conexão com o banco de dados

    # Método para ler todos os produtos do banco de dados
    def read(self):
        produtos = []  # Lista para armazenar os produtos lidos

        con = FabricaConexao.getConection()  # Obtém a conexão com o banco de dados
        stmt = None
        rs = None

        try:
            stmt = con.cursor()
            stmt.execute("SELECT * FROM PRODUTOS")  # Executa a query SQL para selecionar todos os produtos
            rows = stmt.fetchall() 

            for row in rows:
                produto = Produto()  # Cria um objeto Produto para cada linha retornada
                produto.ID = row[0]
                produto.nome = row[1]
                produto.descricao = row[2]
                produto.marca = row[3]
                produto.categoria = row[4]
                produto.preco = row[5]
                produto.custo = row[6]
                produtos.append(produto)  # Adiciona o produto à lista de produtos lidos

        except mysql.connector.Error as e:
            FabricaConexao.trataProblemaBancoDados(e)  # Trata erros específicos do banco de dados
        finally:
            FabricaConexao.closeConnectionWithStmtAndRs(con, stmt, rs)  # Fecha conexão e recursos

        return produtos  # Retorna a lista de produtos lidos do banco de dados

    # Método para atualizar um produto no banco de dados
    def update(self, p):
        con = FabricaConexao.getConection()  # Obtém a conexão com o banco de dados
        stmt = None

        try:
            stmt = con.cursor()
            # Define a query SQL para atualizar os dados de um produto existente
            sql = "UPDATE PRODUTOS SET NOME = %s, DESCRICAO = %s, MARCA = %s, CATEGORIA = %s, PRECO = %s, CUSTO = %s WHERE PRODUTOS_ID = %s"
            val = (p.nome, p.descricao, p.marca, p.categoria, p.preco, p.custo, p.ID)
            stmt.execute(sql, val)  
            con.commit()  # Realiza o commit da transação no banco de dados
        except mysql.connector.Error as e:
            FabricaConexao.trataProblemaBancoDados(e)  # Trata erros específicos do banco de dados
        finally:
            FabricaConexao.closeConnectionWithStmt(con, stmt)  # Fecha a conexão com o banco de dados

    # Método para deletar um produto do banco de dados
    def delete(self, p):
        con = FabricaConexao.getConection()  # Obtém a conexão com o banco de dados
        stmt = None

        try:
            stmt = con.cursor()
            # Define a query SQL para deletar um produto do banco de dados
            sql = "DELETE FROM PRODUTOS WHERE PRODUTOS_ID = %s"
            val = (p.ID,)
            stmt.execute(sql, val)  # Executa a query SQL com o ID do produto a ser deletado
            con.commit()  # Realiza o commit da transação no banco de dados
        except mysql.connector.Error as e:
            FabricaConexao.trataProblemaBancoDados(e)  # Trata erros específicos do banco de dados
        finally:
            FabricaConexao.closeConnectionWithStmt(con, stmt)  # Fecha a conexão com o banco de dados
