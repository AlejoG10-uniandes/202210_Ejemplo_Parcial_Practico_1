package co.edu.uniandes.dse.parcial1.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import co.edu.uniandes.dse.parcial1.entities.ProductoEntity;
import co.edu.uniandes.dse.parcial1.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.parcial1.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcial1.repositories.ProductoRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProductoService {
	
	@Autowired
	ProductoRepository productoRepository;
	
	@Transactional
	public ProductoEntity createProducto(ProductoEntity productoEntity) throws EntityNotFoundException, IllegalOperationException {
		log.info("Inicia proceso de creación del producto");
		
		if (!esValido(productoEntity))
			throw new IllegalOperationException("El precio del producto debe ser mayor a 10.000");
		
        log.info("Termina proceso de creación del producto");
        return productoRepository.save(productoEntity);
	}
	
	public boolean esValido(ProductoEntity productoEntity) {
		return (productoEntity.getPrecio() >= 10000);
	}
}
