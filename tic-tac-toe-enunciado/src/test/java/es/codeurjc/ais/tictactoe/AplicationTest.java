package es.codeurjc.ais.tictactoe;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class AplicationTest {
	protected WebDriver driver1;
	protected WebDriver driver2;
	
	private String jugador1 = "Cristina";
	private String jugador2 = "Alfonso";

	@Before
	public void setUp() {
		driver1 = new ChromeDriver();
		driver2 = new ChromeDriver();
		
		driver1.get("http://localhost:8081/");
		driver2.get("http://localhost:8081/");
	}
	
	@BeforeClass
	public static void setUpClass() {
		WebDriverManager.chromedriver().setup();		
		WebApp.start();
	}
	
	@After
	public void tearDown() {
		if(driver1 != null)
			driver1.quit();
		if(driver2 != null)
			driver2.quit();
	}
	
	@AfterClass
	public static void tearDownClass() {
		WebApp.stop();
	}
	
	@Test
	public void EmpateTest() {
		//Añadimos el jugador 1 a la partida
		driver1.findElement(By.id("nickname")).sendKeys(jugador1);
		driver1.findElement(By.id("startBtn")).click();
		
		//Añadimos el jugador 2 a la partida
		driver2.findElement(By.id("nickname")).sendKeys(jugador2);
		driver2.findElement(By.id("startBtn")).click();
		
		//El navegador espera un tiempo para poder cargar los elementos sin errores
		driver1.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		driver2.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		
		/*Nuestro caso de prueba será el siguiente: 
		 * 
		 * O O X
		 * X X O   ->  EMPATE
		 * O X X
		 * 
		 * p1: X
		 * p2: O
		 */
		
		driver1.findElement(By.id("cell-8")).click();
		driver2.findElement(By.id("cell-0")).click();
		driver1.findElement(By.id("cell-7")).click();
		driver2.findElement(By.id("cell-1")).click();
		driver1.findElement(By.id("cell-2")).click();
		driver2.findElement(By.id("cell-6")).click();
		driver1.findElement(By.id("cell-3")).click();
		driver2.findElement(By.id("cell-5")).click();
		driver1.findElement(By.id("cell-4")).click();
		
		String mensaje = "Draw!";
		
		driver1.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		
		String mensajeRevuelto = driver1.switchTo().alert().getText();
		
		assertEquals(mensaje, mensajeRevuelto);
	}
	
	@Test
	public void Jugador1GanaTest() {
		//Añadimos el jugador 1 a la partida
		driver1.findElement(By.id("nickname")).sendKeys(jugador1);
		driver1.findElement(By.id("startBtn")).click();
		
		//Añadimos el jugador 2 a la partida
		driver2.findElement(By.id("nickname")).sendKeys(jugador2);
		driver2.findElement(By.id("startBtn")).click();
		
		//El navegador espera un tiempo para poder cargar los elementos sin errores
		driver1.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		driver2.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		
		/*Nuestro caso de prueba será el siguiente: 
		 * 
		 * O X -
		 * - X -   ->  p1 GANA
		 * O X -
		 * 
		 * p1: X
		 * p2: O
		 */
		driver1.findElement(By.id("cell-1")).click();
		driver2.findElement(By.id("cell-6")).click();
		driver1.findElement(By.id("cell-4")).click();
		driver2.findElement(By.id("cell-0")).click();
		driver1.findElement(By.id("cell-7")).click();
		
		String mensaje = jugador1 + " wins! " + jugador2 + " looses.";
		
		driver1.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		
		String mensajeDevuelto = driver1.switchTo().alert().getText();
	
		assertEquals(mensaje, mensajeDevuelto);
	}
	
	
	@Test
	public void jugador1PierdeTest() {	
		//Asignamos el jugador 1
		driver1.findElement(By.id("nickname")).sendKeys(jugador1);
		driver1.findElement(By.id("startBtn")).click();
		
		//Asignamos el jugador 2
		driver2.findElement(By.id("nickname")).sendKeys(jugador2);
		driver2.findElement(By.id("startBtn")).click();
		
		//El navegador espera un tiempo para poder cargar los elementos sin errores
		driver1.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		driver2.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		
		/*Nuestro caso de prueba será el siguiente: 
		 * 
		 * - X O
		 * - O -   ->  p1 PIERDE
		 * O X X
		 * 
		 * p1: X
		 * p2: O
		 */
		driver1.findElement(By.id("cell-8")).click();
		driver2.findElement(By.id("cell-2")).click();
		driver1.findElement(By.id("cell-1")).click();
		driver2.findElement(By.id("cell-4")).click();
		driver1.findElement(By.id("cell-7")).click();
		driver2.findElement(By.id("cell-6")).click();
		
		String mensaje = jugador2 + " wins! " + jugador1 + " looses.";
		
		driver2.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		
		String mensajeDevuelto = driver2.switchTo().alert().getText();
		
		assertEquals(mensaje, mensajeDevuelto);
	}

}