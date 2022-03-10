package co.edu.uniandes.dse.parcial1.services;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import co.edu.uniandes.dse.parcial1.entities.CategoriaEntity;
import co.edu.uniandes.dse.parcial1.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.parcial1.exceptions.IllegalOperationException;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

class CategoriaServiceTest {

	@Autowired
	private CategoriaService categoriaService;
	
	@Autowired
	private TestEntityManager entityManager;
	
	private PodamFactory factory = new PodamFactoryImpl();
	
	private List<CategoriaEntity> categorias = new ArrayList<>();
	
	@BeforeEach
	void setUp() throws Exception {
		clearData();
		insertData();
	}
	
	private void clearData() {
        entityManager.getEntityManager().createQuery("delete from CategoriaEntity").executeUpdate();
	}
	
	private void insertData() {
        for (int i = 0; i < 3; i++) {
           	CategoriaEntity CategoriaEntity = factory.manufacturePojo(CategoriaEntity.class);
            entityManager.persist(CategoriaEntity);
            categorias.add(CategoriaEntity);
        }
	}

    @Test
    void testCreateCategoria() throws EntityNotFoundException, IllegalOperationException {
		CategoriaEntity newEntity = categorias.get(0);
		CategoriaEntity result = categoriaService.createCategoria(newEntity);
        assertNotNull(result);
        CategoriaEntity entity = entityManager.find(CategoriaEntity.class, result.getId());
        assertEquals(newEntity.getId(), entity.getId());
        assertEquals(newEntity.getNombre(), entity.getNombre());
    }
}
