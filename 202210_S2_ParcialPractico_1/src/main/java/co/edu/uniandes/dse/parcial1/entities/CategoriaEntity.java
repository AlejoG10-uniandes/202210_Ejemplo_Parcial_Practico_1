package co.edu.uniandes.dse.parcial1.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;

import lombok.Getter;
import lombok.Setter;
import uk.co.jemos.podam.common.PodamExclude;

@Entity
@Getter
@Setter
public class CategoriaEntity extends BaseEntity {
	
	private String nombre;
	
	@PodamExclude
	@ManyToMany(
		fetch = FetchType.LAZY
	)
	private List<ProductoEntity> productos;
}
