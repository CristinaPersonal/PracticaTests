package es.codeurjc.ais.tictactoe;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.core.IsEqual.equalTo;

import es.codeurjc.ais.tictactoe.Board;
import es.codeurjc.ais.tictactoe.Player;

/*Pruebas unitarias de la clase Board: En estas pruebas se tiene que comprobar que la clase
implementa correctamente la detección de que un jugador ha ganado o de que se ha
empatado. Concretamente, se tienen que implementar varios tests que cambien el estado de
las celdas simulando una partida y posteriormente verificar que los métodos
getCellsIfWinner(…) y checkDraw() funcionan como se espera. El método getCellsIfWinner(…) devuelve el número de celdas que contienen la “línea” en caso de que
el jugador pasado como parámetro haya ganado. Si no, devuelve null. El método
checkDraw() devuelve true si el tablero está completo sin ninguna línea con fichas iguales. */

public class UnitaryBoardTest {

	private Player p1;
	private Player p2;
	private Board b;
	
	@Before
	public void setUp() {
		this.p1 = new Player(1, "O", "Cristina");
		this.p2 = new Player(2, "X", "Alfonso");
		this.b = new Board();
		this.b.enableAll(); 
	}
	
	//Necesitamos un método para marcar
	
	public void marcar(Player p, int celda) {
		this.b.getCell(celda).value = p.getLabel();
		this.b.getCell(celda).active = false;
	}
	
	/*Vamos a probar distintas situaciones:
	 * Hay empate
	 * El jugador 1 gana
	 * El jugador 1 pierde
	 */
	@Test
	public void empateTest() {
		/*Nuestro caso de prueba será el siguiente: 
		 * 
		 * O X X
		 * O X O   ->  EMPATE
		 * X O X
		 * 
		 * p1: O
		 * p2: X
		 */
		marcar(p1, 0);
		marcar(p2, 1);
		marcar(p1, 3);
		marcar(p2, 4);
		marcar(p1, 5);
		marcar(p2, 6);
		marcar(p1, 7);
		marcar(p2, 8);
		marcar(p1, 2);
		assertThat(null,equalTo(b.getCellsIfWinner(p1.getLabel())));
		assertThat(null,equalTo(b.getCellsIfWinner(p2.getLabel())));
		assertThat(true,equalTo(b.checkDraw()));
	}
	
	@Test
	public void jugador1ganaTest() {
		/*Nuestro caso de prueba será el siguiente: 
		 * 
		 * O O O
		 * X   X   ->  p1 GANA
		 *      
		 * 
		 * p1: O
		 * p2: X
		 */
		
		marcar(p1, 0);
		marcar(p2, 3);
		marcar(p1, 1);
		marcar(p2, 5);
		marcar(p1, 2);
		int[] jugadaGanadora = {0, 1, 2};
		
		assertThat(false, equalTo(this.b.checkDraw()));
		
		assertThat(jugadaGanadora, equalTo(this.b.getCellsIfWinner(p1.getLabel())));
		//Como el jugador2 no ha ganado nada, no hay nada con lo que comparar, debe ser vacía su jugada ganadora
		assertThat(null, equalTo(this.b.getCellsIfWinner(p2.getLabel())));
	}

	
	
	@Test
	public void jugador1pierdeTest() {
		/*Nuestro caso de prueba será el siguiente: 
		 * 
		 * X O -
		 * X - O   ->  p1 PIERDE
		 * X O -  
		 * 
		 * p1: O
		 * p2: X
		 */
		
		marcar(p1, 1);
		marcar(p2, 0);
		marcar(p1, 5);
		marcar(p2, 3);
		marcar(p1, 7);
		marcar(p2, 6);
		int[] jugadaGanadora = {0, 3, 6};
		
		assertThat(false, equalTo(b.checkDraw()));
		assertThat(null, equalTo(b.getCellsIfWinner(p1.getLabel())));
		assertThat(jugadaGanadora, equalTo(b.getCellsIfWinner(p2.getLabel())));
	}
	
	
}