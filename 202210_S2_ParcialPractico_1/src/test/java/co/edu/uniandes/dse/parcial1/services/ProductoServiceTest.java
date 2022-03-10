package co.edu.uniandes.dse.parcial1.services;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import co.edu.uniandes.dse.parcial1.entities.ProductoEntity;
import co.edu.uniandes.dse.parcial1.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.parcial1.exceptions.IllegalOperationException;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Transactional
@Import(ProductoService.class)
class ProductoServiceTest {

	@Autowired
	private ProductoService productoService;
	
	@Autowired
	private TestEntityManager entityManager;
	
	private PodamFactory factory = new PodamFactoryImpl();
	
	private List<ProductoEntity> productos = new ArrayList<>();
	
	@BeforeEach
	void setUp() throws Exception {
		clearData();
		insertData();
	}
	
	private void clearData() {
        entityManager.getEntityManager().createQuery("delete from ProductoEntity").executeUpdate();
	}
	
	private void insertData() {
        for (int i = 0; i < 3; i++) {
               	ProductoEntity ProductoEntity = factory.manufacturePojo(ProductoEntity.class);
                entityManager.persist(ProductoEntity);
                productos.add(ProductoEntity);
        }
	}

    @Test
    void testCreateProducto() throws EntityNotFoundException, IllegalOperationException {
		ProductoEntity newEntity = productos.get(0);
		ProductoEntity result = productoService.createProducto(newEntity);
        assertNotNull(result);
        ProductoEntity entity = entityManager.find(ProductoEntity.class, result.getId());
        assertEquals(newEntity.getId(), entity.getId());
        assertEquals(newEntity.getNombre(), entity.getNombre());
        assertEquals(newEntity.getDescripcion(), entity.getDescripcion());
        assertEquals(newEntity.getPrecio(), entity.getPrecio());
    }

    @Test
    void testCreateProductoWithInvalidPrice() {
        assertThrows(IllegalOperationException.class, () -> {
    		ProductoEntity newEntity = productos.get(0);
            newEntity.setPrecio(5000.00); // menor a 10000 = IllegalOperation
            productoService.createProducto(newEntity);
        });
    }
}
