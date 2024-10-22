# Diretórios principais
SRC_DIR := src
OBJ_DIR := obj
INCLUDE_DIR := include
APP_DIR := app
GRAPHS_DIR := grafos

# Nome do binário
TARGET := closeness
TARGET_PY := plot.py

# Compilador e flags do compilador
CCPY := python3
CC := gcc
CFLAGS := -Wall -Wextra -I$(INCLUDE_DIR)

# Arquivos fonte
SRC_FILES := $(wildcard $(SRC_DIR)/*.c)

# Lista de objetos
OBJ_FILES := $(patsubst $(SRC_DIR)/%.c, $(OBJ_DIR)/%.o, $(SRC_FILES))

# Regra para gerar o binário
$(APP_DIR)/$(TARGET): $(OBJ_FILES)
	pip3 install -r requirements.txt
	$(CC) $(CFLAGS) -o $@ $^

# Regra para gerar os arquivos objeto
$(OBJ_DIR)/%.o: $(SRC_DIR)/%.c
	$(CC) $(CFLAGS) -c -o $@ $<

# Regra para limpar objetos e o binário
clean:
	rm -rf $(OBJ_DIR) $(APP_DIR) $(GRAPHS_DIR)

# Rodar o programa principal
run: $(APP_DIR)/$(TARGET)
	./$(APP_DIR)/$(TARGET) && $(CCPY) $(TARGET_PY) 

# Garante que os diretórios de objetos e aplicativo existam
$(shell mkdir -p $(OBJ_DIR) $(APP_DIR) $(GRAPHS_DIR)/arquivos $(GRAPHS_DIR)/custom $(GRAPHS_DIR)/plotados)

.PHONY: clean run