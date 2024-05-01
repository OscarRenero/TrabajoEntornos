package pruebas;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class SistemaVentaEntradasTest {

	private SistemaVentaEntradas sistema;

	@Test
	public void testAnyadirSala() {
		SistemaVentaEntradas sistema = new SistemaVentaEntradas();

		assertTrue(sistema.añadirSala(1, "Titanic"));

		assertFalse(sistema.añadirSala(1, "Avatar"));

		assertTrue(sistema.añadirSala(2, "Avatar"));

		for (int i = 3; i <= SistemaVentaEntradas.MAX_SALAS; i++) {
			assertTrue(sistema.añadirSala(i, "Pelicula " + i));
		}

		assertFalse(sistema.añadirSala(SistemaVentaEntradas.MAX_SALAS + 1, "Avatar 2"));
		sistema.vaciarCine();	
	}

	@Test
	public void testComprarEntrada() {
		sistema = new SistemaVentaEntradas();

		sistema.añadirSala(1, "Titanic");
		assertTrue(sistema.comprarEntrada(1, 1));
		assertFalse(sistema.comprarEntrada(2, 1));
		assertFalse(sistema.comprarEntrada(1, 0));
		assertFalse(sistema.comprarEntrada(1, 31));

		sistema.vaciarCine();
	}

	
	@Test
	public void testGetEntradasVendidasPorSala() {
	    SistemaVentaEntradas sistema = new SistemaVentaEntradas();
	    sistema.añadirSala(1, "Titanic");
	    sistema.añadirSala(2, "Avatar");

	    sistema.comprarEntrada(1, 5);
	    sistema.comprarEntrada(1, 15);
	    sistema.comprarEntrada(2, 10);

	    assertEquals(2, sistema.getEntradasVendidasPorSala(1));
	    assertEquals(1, sistema.getEntradasVendidasPorSala(2));
	    assertEquals(0, sistema.getEntradasVendidasPorSala(3));

	    sistema.vaciarCine();
	    assertEquals(0, sistema.getEntradasVendidasPorSala(1)); 
	}
	
	@Test
	public void testTotalRecaudacion() {
	    SistemaVentaEntradas sistema = new SistemaVentaEntradas();

	    sistema.añadirSala(1, "Titanic");
	    sistema.comprarEntrada(1, 5); 
	    sistema.comprarEntrada(1, 15); 
	    sistema.añadirSala(2, "Avatar");
	    sistema.comprarEntrada(2, 10); 

	    assertEquals(18.0, sistema.totalRecaudacion(), 0.01); 

	    sistema.vaciarCine(); 
}
	
	@Test
	public void testCalcularPrecioEntrada() {
		sistema = new SistemaVentaEntradas();
		assertEquals(10.0, sistema.calcularPrecioEntrada(5), 0.01);
		assertEquals(8.0, sistema.calcularPrecioEntrada(15), 0.01);
		assertEquals(5.0, sistema.calcularPrecioEntrada(25), 0.01);

		sistema.vaciarCine();
	}

	@Test
	public void testVaciarCine() {
		sistema = new SistemaVentaEntradas();
		sistema.añadirSala(1, "Titanic");
		sistema.comprarEntrada(1, 5);
		sistema.comprarEntrada(1, 15);
		sistema.añadirSala(2, "Avatar");
		sistema.comprarEntrada(2, 10);
		sistema.vaciarCine();
		assertEquals(0, sistema.getSalas().size());
		assertEquals(0, sistema.getEntradas().size());

		sistema.vaciarCine();
	}

}