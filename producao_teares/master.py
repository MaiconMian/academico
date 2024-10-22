"""
Progama para contabilização da porcentagem de produção (RPM) de cada tear
Por: Maicon Almeida Mian
"""

#importe da biblioteca tkinter
import tkinter as tk
from tkinter import ttk
from tkinter import messagebox

#guarda os resultados em um arquivo de texto
def resultado(grupo, rpm, hj, entrada_obs, dia, mes, ano, entrada_turno):

    #converte os valores coletados
    tempo = float(entrada_obs.get())
    turno_arquivo = entrada_turno.get()
    datatotal = f"{dia.get()}-{mes.get()}-{ano.get()}"

    #descobre os valores de teares por grupo
    grupos = []
    for j in range(3):
        if grupo[j].get():
            grupos.append(int(grupo[j].get()))
        else:
            grupos.append(0)

    #rpm de cada tear +  hrs justificadas
    resultado = []
    horas_justificadas = []
    for i in range(24):
        if rpm[i].get() :
            resultado.append(float(rpm[i].get()))
        else:
            resultado.append(0.0)
        if hj[i].get() :
            horas_justificadas.append(float(hj[i].get()))
        else:
            horas_justificadas.append(0.0)

    #abrir aquivo
    with open("ConfigTear.txt", "r") as arquivo:
        conteudo = arquivo.read()
    palavras = conteudo.split()
    padrao = [float(palavra) for palavra in palavras]
    arquivo.close()

    #multiplica o valor de um turno pelo número de turnos
    for i in range(len(padrao)):
        padrao[i] = padrao[i] * (tempo - (horas_justificadas[i]/8))

    #pocentagem de rotação de cada tear
    porcentagem = []
    soma_grupo = [0,0,0]

    #faz as contas das porcentagens de cada tear do grupo 1
    aux = int(grupos[0])
    i = j = 0
    if aux != 0:
        while i < aux:
            if padrao[i] == 0:
                porcentagem.append(100.0)
                soma_grupo[0] = soma_grupo[0] + 100
            else :
                porcentagem.append((100*resultado[i])/padrao[i])
                soma_grupo[0] = soma_grupo[0] + ((100*resultado[i])/padrao[i])
            i = i + 1
            j = j + 1
        soma_grupo[0] = float(soma_grupo[0]/(j))

    #faz as contas das porcentagens de cada tear do grupo 2
    aux = int(grupos[1])
    j = 0
    if aux != 0:
        while j < aux:

            if padrao[i] == 0:
                porcentagem.append(100.0)
                soma_grupo[1] = soma_grupo[1] + 100
            else :
                porcentagem.append((100*resultado[i])/padrao[i])
                soma_grupo[1] = soma_grupo[1]+ ((100*resultado[i])/padrao[i])
            
            i = i + 1
            j = j + 1

        soma_grupo[1] = float(soma_grupo[1]/j)

    #faz as contas das porcentagens de cada tear do grupo 3
    aux = int(grupos[2])
    j = 0
    if aux != 0 :
        while j < aux:

            if padrao[i] == 0:
                porcentagem.append(100.0)
                soma_grupo[2] = soma_grupo[2] + 100
            else :
                porcentagem.append((100*resultado[i])/padrao[i])
                soma_grupo[2] = soma_grupo[2] + 100
            
            i = i + 1
            j = j + 1

        soma_grupo[2] = float(soma_grupo[2] / j)

    #cria o título do arquivo
    titulo = f"{datatotal} {turno_arquivo}.txt"

    #joga as informações no arquivo de texto
    with open(titulo, "w") as results :
        back = f"\n"
        cabecalho = f"No período de {tempo} dias, com início em {datatotal}:"
        results.write(cabecalho)
        results.write(back)
        for w in range(len(porcentagem)):
            text = (f"Tear {w + 1} teve %2.f%% de aproveitamento com {horas_justificadas[w]} horas justificadas\n" % (porcentagem[w]))
            results.write(text)
        results.write(back)
        total_fabrica = 0
        cont = 0
        for h in range(len(soma_grupo)):
            if soma_grupo[h] != 0 :
                text = (f"O grupo {h + 1} teve %2.f%% de aproveitamento \n" % (soma_grupo[h]))
                results.write(text)
                cont = cont + 1
        results.write(back)
        total_fabrica = float(sum(porcentagem)/len(porcentagem))
        fabrica = (f"O total de aproveitamento da fabrica inteira foi %2.f%%\n" % (total_fabrica))
        results.write(fabrica)
    results.close()

#salva os valores padrões dos RPM dos teares em um arquivo       
def salvar(tear):
    guarda = []

    for j in range(len(tear)):
        guarda.append(tear[j].get())

    espaco = " "

    with open("ConfigTear.txt", "w") as arquivo:
        for j in range(24) :
            arquivo.write(guarda[j])
            arquivo.write(espaco)
    arquivo.close()

#tela de configuração do valor máximo de RPM para cada tear        
def Config() :
    #criação da tela de opções
    outra_tela = tk.Toplevel(janela) 
    outra_tela.title("Configuração Tear")
    outra_tela.resizable(False, False)

    #cabeçalho
    titulo = tk.Label(outra_tela, text="Guarde o valor esperado de RPM de cada tear por turno")
    titulo.grid(column=0, row=0, columnspan=4, sticky="ew")

    vp = []
    #abre o arquivo anterior com os valores padroes
    with open("ConfigTear.txt", "r") as arquivo:
        vp = arquivo.read()
    arquivo.close()
    palavras = vp.split()
    vp = [int(palavra) for palavra in palavras]
    #preenche o vetor com 24 valores, com 0 aonde nao havia nada no arquivo
    while len(vp) < 24:
        vp.append(0)

    tear = []
    #cria as caixas de dialogo para RPM padrao do tear 1 ao tear 12
    for i in range(12):
        label_text = f"Tear {i+1}:"
        tear_label = tk.Label(outra_tela, text=label_text)
        tear_label.grid(column=0, row=i+1)
        tear_padrao = ttk.Entry(outra_tela)
        tear_padrao.grid(column=1, row=i+1)
        text = f"{vp[i]}"
        tear_padrao.insert(0, text)
        tear.append(tear_padrao)

    #cria as caixas de dialogo para RPM padrao do tear 13 ao tear 24
    for i in range(12):
        label_text = f"Tear {i+13}:"
        tear_label = tk.Label(outra_tela, text=label_text)
        tear_label.grid(column=3, row=i+1)
        tear_padrao = ttk.Entry(outra_tela)
        text = f"{vp[i+12]}"
        tear_padrao.insert(0, text)
        tear_padrao.grid(column=4, row=i+1)
        tear.append(tear_padrao)

    while len(tear) < 24:
        tear.append(0)

    #botao para salvar os valores padroes doas teares
    botao = ttk.Button(outra_tela, text="Salvar", command=lambda: salvar(tear))
    botao.grid (column = 4, row=13)

#verifica se todos os campos obrigatorios estão preenchidos
def salvar_resultado(grupo, rpm, hj, entrada_obs, dia, mes, ano, entrada_turno):

    entrada = str(entrada_obs.get())

    rolagem = []
    rolagem_string = []

    numeros = False

    for i in range(24):
        if rpm[i].get():

            rolagem_string.append(str(rpm[i].get()))

            if rolagem_string[i].isdigit():
                rolagem.append(int(rpm[i].get()))
                numeros = False
            else:
                numeros = True
                break

        else:
            rolagem.append(int(0.0))
            rolagem_string.append(str(0.0))

    test = []
    #abre o arquivo com os valores padroes do tear
    with open("ConfigTear.txt", "r") as arquivo:
        test = arquivo.read()
    arquivo.close()

    palavras = test.split()
    test = [int(palavra) for palavra in palavras]
    while len(test) < 24:
        test.append(0)

    confere = False
    
    #confere se os valores de RPM digitados tem um valor padrao
    for y in range(len(rolagem)):
        if rolagem[y] > 0 and test[y] <= 0:
            confere = True
            break

    #mensages de erros
    if confere:
        messagebox.showerror("Sem RPM base", "algum tear digitado não consta o RPM base, entre em opções e o adicione")
    else:
        if not entrada_obs.get().strip():
            messagebox.showerror("Campo Obrigatório", "O campo tempo de observação deve estar preenchido.")
        else:
            if not grupo[0].get().strip():
                messagebox.showerror("Campo Obrigatório", "Pelo menos um dos campos de grupo deve estar preenchido.")
            else: 
                if not dia.get().strip():
                    messagebox.showerror("Campo Obrigatório", "O dia da data deve estar preenchido.")
                else:
                    if not mes.get().strip():
                        messagebox.showerror("Campo Obrigatório", "O mês da data deve estar preenchido.")
                    else:
                        if not ano.get().strip():
                            messagebox.showerror("Campo Obrigatório", "O ano da data deve estar preenchido.")
                        else:
                            if not entrada_turno.get().strip():
                                messagebox.showerror("Campo Obrigatório", "O turno deve estar preenchido.")
                            #se nenhum erro foi encontrado, ele chama a função resultado
                            else:
                                if numeros == True:
                                    messagebox.showerror("Valor Inválido", "O valor digitado em algum campo é invalido.")
                                else:
                                    resultado(grupo, rpm, hj, entrada_obs, dia, mes, ano, entrada_turno)

#criação da janela principal
janela = tk.Tk()
janela.title("Controle de Produção - M&C Textil")
janela.geometry("800x570")
janela.resizable(False, False)

#turno
turno = tk.Label(janela, text="Turno:")
turno.place(x=310, y=1, width=60, height=20)
entrada_turno = ttk.Entry(janela, width=10)
entrada_turno.place(x=370, y=1, width=60, height=20)

grupo = []
w = 0
j = 1

linha = 32
coluna = 100

#caixa de dialogo para criação dos grupos
for i in range(3):
        label_text = f"Grupo {i+1}:"
        grupo_jan = tk.Label(janela, text=label_text)
        grupo_jan.place(x=coluna, y=linha, width=60, height=20)

        grupo_ent = ttk.Entry(janela, width=10)
        grupo_ent.place(x=coluna + 80, y=linha, width=60, height=20)
        grupo.append(grupo_ent)

        coluna = coluna + 240
        j = j + 2
        w = w + 2

rpm = []
hj = []
coluna = 130
linha = 70
#Caixas de dialogo teares do 1 ao 12
for i in range(12):
        label_text = f"Tear {i+1}:"
        rpm_text = tk.Label(janela, text=label_text)
        rpm_text.place(x=coluna, y=linha, width=60, height=20)
        valor_rpm = ttk.Entry(janela)
        valor_rpm.place(x=coluna+80, y=linha, width=80, height=20)
        rpm.append(valor_rpm)
        horas_just = ttk.Entry(janela, width=5)
        horas_just.place(x=coluna+170, y=linha, width=30, height=20)
        hj.append(horas_just)
        linha = linha + 35

coluna = 400
linha = 70
#caixa de dialogo teares do 13 ao 24
for i in range(12):
        label_text = f"Tear {i+13}:"
        rpm_text = tk.Label(janela, text=label_text)
        rpm_text.place(x=coluna, y=linha, width=60, height=20)
        valor_rpm = ttk.Entry(janela)
        valor_rpm.place(x=coluna+80, y=linha, width=80, height=20)
        rpm.append(valor_rpm)
        horas_just = ttk.Entry(janela, width=5)
        horas_just.place(x=coluna+170, y=linha, width=30, height=20)
        hj.append(horas_just)
        linha = linha + 35

#tempo observação em dias
tk.Label(janela, text="Tempo de Observação:").place(x=2, y=linha+35, width=150, height=20)
entrada_obs = ttk.Entry(janela)
entrada_obs.place(x=160, y=linha+35, width=50, height=20)

#data
data = tk.Label(janela, text="Data :").place(x=250, y=linha+35, width=50, height=20)

#caixa de dialogo das datas
dia = ttk.Entry(janela, width=3)
dia.place(x=300, y=linha+35, width=30, height=20)
tk.Label(janela, text="/").place(x=331, y=linha+35, width=10, height=20)
mes = ttk.Entry(janela, width=3)
mes.place(x=342, y=linha+35, width=30, height=20)
tk.Label(janela, text="/").place(x=373, y=linha+35, width=10, height=20)
ano = ttk.Entry(janela, width=4)
ano.place(x=384, y=linha+35, width=40, height=20)

#salvar
botao = ttk.Button(janela, text="Salvar", command=lambda:salvar_resultado(grupo, rpm, hj, entrada_obs, dia, mes, ano, entrada_turno))
botao.place(x=500, y=linha+35, width=100, height=30)

#opções
botao_abrir = ttk.Button(janela, text="Opções", command=Config)
botao_abrir.place(x=601, y=linha+35, width=100, height=30)

janela.mainloop()
