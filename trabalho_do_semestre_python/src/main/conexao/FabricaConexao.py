import mysql.connector
from tkinter import messagebox

class FabricaConexao:
    # Atributos estáticos para configurar a conexão com o banco de dados MySQL
    DRIVE = "mysql.connector"
    HOST = "localhost"
    PORT = 3306
    DATABASE = "produtos_bd"
    USER = "Adm"
    PASS = "Secret_123"

    @staticmethod
    def getConection():
        # Retorna uma conexão com o banco de dados MySQL usando os atributos de configuração
        try:
            return mysql.connector.connect(
                host=FabricaConexao.HOST,
                port=FabricaConexao.PORT,
                user=FabricaConexao.USER,
                password=FabricaConexao.PASS,
                database=FabricaConexao.DATABASE
            )
        except mysql.connector.Error as ex:
            raise RuntimeError(f"Erro na conexão: {ex}")

    @staticmethod
    def closeConnection(con):
        # Fecha a conexão com o banco de dados, se estiver aberta
        try:
            if con.is_connected():
                con.close()
        except mysql.connector.Error as ex:
            FabricaConexao.trataProblemaBancoDados(ex)

    @staticmethod
    def closeConnectionWithStmt(con, stmt):
        # Fecha a conexão com o statement do banco de dados
        FabricaConexao.closeConnection(con)
        try:
            if stmt:
                stmt.close()
        except mysql.connector.Error as ex:
            FabricaConexao.trataProblemaBancoDados(ex)

    @staticmethod
    def closeConnectionWithStmtAndRs(con, stmt, rs):
        # Fecha a conexão com o statement e o result set do banco de dados
        FabricaConexao.closeConnectionWithStmt(con, stmt)
        try:
            if rs:
                rs.close()
        except mysql.connector.Error as ex:
            FabricaConexao.trataProblemaBancoDados(ex)

    @staticmethod
    def trataProblemaBancoDados(ex):
        # Exibe uma mensagem de erro em uma caixa de diálogo, tratando problemas com o banco de dados
        messagebox.showerror("Erro", f"Erro: {ex.msg}\nPor favor, verifique sua conexão com o banco de dados MySQL.")
