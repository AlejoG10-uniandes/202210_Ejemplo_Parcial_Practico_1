package co.edu.uniandes.dse.parcial1.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uniandes.dse.parcial1.entities.CategoriaEntity;
import co.edu.uniandes.dse.parcial1.entities.ProductoEntity;
import co.edu.uniandes.dse.parcial1.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.parcial1.repositories.CategoriaRepository;
import co.edu.uniandes.dse.parcial1.repositories.ProductoRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProductoCategoriaService {
	@Autowired
    private ProductoRepository productoRepository;
	
	@Autowired
    private CategoriaRepository categoriaRepository;
    
    @Transactional
    public CategoriaEntity addCategoria(Long productoId, Long categoriaId) throws EntityNotFoundException {
    	log.info("Inicia proceso de asociarle una categoria al producto con id = {0}", productoId);
    
        Optional<CategoriaEntity> categoriaEntity = categoriaRepository.findById(categoriaId);
        if (categoriaEntity.isEmpty())
                throw new EntityNotFoundException("La categoria con el id dado no existe");
        
        Optional<ProductoEntity> productoEntity = productoRepository.findById(productoId);
        if (productoEntity.isEmpty())
                throw new EntityNotFoundException("El producto con el id dado no existe");

        productoEntity.get().getCategorias().add(categoriaEntity.get());
        log.info("Termina proceso de asociarle una categoria al producto con id = {0}", productoId);
        return categoriaEntity.get();
    }
}
