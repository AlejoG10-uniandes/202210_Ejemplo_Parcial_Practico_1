package co.edu.uniandes.dse.parcial1.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductoDTO {
	
	private Long id;
	
	private String nombre;
	
	private String descripcion;
	
	private Double precio;
}
