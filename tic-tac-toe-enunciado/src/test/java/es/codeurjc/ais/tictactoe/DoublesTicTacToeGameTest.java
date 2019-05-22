package es.codeurjc.ais.tictactoe;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.ArgumentCaptor;
import static org.mockito.Mockito.*;

import java.util.concurrent.CopyOnWriteArrayList;

import es.codeurjc.ais.tictactoe.TicTacToeGame.CellMarkedValue;
import es.codeurjc.ais.tictactoe.TicTacToeGame.Event;
import es.codeurjc.ais.tictactoe.TicTacToeGame.EventType;
import es.codeurjc.ais.tictactoe.TicTacToeGame.WinnerValue;



public class DoublesTicTacToeGameTest {
	TicTacToeGame partida;
	Connection conexion1, conexion2;
	Player jugador1, jugador2;
	
	@Before
	public void setUp() {
		//1) Crear el objeto TicTacToeGame
		this.partida = new TicTacToeGame();
		
		//2) Crear los dobles de los objetos connection
		conexion1 = mock(Connection.class);
		conexion2 = mock(Connection.class);
		
		//3) Añadir los dobles al objeto TicTacToeGame
		partida.addConnection(conexion1);
		partida.addConnection(conexion2);
		
		//4) Añadir los jugadores al objeto TicTacToeGame
		jugador1 = new Player(0, "X", "Cristina");
		jugador2 = new Player(1, "O", "Alfonso");
	}
	
	//6) Comprobar que la conexión 1 recibe el evento JOIN_GAME, con ambos jugadores
	@Test
	public void conexion1_JOINGAME_Test() {
		doNothing().when(conexion1).sendEvent(EventType.JOIN_GAME, partida.getPlayers());
		
		partida.addPlayer(jugador1);
		partida.addPlayer(jugador2);
		
		ArgumentCaptor<Event> argument = ArgumentCaptor.forClass(Event.class);
		verify(conexion1, times(2)).sendEvent(eq(EventType.JOIN_GAME), argument.capture());
		Object event = argument.getValue();
		CopyOnWriteArrayList<Player> eventPlayers = (CopyOnWriteArrayList<Player>) event;
		assertEquals(2, eventPlayers.size());
	}
	
	//7) Comprobar que la conexión 1 recibe el evento JOIN_GAME, con ambos jugadores
	@Test
	public void conexion2_JOINGAME_Test() {
		doNothing().when(conexion2).sendEvent(EventType.JOIN_GAME, partida.getPlayers());
		
		partida.addPlayer(jugador1);
		partida.addPlayer(jugador2);
		
		ArgumentCaptor<Event> argument = ArgumentCaptor.forClass(Event.class);
		verify(conexion2, times(2)).sendEvent(eq(EventType.JOIN_GAME), argument.capture());
		Object event = argument.getValue();
		CopyOnWriteArrayList<Player> eventPlayers = (CopyOnWriteArrayList<Player>) event;
		assertEquals(2, eventPlayers.size());
	}
	
	@Test
    public void empateTest() {
		int turnoActual;
		
		doNothing().when(conexion1).sendEvent(EventType.MARK, 0);
		
		partida.addPlayer(jugador1);
		partida.addPlayer(jugador2);
		
		/*Nuestro caso de prueba será el siguiente: 
		 * 
		 * O O X
		 * X X O   ->  EMPATE
		 * O X X
		 * 
		 * p1: X
		 * p2: O
		 */
		
		// Marca Jugador 1
		partida.mark(2);
		
		ArgumentCaptor<Event> argument0 = ArgumentCaptor.forClass(Event.class);
		verify(conexion1, times(1)).sendEvent(eq(EventType.MARK), argument0.capture());
		Object event0 = argument0.getValue();
		CellMarkedValue cell0 = (CellMarkedValue) event0;
		turnoActual = cell0.player.getId();
		
		assertEquals(0, turnoActual);
		
		// Marca Jugador 2
		partida.mark(0);
		
		ArgumentCaptor<Event> argument1 = ArgumentCaptor.forClass(Event.class);
		verify(conexion1, times(2)).sendEvent(eq(EventType.MARK), argument1.capture());
		Object event1 = argument1.getValue();
		CellMarkedValue cell1 = (CellMarkedValue) event1;
		turnoActual = cell1.player.getId();
		
		assertEquals(1, turnoActual);
		
		// Marca Jugador 1
		partida.mark(3);
		
		ArgumentCaptor<Event> argument2 = ArgumentCaptor.forClass(Event.class);
		verify(conexion1, times(3)).sendEvent(eq(EventType.MARK), argument2.capture());
		Object event2 = argument2.getValue();
		CellMarkedValue cell2 = (CellMarkedValue) event2;
		turnoActual = cell2.player.getId();
		
		assertEquals(0, turnoActual);
		
		// Marca Jugador 2
		partida.mark(1);
		
		ArgumentCaptor<Event> argument3 = ArgumentCaptor.forClass(Event.class);
		verify(conexion1, times(4)).sendEvent(eq(EventType.MARK), argument3.capture());
		Object event3 = argument3.getValue();
		CellMarkedValue cell3 = (CellMarkedValue) event3;
		turnoActual = cell3.player.getId();
		
		assertEquals(1, turnoActual);
		
		// Marca Jugador 1
		partida.mark(4);
		
		ArgumentCaptor<Event> argument4 = ArgumentCaptor.forClass(Event.class);
		verify(conexion1, times(5)).sendEvent(eq(EventType.MARK), argument4.capture());
		Object event4 = argument4.getValue();
		CellMarkedValue cell4 = (CellMarkedValue) event4;
		turnoActual = cell4.player.getId();
		
		assertEquals(0, turnoActual);
		
		// Marca Jugador 2
		partida.mark(5);
		
		ArgumentCaptor<Event> argument5 = ArgumentCaptor.forClass(Event.class);
		verify(conexion1, times(6)).sendEvent(eq(EventType.MARK), argument5.capture());
		Object event5 = argument5.getValue();
		CellMarkedValue cell5 = (CellMarkedValue) event5;
		turnoActual = cell5.player.getId();
		
		assertEquals(1, turnoActual);
		
		// Marca Jugador 1
		partida.mark(7);
		
		ArgumentCaptor<Event> argument6 = ArgumentCaptor.forClass(Event.class);
		verify(conexion1, times(7)).sendEvent(eq(EventType.MARK), argument6.capture());
		Object event6 = argument6.getValue();
		CellMarkedValue cell6 = (CellMarkedValue) event6;
		turnoActual = cell6.player.getId();
		
		assertEquals(0, turnoActual);
		
		// Marca Jugador 2
		partida.mark(6);
		
		ArgumentCaptor<Event> argument7 = ArgumentCaptor.forClass(Event.class);
		verify(conexion1, times(8)).sendEvent(eq(EventType.MARK), argument7.capture());
		Object event7 = argument7.getValue();
		CellMarkedValue cell7 = (CellMarkedValue) event7;
		turnoActual = cell7.player.getId();
		
		assertEquals(1, turnoActual);
		
		// Marca Jugador 1
		partida.mark(8);
		
		ArgumentCaptor<Event> argument8 = ArgumentCaptor.forClass(Event.class);
		verify(conexion1, times(9)).sendEvent(eq(EventType.MARK), argument8.capture());
		Object event8 = argument8.getValue();
		CellMarkedValue cell8 = (CellMarkedValue) event8;
		turnoActual = cell8.player.getId();
		
		assertEquals(0, turnoActual);
		
		//Comprobamos que hay empate
		doNothing().when(conexion1).sendEvent(EventType.GAME_OVER, null);
		ArgumentCaptor<Event> argument = ArgumentCaptor.forClass(Event.class);
		verify(conexion1, times(1)).sendEvent(eq(EventType.GAME_OVER), argument.capture());
		Object event = argument.getValue();
		WinnerValue winnervalue = (WinnerValue) event;
		
		assertEquals(null, winnervalue);
	}
	
	@Test
    public void jugardor1GanaTest() {
		int turnoActual;
		
		doNothing().when(conexion1).sendEvent(EventType.MARK, 0);
		
		partida.addPlayer(jugador1);
		partida.addPlayer(jugador2);
		
		/*Nuestro caso de prueba será el siguiente: 
		 * 
		 * O X -
		 * - X -   ->  p1 GANA
		 * O X -
		 * 
		 * p1: X
		 * p2: O
		 */
			
		// Marca Jugador 1
		partida.mark(1);
		
		ArgumentCaptor<Event> argument = ArgumentCaptor.forClass(Event.class);
		verify(conexion1, times(1)).sendEvent(eq(EventType.MARK), argument.capture());
		Object event = argument.getValue();
		CellMarkedValue cell = (CellMarkedValue) event;
		turnoActual = cell.player.getId();
		
		assertEquals(0, turnoActual);
		
		// Marca Jugador 2
		partida.mark(0);
		
		ArgumentCaptor<Event> argument1 = ArgumentCaptor.forClass(Event.class);
		verify(conexion1, times(2)).sendEvent(eq(EventType.MARK), argument1.capture());
		Object event1 = argument1.getValue();
		CellMarkedValue cell1 = (CellMarkedValue) event1;
		turnoActual = cell1.player.getId();
		
		assertEquals(1, turnoActual);
		
		// Marca Jugador 1
		partida.mark(4);
		
		ArgumentCaptor<Event> argument2 = ArgumentCaptor.forClass(Event.class);
		verify(conexion1, times(3)).sendEvent(eq(EventType.MARK), argument2.capture());
		Object event2 = argument2.getValue();
		CellMarkedValue cell2 = (CellMarkedValue) event2;
		turnoActual = cell2.player.getId();
		
		assertEquals(0, turnoActual);
	
		// Marca Jugador 2
		partida.mark(6);
		
		ArgumentCaptor<Event> argument3 = ArgumentCaptor.forClass(Event.class);
		verify(conexion1, times(4)).sendEvent(eq(EventType.MARK), argument3.capture());
		Object event3 = argument3.getValue();
		CellMarkedValue cell3 = (CellMarkedValue) event3;
		turnoActual = cell3.player.getId();
		
		assertEquals(1, turnoActual);
		
		// Marca Jugador 1
		partida.mark(7);
		
		ArgumentCaptor<Event> argument4 = ArgumentCaptor.forClass(Event.class);
		verify(conexion1, times(5)).sendEvent(eq(EventType.MARK), argument4.capture());
		Object event4 = argument4.getValue();
		CellMarkedValue cell4 = (CellMarkedValue) event4;
		turnoActual = cell4.player.getId();
		
		assertEquals(0, turnoActual);
	
		
		//Comprobamos que el jugador 1 ha ganado
		int[] jugadaGanadora = {1, 4, 7};
		WinnerValue ganador = new WinnerValue();
		ganador.player = partida.getPlayers().get(0);
		ganador.pos = jugadaGanadora;
		
		doNothing().when(conexion1).sendEvent(EventType.GAME_OVER, ganador);
		ArgumentCaptor<Event> argument7 = ArgumentCaptor.forClass(Event.class);
		verify(conexion1, times(1)).sendEvent(eq(EventType.GAME_OVER), argument7.capture());
		Object event7 = argument7.getValue();
		WinnerValue winnervalue = (WinnerValue) event7;
		
		assertEquals(0, winnervalue.player.getId());
		assertArrayEquals(jugadaGanadora, winnervalue.pos);
	}
	
	@Test
    public void jugador1PierdeTest() {
		int turnoActual;
		
		doNothing().when(conexion1).sendEvent(EventType.MARK, 0);
		
		partida.addPlayer(jugador1);
		partida.addPlayer(jugador2);
		
		/*Nuestro caso de prueba será el siguiente: 
		 * 
		 * - X O
		 * - O -   ->  p1 PIERDE
		 * O X X
		 * 
		 * p1: X
		 * p2: O
		 */
			
		// Marca Jugador 1
		partida.mark(8);
		
		ArgumentCaptor<Event> argument = ArgumentCaptor.forClass(Event.class);
		verify(conexion1, times(1)).sendEvent(eq(EventType.MARK), argument.capture());
		Object event = argument.getValue();
		CellMarkedValue cell = (CellMarkedValue) event;
		turnoActual = cell.player.getId();
		
		assertEquals(0, turnoActual);
		
		// Marca Jugador 2
		partida.mark(2);
		
		ArgumentCaptor<Event> argument2 = ArgumentCaptor.forClass(Event.class);
		verify(conexion1, times(2)).sendEvent(eq(EventType.MARK), argument2.capture());
		Object event2 = argument2.getValue();
		CellMarkedValue cell2 = (CellMarkedValue) event2;
		turnoActual = cell2.player.getId();
		
		assertEquals(1, turnoActual);
		
		// Marca Jugador 1
		partida.mark(1);
		
		ArgumentCaptor<Event> argument3 = ArgumentCaptor.forClass(Event.class);
		verify(conexion1, times(3)).sendEvent(eq(EventType.MARK), argument3.capture());
		Object event3 = argument3.getValue();
		CellMarkedValue cell3 = (CellMarkedValue) event3;
		turnoActual = cell3.player.getId();
		
		assertEquals(0, turnoActual);
	
		// Marca Jugador 2
		partida.mark(4);
		
		ArgumentCaptor<Event> argument4 = ArgumentCaptor.forClass(Event.class);
		verify(conexion1, times(4)).sendEvent(eq(EventType.MARK), argument4.capture());
		Object event4 = argument4.getValue();
		CellMarkedValue cell4 = (CellMarkedValue) event4;
		turnoActual = cell4.player.getId();
		
		assertEquals(1, turnoActual);
		
		// Marca Jugador 1
		partida.mark(7);
		
		ArgumentCaptor<Event> argument5 = ArgumentCaptor.forClass(Event.class);
		verify(conexion1, times(5)).sendEvent(eq(EventType.MARK), argument5.capture());
		Object event5 = argument5.getValue();
		CellMarkedValue cell5 = (CellMarkedValue) event5;
		turnoActual = cell5.player.getId();
		
		assertEquals(0, turnoActual);
		
		// Marca Jugador 2
		partida.mark(6);
		
		//Comprobamos que el jugador 2 es el que gana y que por lo tanto el jugador 1 pierde
		int[] jugadaGanadora = {6,4,2};
		WinnerValue ganador = new WinnerValue();
		ganador.player = partida.getPlayers().get(1);
		ganador.pos = jugadaGanadora;
		
		doNothing().when(conexion1).sendEvent(EventType.GAME_OVER, ganador);
		ArgumentCaptor<Event> argument6 = ArgumentCaptor.forClass(Event.class);
		verify(conexion1, times(1)).sendEvent(eq(EventType.GAME_OVER), argument6.capture());
		Object event6 = argument6.getValue();
		WinnerValue winnervalue = (WinnerValue) event6;
		
		assertEquals(1, winnervalue.player.getId());
		assertArrayEquals(jugadaGanadora, winnervalue.pos);
	}

}