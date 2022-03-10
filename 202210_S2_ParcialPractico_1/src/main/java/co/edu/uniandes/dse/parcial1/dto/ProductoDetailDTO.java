package co.edu.uniandes.dse.parcial1.dto;


import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductoDetailDTO extends ProductoDTO {
	
	private List<CategoriaDTO> categorias = new ArrayList<>();
}
