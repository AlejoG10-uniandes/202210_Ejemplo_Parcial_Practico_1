package co.edu.uniandes.dse.parcial1.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uniandes.dse.parcial1.dto.ProductoDTO;
import co.edu.uniandes.dse.parcial1.entities.ProductoEntity;
import co.edu.uniandes.dse.parcial1.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.parcial1.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcial1.services.ProductoService;

@RestController
@RequestMapping("/productos")
public class ProductoController {
	
	@Autowired
    private ProductoService productoService;

    @Autowired
    private ModelMapper modelMapper;
    
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ProductoDTO create(@RequestBody ProductoDTO productoDTO) throws IllegalOperationException, EntityNotFoundException {
	    ProductoEntity productoEntity = productoService.createProducto(modelMapper.map(productoDTO, ProductoEntity.class));
	    return modelMapper.map(productoEntity, ProductoDTO.class);
    }
}
