/******************************************************************************
Proyecto para Estructura de Datos  INF-104
"Manejo de un supermercado con pilas y colas"

Alumno: Miguel Angel Pacheco Arteaga
Docente: Msc. Irma Prado Villarroel
Semestre 2/2004
*******************************************************************************/

#include <conio.h>
#include <iostream.h>
#include <stdio.h>
#include <dos.h>
#include <stdlib.h>
#include <graphics.h>

// numero de carros del supermercado
const NumCarritos = 30;
int CheckCajas();

// Iniciamos  el modo grafico
void Graphic(void)
{
	int graphdriver = 9; // emula a una tarjeta VGA
	int graphmode = 1;	 // driver: VGA, graphic_modes: VGAMED, Value: 1
	// Columns x Row: 640 x 350, Palette: 16 color, Pages: 2
	initgraph(&graphdriver, &graphmode, "..\\bgi");
};

// Clase Persona (caracteristicas)
class Persona
{
public:
	int PosX;		 // valor de la posicion en X
	int PosY;		 // valor de la posicion en X
	int Accion;		 // estado movimiento
	int CCar;		 // estado carrito de compras
	int Comprando;	 // estado de compra
	int Color;		 // color de las personas
	Persona();		 // constructor de la clase Persona
	Persona *Move(); // puntero de movimiento
	Persona *Next;	 // puntero de posicion
};

void AddCaja(int, Persona *); // agregar fila(cola) a la caja
void AskCarrito(Persona *);	  // recoger carrito (de la pila)

Persona::Persona() // Caracteristicas de la persona
{
	PosX = 55;
	PosY = 1;
	Accion = 1;
	Comprando = 0;
	CCar = 0;
	Color = rand() % 15 + 1;
}

Persona *Persona::Move() // movimientos de las personas
{
	Persona *Temp = NULL; // asignamos el valor NULL a las personas
	switch (Accion)		  // definimos 10 casos para las personas
	{
	case 1:
		PosY += 10;
		if (PosY >= 260)
			return this; // this apunta al objeto para que la funcion es llamada
		break;
	case 2:
		break;
	case 3:
		if (PosX < 130)
			PosX += 10;
		else if ((PosY > 20) && (PosX < 210))
			PosY -= 10;
		else if (PosX < 210)
			PosX += 10;
		else if ((PosY < 260) && (PosX < 300))
			PosY += 10;
		else if (PosX < 300)
			PosX += 10;
		else if (PosY > 20)
			PosY -= 10;
		else if (PosX < 410)
			PosX += 10;
		else
		{
			Accion = CheckCajas() + 5;
		}
		break;
	case 4:
		if (PosX < 190)
			PosX += 10;
		else if (PosY > 20)
			PosY -= 10;
		else if (PosX < 410)
			PosX += 10;
		else
		{
			Accion = CheckCajas() + 5;
		}
		break;
	case 5:
		if (PosX < 300)
			PosX += 10;
		else if (PosY > 20)
			PosY -= 10;
		else if (PosX < 410)
			PosX += 10;
		else
		{
			Accion = CheckCajas() + 5;
		}
		break;
	case 6:
		if (PosY < 260)
			PosY += 10;
		else
		{
			AddCaja(1, this);
			Accion = 9;
		}
		break;
	case 7:
		if (PosX < 465)
			PosX += 10;
		else if (PosY < 260)
			PosY += 10;
		else
		{
			AddCaja(2, this);
			Accion = 9;
		}
		break;
	case 8:
		if (PosX < 520)
			PosX += 10;
		else if (PosY < 260)
			PosY += 10;
		else
		{
			AddCaja(3, this);
			Accion = 9;
		}
		break;
	case 9:
		break;
	case 10:
		CCar = 0;
		if (PosY < 340)
			PosY += 10;
		else
			Accion = 2;
	}
	return Temp;
}

class People // clase People (conjunto de personas)
{
public:
	Persona *First;
	People();
	~People();
	void Add();
	void Eliminar();
	void Show();
	void MoveAll();
};

People::People() // Comenzamos con un NULL en la memoria
{
	First = NULL;
}

People::~People() // Eliminamos el flujo de personas
{
	Persona *Temp, *Rec = First;
	while (Rec != NULL)
	{
		Temp = Rec;
		Rec = Rec->Next;
		delete Temp;
	}
}

void People::Add() // añadimos las personas
{
	Persona *Temp;
	Temp = new Persona;
	Temp->Next = First;
	First = Temp;
}

void People::Eliminar() // sacamos personas
{
	if (First != NULL)
	{
		Persona *Temp = First, *Aux;
		if (First->Next == NULL)
		{
			delete Temp;
			First = NULL;
		}
		else
		{
			while (Temp->Next != NULL)
			{
				Aux = Temp;
				Temp = Temp->Next;
			}
			Aux->Next = NULL;
			delete Temp;
		}
	}
}

void DrawCarrito(int X, int Y) // Dibujamos el carrito de compras
{
	setcolor(0);
	line(X - 15, Y - 5, X + 13, Y - 5);
	line(X - 12, Y - 2, X + 13, Y - 2);
	line(X - 12, Y + 1, X + 13, Y + 1);
	line(X - 12, Y + 4, X + 13, Y + 4);
	line(X - 12, Y + 7, X + 13, Y + 7);
	line(X - 7, Y + 8, X + 8, Y + 8);
	line(X - 7, Y + 9, X + 8, Y + 9);

	line(X - 12, Y - 5, X - 12, Y + 7);
	line(X - 7, Y - 5, X - 7, Y + 7);
	line(X - 2, Y - 5, X - 2, Y + 7);
	line(X + 3, Y - 5, X + 3, Y + 7);
	line(X + 8, Y - 5, X + 8, Y + 7);
	line(X + 13, Y - 5, X + 13, Y + 7);
}

void DrawPersona(int X, int Y, int C, int Color)
// Dibujamos a las personas con ayuda de vectores
{
	int F = 0;
	int B = Color;
	int S = 2;
	int Body[6];
	Body[0] = X - 11; // cuerpo de las personas
	Body[1] = Y - 11;
	Body[2] = X + 11;
	Body[3] = Y - 11;
	Body[4] = X;
	Body[5] = Y + 10;

	setcolor(S);
	setfillstyle(1, B);
	fillpoly(3, Body);
	int Len[10]; // lentes de las personas
	Len[0] = X - 10;
	Len[1] = Y - 6;
	Len[2] = X + 10;
	Len[3] = Y - 6;
	Len[4] = X + 2;
	Len[5] = Y - 2;
	Len[6] = X;
	Len[7] = Y - 6;
	Len[8] = X - 2;
	Len[9] = Y - 2;

	setcolor(F);
	setfillstyle(1, F);
	fillpoly(5, Len);

	putpixel(X - 9, Y - 5, F); // para los brazos y pies de las personas
	putpixel(X - 4, Y - 3, F);
	putpixel(X - 5, Y - 3, F);
	putpixel(X - 7, Y - 4, F);
	putpixel(X - 10, Y - 5, F);
	putpixel(X - 8, Y - 4, F);

	putpixel(X + 9, Y - 5, F);
	putpixel(X + 4, Y - 3, F);
	putpixel(X + 5, Y - 3, F);
	putpixel(X + 7, Y - 4, F);
	putpixel(X + 10, Y - 5, F);
	putpixel(X + 8, Y - 4, F);

	putpixel(X - 1, Y + 2, F);
	putpixel(X, Y + 3, F);
	putpixel(X + 1, Y + 3, F);

	putpixel(X - 6, Y, F);
	putpixel(X - 7, Y, F);
	putpixel(X - 8, Y, F);
	putpixel(X - 8, Y + 1, F);
	putpixel(X - 9, Y + 1, F);
	putpixel(X - 10, Y + 1, F);
	putpixel(X - 10, Y + 2, F);
	putpixel(X - 11, Y + 2, F);

	putpixel(X + 6, Y, F);
	putpixel(X + 7, Y, F);
	putpixel(X + 8, Y, F);
	putpixel(X + 8, Y + 1, F);
	putpixel(X + 9, Y + 1, F);
	putpixel(X + 10, Y + 1, F);
	putpixel(X + 10, Y + 2, F);
	putpixel(X + 11, Y + 2, F);

	setcolor(S);
	line(X - 9, Y + 3, X - 12, Y + 3);
	putpixel(X - 9, Y + 4, S);
	putpixel(X - 13, Y + 4, S);
	putpixel(X - 9, Y + 5, S);
	putpixel(X - 10, Y + 5, S);
	putpixel(X - 14, Y + 5, S);

	putpixel(X - 11, Y + 6, S);
	putpixel(X - 14, Y + 6, S);
	putpixel(X - 11, Y + 7, S);
	putpixel(X - 14, Y + 7, S);
	putpixel(X - 10, Y + 8, S);
	putpixel(X - 14, Y + 8, S);
	putpixel(X - 10, Y + 9, S);
	putpixel(X - 14, Y + 9, S);
	line(X - 13, Y + 10, X - 10, Y + 10);

	setcolor(B);
	line(X - 12, Y + 4, X - 10, Y + 4);
	line(X - 13, Y + 5, X - 11, Y + 5);
	line(X - 13, Y + 6, X - 12, Y + 6);
	line(X - 13, Y + 7, X - 12, Y + 7);
	line(X - 13, Y + 8, X - 10, Y + 8);
	line(X - 13, Y + 9, X - 10, Y + 9);

	setcolor(S);
	line(X + 9, Y + 3, X + 12, Y + 3);
	putpixel(X + 9, Y + 4, S);
	putpixel(X + 13, Y + 4, S);
	putpixel(X + 9, Y + 5, S);
	putpixel(X + 10, Y + 5, S);
	putpixel(X + 14, Y + 5, S);

	putpixel(X + 11, Y + 6, S);
	putpixel(X + 14, Y + 6, S);
	putpixel(X + 11, Y + 7, S);
	putpixel(X + 14, Y + 7, S);
	putpixel(X + 10, Y + 8, S);
	putpixel(X + 14, Y + 8, S);
	putpixel(X + 10, Y + 9, S);
	putpixel(X + 14, Y + 9, S);
	line(X + 13, Y + 10, X + 10, Y + 10);

	setcolor(B);
	line(X + 12, Y + 4, X + 10, Y + 4);
	line(X + 13, Y + 5, X + 11, Y + 5);
	line(X + 13, Y + 6, X + 12, Y + 6);
	line(X + 13, Y + 7, X + 12, Y + 7);
	line(X + 13, Y + 8, X + 10, Y + 8);
	line(X + 13, Y + 9, X + 10, Y + 9);

	setcolor(F);
	line(X - 3, Y + 6, X - 3, Y + 8);
	line(X - 4, Y + 9, X - 4, Y + 12);
	line(X - 3, Y + 13, X - 3, Y + 14);

	putpixel(X + 2, Y + 8, F);
	line(X + 3, Y + 9, X + 3, Y + 12);
	line(X + 4, Y + 13, X + 4, Y + 14);

	setcolor(S);
	line(X - 2, Y + 14, X - 5, Y + 14);
	line(X - 4, Y + 15, X - 9, Y + 15);
	line(X - 3, Y + 16, X - 4, Y + 16);
	putpixel(X - 10, Y + 16, S);
	line(X - 2, Y + 17, X - 3, Y + 17);
	putpixel(X - 11, Y + 17, S);
	line(X - 2, Y + 18, X - 3, Y + 18);
	putpixel(X - 11, Y + 18, S);
	line(X - 2, Y + 19, X - 11, Y + 19);

	setcolor(B);
	putpixel(X - 2, Y + 15, B);
	putpixel(X - 3, Y + 15, B);
	putpixel(X - 2, Y + 16, B);
	line(X - 5, Y + 16, X - 9, Y + 16);
	line(X - 4, Y + 17, X - 10, Y + 17);
	line(X - 4, Y + 18, X - 10, Y + 18);

	setcolor(S);
	line(X + 3, Y + 14, X + 5, Y + 14);
	line(X + 10, Y + 14, X + 14, Y + 14);
	line(X + 5, Y + 15, X + 6, Y + 15);
	putpixel(X + 9, Y + 15, S);
	putpixel(X + 15, Y + 15, S);
	putpixel(X + 15, Y + 16, S);
	putpixel(X + 16, Y + 17, S);
	line(X + 4, Y + 16, X + 9, Y + 16);
	line(X + 3, Y + 17, X + 5, Y + 17);
	line(X + 3, Y + 18, X + 5, Y + 18);
	line(X + 15, Y + 18, X + 16, Y + 18);
	line(X + 3, Y + 19, X + 16, Y + 19);

	setcolor(B);
	putpixel(X + 3, Y + 15, B);
	putpixel(X + 4, Y + 15, B);
	putpixel(X + 3, Y + 16, B);
	line(X + 10, Y + 15, X + 14, Y + 15);
	line(X + 10, Y + 16, X + 14, Y + 16);
	line(X + 6, Y + 17, X + 15, Y + 17);
	line(X + 6, Y + 18, X + 14, Y + 18);
	putpixel(X, Y, F);

	if (C == 1)
		DrawCarrito(X + 5, Y + 10);
}

// Optativo para la forma del suelo
char SueloStyle[8] = {
	0xF0, // 11110000
	0xF0, // 11110000
	0xF0, // 11110000
	0xF0, // 11110000
	0xF,  // 00001111
	0xF,  // 00001111
	0xF,  // 00001111
	0xF	  // 00001111
};

void People::Show() // Muestra a las personas
{
	if (First != NULL)
	{
		Persona *Temp = First;
		while (Temp != NULL)
		{
			if ((Temp->Accion != 2) && (Temp->Accion != 9))
				DrawPersona(Temp->PosX, Temp->PosY, Temp->CCar, Temp->Color);
			Temp = Temp->Next;
		}
	}
}

void People::MoveAll() // Las personas que recogen los carritos
{
	if (First != NULL)
	{
		Persona *Temp = First, *Aux;
		while (Temp != NULL)
		{
			if ((Aux = Temp->Move()) != NULL)
				if (Aux->Accion == 1)
					AskCarrito(Aux);
			Temp = Temp->Next;
		}
	}
}

typedef char Byte; // definimos un char Byte

struct Nodo // definimos nodos para las personas
{
	Persona *Data;
	Nodo *Next;
};

struct NodoL // Definimos nodos por los datos
{
	char Data;
	NodoL *Next;
};

class Pila // Establecemos la clase pila
{
	NodoL *Tope;

public:
	Pila();
	~Pila();
	void Push(char);
	Byte Pop();
	void Show(int, int);
};

class Cola // Establecemos la clase cola
{
	Nodo *Last, *First;

public:
	int NElem;
	int Elem;
	Cola();
	~Cola();
	Persona *Eliminar();
	void Add(Persona *);
	void Show(int, int);
};

Pila::Pila() // Constructor
{
	Tope = NULL;
}

void Pila::~Pila() // Destructor
{
	NodoL *Temp, *Rec = Tope;
	while (Rec != NULL)
	{
		Temp = Rec;
		Rec = Rec->Next;
		delete Temp;
	}
}

void Pila::Push(char T) // PUSH de la pila
{
	NodoL *Temp;
	Temp = new NodoL;
	Temp->Next = Tope;
	Temp->Data = T;
	Tope = Temp;
}

Byte Pila::Pop() // POP de la pila
{
	if (Tope == NULL)
	{
		return -1;
	}
	else
	{
		NodoL *Temp = Tope;
		Tope = Tope->Next;
		delete Temp;
		return 1;
	}
}

void Pila::Show(int X, int Y) // Pila de carritos
{
	NodoL *Temp;
	Temp = Tope;
	int C = 0, XT = X;
	while (Temp != NULL)
	{
		DrawCarrito(X, Y);
		X += 12;
		Temp = Temp->Next;
		C++;
		if ((C % 5) == 0)
		{
			Y += 12;
			X = XT;
		}
	}
}

Cola::Cola() // Constructor
{
	Last = NULL;
	First = NULL;
	NElem = 0;
	Elem = 0;
}

Cola::~Cola() // Destructor
{
	Nodo *Temp, *Rec = Last;
	while (Rec != NULL)
	{
		Temp = Rec;
		Rec = Rec->Next;
		delete Temp;
	}
}

void Cola::Add(Persona *T) // Añade personas a la cola
{
	NElem++;
	Nodo *Temp;
	Temp = new Nodo;
	Temp->Next = Last;
	Temp->Data = T;
	Last = Temp;
	T->Accion = 2;
	if (First == NULL)
		First = Last;
}

void Cola::Show(int X, int Y) // Muestra el ingreso de personas a la cola
{
	Nodo *Temp;
	Temp = Last;
	Y = Y - (NElem * 10) + 10;
	while (Temp != NULL)
	{
		DrawPersona(X, Y, Temp->Data->CCar, Temp->Data->Color);
		Y += 10;
		Temp = Temp->Next;
	}
}

Persona *Cola::Eliminar() // Muestra la salida de personas a la cola
{
	Persona *Te = First->Data;
	Nodo *Temp = Last;
	if (First == NULL)
	{
		Te = NULL;
	}
	else if (Last == First)
	{
		delete Temp;
		First = NULL;
		Last = NULL;
		NElem = 0;
	}
	else
	{
		Nodo *Aux = First;
		while (Temp->Next != First)
		{
			Temp = Temp->Next;
		}
		Temp->Next = NULL;
		delete Aux;
		First = Temp;
		NElem--;
	}
	return Te;
}

// Objetos Globales del proyecto
Pila Carritos;
Cola WaitCarritos;
Cola Caja1, Caja2, Caja3;

void AddCaja(int C, Persona *Temp) // Ingreso de colas a la caja
{
	Cola *CajaE;
	switch (C)
	{
	case 1:
		CajaE = &Caja1;
		break;
	case 2:
		CajaE = &Caja2;
		break;
	case 3:
		CajaE = &Caja3;
		break;
	}
	CajaE->Add(Temp);
}

void ShowCaja(int X, int Y) // Dibuja la caja
{
	setfillstyle(1, 9);
	setcolor(15);
	bar(X - 30, Y - 10, X + 30, Y + 10);

	line(X - 22, Y - 6, X - 15, Y - 6);
	line(X - 22, Y - 6, X - 22, Y + 7);
	line(X - 22, Y + 7, X - 15, Y + 7);

	line(X - 10, Y - 6, X - 3, Y - 6);
	line(X - 10, Y - 6, X - 10, Y + 7);

	line(X - 3, Y - 6, X - 3, Y + 7);
	line(X - 10, Y, X - 3, Y);

	line(X + 2, Y + 3, X + 2, Y + 7);
	line(X + 2, Y + 7, X + 9, Y + 7);
	line(X + 9, Y + 7, X + 9, Y - 6);

	line(X + 14, Y - 6, X + 14, Y + 7);
	line(X + 14, Y - 6, X + 21, Y - 6);
	line(X + 21, Y - 6, X + 21, Y + 7);
	line(X + 21, Y, X + 14, Y);
}

void CajasShow() // Muestra las cajas
{
	setfillstyle(1, 4);
	ShowCaja(400, 290);
	ShowCaja(470, 290);
	ShowCaja(540, 290);
	int X = 400, Y = 255;
	Caja1.Show(X, Y);
	X += 55;
	Caja2.Show(X, Y);
	X += 55;
	Caja3.Show(X, Y);
}

int CheckCajas() // Verifica las actividades de la caja
{
	int CajaD = 1;
	Cola *CajaE = &Caja1;
	if (Caja2.Elem < CajaE->Elem)
	{
		CajaD = 2;
		CajaE = &Caja2;
	}
	if (Caja3.Elem < CajaE->Elem)
	{
		CajaD = 3;
		CajaE = &Caja3;
	}
	CajaE->Elem++;
	return CajaD;
}

void AskCarrito(Persona *Temp) // Espera carritos si es que la pila esta vacía
{
	if (Carritos.Pop() != 1)
	{
		WaitCarritos.Add(Temp);
	}
	else
	{
		int Ac = rand() % 3 + 3;
		Temp->Accion = Ac;
		Temp->CCar = 1;
	}
}

void CheckNewCarrito() // Lleva carritos a la pila cuando salen de la caja
{
	Persona *Temp;
	if (Carritos.Pop() != -1)
	{
		Temp = WaitCarritos.Eliminar();
		int Ac = rand() % 3 + 3;
		Temp->Accion = Ac;
		Temp->CCar = 1;
	}
}

void ProceedCajas() // Atencion  en caja
{
	Persona *Temp;
	if ((Temp = Caja1.Eliminar()) != NULL)
	{
		Temp->Accion = 10;
		Temp->PosX = 400;
		Carritos.Push('C');
	}
	if ((Temp = Caja2.Eliminar()) != NULL)
	{
		Temp->Accion = 10;
		Temp->PosX = 455;
		Carritos.Push('C');
	}
	if ((Temp = Caja3.Eliminar()) != NULL)
	{
		Temp->Accion = 10;
		Temp->PosX = 510;
		Carritos.Push('C');
	}
}

// Variable global
People Test;

void Show() // presentacion del escenario
{
	setbkcolor(0);
	// Optativo con diseno
	// setfillpattern(SueloStyle,15);
	setfillstyle(1, 15);
	bar(0, 0, 640, 350); // barra bidimensional
	setfillstyle(1, 9);	 // estilo de relleno y color
	bar(100, 0, 105, 230);
	bar(100, 285, 105, 350);
	bar(350, 50, 355, 350);
	bar(435, 110, 437, 230);
	bar(492, 110, 494, 230);
	setfillstyle(1, 9);
	bar(160, 35, 175, 205);
	bar(235, 35, 265, 205);
	bar(150, 300, 305, 325);
	Test.Show();
	Carritos.Show(20, 280);
	WaitCarritos.Show(20, 265);
	CajasShow();
}

main()
{
	for (int Da = 0; Da < NumCarritos; Da++)
		Carritos.Push('C');
	Graphic();
	clearviewport();
	settextstyle(7, 0, 1);
	outtextxy(20, 50, "Estructura de Datos - INF 104");
	settextstyle(7, 0, 1);
	outtextxy(20, 100, "Docente: Msc. Irma Prado");
	settextstyle(7, 0, 1);
	outtextxy(20, 150, "Alumno: Miguel Angel Pacheco Arteaga");
	settextstyle(7, 0, 1);
	outtextxy(20, 200, "Proyecto: ");
	settextstyle(7, 0, 1);
	outtextxy(20, 250, "Manejo de un supermercado con pilas y colas");
	getch();
	clearviewport(); // limpiamos la pantalla grafica
	Test.Add();		 // Aplicamos la variable global
	int Compra = 0;
	char T = 0;
	randomize();
	char R = rand() % 10 + 4;
	int AVP = 0;
	// setactivepage hace de la pagina, una pagina activa de graficos.
	// El rendimiento de todos los gráficos se dirigira a esa pagina de graficos.
	// Esto solo funciona con tarjetas VGA,CGA y Hercules
	do
	{
		delay(100);
		Compra++;
		if (Compra >= 33)
		{
			ProceedCajas();
			Compra = 0;
		}
		if (WaitCarritos.NElem > 0)
			CheckNewCarrito();
		Test.MoveAll();
		if (AVP == 0)
			AVP = 1;
		else
			AVP = 0;
		setactivepage(AVP); // Pagina los graficos en este entorno
		Show();
		setvisualpage(AVP);
		// setvisualpage hace llamar al pagina visual grafica
		T++;
		if (T == R)
		{
			if (WaitCarritos.NElem < 10)
				Test.Add();
			T = 0;
			R = rand() % 10 + 4;
		}
	} while (!kbhit()); // hasta presionar una tecla
	clearviewport();
	getch();
	return 0;
}