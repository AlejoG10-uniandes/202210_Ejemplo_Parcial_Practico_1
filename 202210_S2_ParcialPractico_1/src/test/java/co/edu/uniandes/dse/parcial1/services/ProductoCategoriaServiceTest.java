package co.edu.uniandes.dse.parcial1.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import co.edu.uniandes.dse.parcial1.entities.CategoriaEntity;
import co.edu.uniandes.dse.parcial1.entities.ProductoEntity;
import co.edu.uniandes.dse.parcial1.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.parcial1.exceptions.IllegalOperationException;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Transactional
@Import(ProductoCategoriaService.class)
class ProductoCategoriaServiceTest {

	@Autowired
	private ProductoCategoriaService pcService;
	
	@Autowired
	private TestEntityManager entityManager;

	private PodamFactory factory = new PodamFactoryImpl();
	
	private ProductoEntity producto = new ProductoEntity();

	
	@BeforeEach
	void setUp() {
		clearData();
		insertData();
	}
	
	private void clearData() {
		entityManager.getEntityManager().createQuery("delete from GeneroEntity").executeUpdate();
		entityManager.getEntityManager().createQuery("delete from GrupoMusicalEntity").executeUpdate();
	}

	private void insertData() {
		producto = factory.manufacturePojo(ProductoEntity.class);
		entityManager.persist(producto);

		for (int i = 0; i < 3; i++) {
			CategoriaEntity entity = factory.manufacturePojo(CategoriaEntity.class);
			entityManager.persist(entity);
			producto.getCategorias().add(entity);
		}
	}
	
	@Test
	void testAddCategoria() throws EntityNotFoundException, IllegalOperationException {
		ProductoEntity newProducto = factory.manufacturePojo(ProductoEntity.class);
		entityManager.persist(newProducto);
		
		CategoriaEntity newCategoria = factory.manufacturePojo(CategoriaEntity.class);
		entityManager.persist(newCategoria);
		
		CategoriaEntity lastCategoria = factory.manufacturePojo(CategoriaEntity.class);
		lastCategoria.setId(newCategoria.getId());
		entityManager.persist(newCategoria);
		
		pcService.addCategoria(newCategoria.getId(), newProducto.getId());
		assertEquals(newCategoria.getId(), lastCategoria.getId());
		assertEquals(newCategoria.getNombre(), lastCategoria.getNombre());
	}

}
