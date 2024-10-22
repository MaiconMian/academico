from tkinter import Tk, Label, Button, DISABLED, NORMAL
from PIL import Image, ImageTk
from plot import GraphPlot
from pathlib import Path
import os



class GraphGUI:
    def __init__(self):
        GraphPlot()
        self.__images_path = Path("grafos", "plotados")
        self.__image_names = [i for i in os.listdir(Path(self.__images_path))]
        self.__window_root = Tk()
        self.__window_root.title(f"{self.__image_names[0]}")
        self.__window_root.geometry("880x700")
        self.__window_root.resizable(False, False)
        self.__images = [ImageTk.PhotoImage(i) for i in [self._resize_image(Image.open(Path(self.__images_path, i))) for i in self.__image_names]]
        self.__label = Label(image=self.__images[0])
        self.__label.grid(row=1, column=0, columnspan=3)
        self.__btn_exit = Button(self.__window_root, text="Sair", command=self.__window_root.quit)
        self.__btn_forward = Button(self.__window_root, text="Próxima", command=lambda: self._next_image(1))
        self.__btn_back = Button(self.__window_root, text="Anterior", command=lambda: self._previous_image(1), state=DISABLED)
        self.__btn_back.grid(row=5, column=0)
        self.__btn_exit.grid(row=5, column=1)
        self.__btn_forward.grid(row=5, column=2)
        self.__window_root.mainloop()

    def _resize_image(self, image):
        return image.resize((880, 670))

    def _next_image(self, image_number):
        self.__label.forget()
        self.__window_root.title(f"{self.__image_names[image_number]}")
        self.__label = Label(image=self.__images[image_number])
        self.__label.grid(row=1, column=0, columnspan=3)
        self.__btn_back = Button(self.__window_root, text="Anterior", command=lambda: self._previous_image(image_number-1))
        self.__btn_forward = Button(self.__window_root, text="Próxima", 
            command=lambda: self._next_image(image_number+1),
            state=DISABLED if image_number == (len(self.__images) - 1) else NORMAL)
        self.__btn_back.grid(row=5, column=0)
        self.__btn_exit.grid(row=5, column=1)
        self.__btn_forward.grid(row=5, column=2)

    def _previous_image(self, image_number):
        self.__label.forget()
        self.__window_root.title(f"{self.__image_names[image_number]}")
        self.__label = Label(image=self.__images[image_number])
        self.__label.grid(row=1, column=0, columnspan=3)
        self.__btn_forward = Button(self.__window_root, text="Próxima", command=lambda: self._next_image(image_number+1))
        self.__btn_back = Button(self.__window_root, text="Anterior", 
            command=lambda: self._previous_image(image_number-1),
            state=DISABLED if image_number == 0 else NORMAL) 
        self.__btn_back.grid(row=5, column=0)
        self.__btn_exit.grid(row=5, column=1)
        self.__btn_forward.grid(row=5, column=2)



if __name__ == "__main__":
    G = GraphGUI()