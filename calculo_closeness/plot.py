from matplotlib.colors import LinearSegmentedColormap
import matplotlib.pyplot as plt
from pathlib import Path
import networkx as nx
import os


class GraphPlot:
    def __init__(self):
        self.__graph_files_path = Path("grafos", "arquivos")
        self.__images_path = Path("grafos", "plotados")
        if not os.path.isdir(self.__graph_files_path) or len(os.listdir(self.__graph_files_path)) == 0:
            print("Nenhum grafo foi gerado! Rode o binário em app/closeness antes!")
            exit(1)
        self._generate_images()

    def _read_adjacent_mat_file(self, filename):
        with open(Path(self.__graph_files_path, filename), 'r') as f:
            lines = f.readlines()
            num_vertices = int(lines[0])
            matrix = [list(map(int, line.split())) for line in lines[1:-1]]  
            closeness = list(map(float, lines[-1].split())) 
            return num_vertices, matrix, closeness

    def _generate_graph_image(self, num_vertices, adjacency_matrix, closeness, filename):
        G = nx.Graph()
        for i in range(num_vertices):
            G.add_node(i)
        for i in range(num_vertices):
            for j in range(num_vertices):
                if adjacency_matrix[i][j] != 0:
                    G.add_edge(i, j)

        colors = ['blue', 'lightblue', 'green', 'lime', 'yellow', 'orange','red']
        colors_list = [colors[0]] + colors + [colors[-1]]
        positions = [0] + [i / (len(colors) - 1) for i in range(len(colors))] + [1]
        cmap = LinearSegmentedColormap.from_list('custom_cmap', list(zip(positions, colors_list)))
        normalized_closeness = [(c - min(closeness)) / (max(closeness) - min(closeness)) for c in closeness]
        node_colors = [cmap(normalized) for normalized in normalized_closeness]
        pos = nx.spring_layout(G) 
        nx.draw(G, pos, with_labels=True, node_color=node_colors, node_size=120, font_size=7, cmap=None)
        graph_file = Path(self.__images_path, f"{filename.replace('.txt', '')}.png")
        plt.savefig(graph_file, format="png", dpi=200)
        plt.clf()

    def _generate_images(self):
        print("Gerando imagens dos arquivos dos grafos...")
        graph_files = os.listdir(Path(self.__graph_files_path))
        for index_file, graph_file in enumerate(graph_files):
            print(f"Gerando imagem {index_file+1}/{len(graph_files)} do arquivo {graph_file}")
            num_vertices, adjacency_matrix, closeness = self._read_adjacent_mat_file(graph_file)
            self._generate_graph_image(num_vertices, adjacency_matrix, closeness, graph_file)
        print("Todas as imagens foram geradas com sucesso!")



if __name__ == "__main__":
    G = GraphPlot()