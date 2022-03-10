package co.edu.uniandes.dse.parcial1.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uniandes.dse.parcial1.entities.CategoriaEntity;
import co.edu.uniandes.dse.parcial1.repositories.CategoriaRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CategoriaService {
	
	@Autowired
	CategoriaRepository categoriaRepository;
	
	@Transactional
	public CategoriaEntity createCategoria(CategoriaEntity categoriaEntity) {
		log.info("Inicia proceso de creación de la categoria");		
        log.info("Termina proceso de creación de la categoria");
        return categoriaRepository.save(categoriaEntity);
	}
}
