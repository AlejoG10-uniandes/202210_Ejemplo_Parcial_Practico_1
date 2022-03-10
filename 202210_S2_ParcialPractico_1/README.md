# Parcial Práctico 1 - Sección 2

## Instrucciones

1. Haga un _fork_ de este repositorio
2. Clone el repositorio bifurcado en su máquina virtual (o en su equipo propio)
3. Abra el proyecto en Spring Tools (o en el editor de su preferencia)
4. Lea el enunciado completamente antes de iniciar

## Punto 1 (15%). Persistencia

Esta aplicación tiene el propósito de administrar una tienda en línea de alimentos orgánicos. Cada producto tiene un conjunto de categorías y cada categoría está asociada con un conjunto de productos.

(5%) Cree la entidad _ProductoEntity_ en la carpeta correspondiente. Un producto tiene un nombre, una descripción, un precio, un id de tipo _Long_ que representa su llave primaria y una lista de categorías.

(5%) Cree la entidad _CategoriaEntity_ en la carpeta correspondiente. Una categoría tiene un nombre, un id de tipo _Long_ que representa su llave primaria y una lista de productos.

(5%) Realice la implementación de la persistencia del producto y de la categoría.

## Punto 2 (15%). Lógica para producto y categoría

(4%) Cree la lógica para la entidad producto; para esto implemente la clase correspondiente y el método _createProducto_. Una regla de negocio que debe validarse es que el precio del producto debe ser un número positivo mayor o igual a 10.000.

(4%) Cree las pruebas unitarias para el método _createProducto_. Se deben crear al menos dos pruebas: una en la que la creación del producto se hace correctamente y otra donde se lanza una excepción cuando se crea un producto con un valor menor de 10.000.

(3.5%) Cree la lógica para la entidad categoría; para esto implemente la clase correspondiente y el método _createCategoria_ (no hay reglas de negocio asociadas a la creación de una categoría).

(3.5%) Cree la prueba unitaria para el método _createCategoria_.

## Punto 2 (20%). Lógica de la asociación

(10%) Cree la lógica para la asociación entre producto y categoría, particularmente para agregar un producto a una categoría; para esto implemente la clase correspondiente y el método _addCategoria_. Este método recibe como parámetros el id del producto y el id de la categoría. El método buscará el producto y la categoría y agregará el la categoría al producto. En caso de que el producto o la categoría no existan se deberá lanzar una excepción.

(10%) Cree las pruebas unitarias para el método _addCategoria_. Se deben crear al menos tres pruebas: una en la que la asociación entre el producto y la categoría se hace correctamente; otra donde el producto proporcionado no existe y otra donde la categoría proporcionada no existe.

## Bono (0.5 puntos). API

(20%) Cree la clase _ProductoDTO_ con los atributos correspondientes.

(20%) Cree la clase _ProductoController_

(60%) Implemente en el controlador el método _create_ para que llame al método de la lógica que crea un producto y retorne al usuario el producto creado con su nuevo id.

## Bono (0.5 puntos). Pruebas de Postman

### Crear un producto

(50%) Cree la siguiente prueba de integración en una colección y expórtela en la carpeta _collections_ del proyecto.

Se espera un resultado semejante a este ejemplo:

```
Method: POST
URL: http://localhost:8080/api/productos
Request body:
{
	"nombre": "Avena en hojuelas Mark",
	"descripcion": "La Avena en Hojuelas Mark es uno de los cereales más completos. La fibra, vitaminas, minerales y otros nutrientes te aportan una excelente nutrición",
    	"precio": 12300
}

Response Status: 201

Response body:
{
	"id": 1,
   	"nombre": "Avena en hojuelas Mark",
	"descripcion": "La Avena en Hojuelas Mark  es uno de los cereales más completos. La fibra, vitaminas, minerales y otros nutrientes te aportan una excelente nutrición",
    	"precio": 12300
}
```
### Crear un producto con uun precio inválido

(50%) Cree la siguiente prueba de integración en una colección y expórtela en la carpeta _collections_ del proyecto.

Se espera un resultado semejante a este ejemplo:

```
Method: POST
URL: http://localhost:8080/api/productos
Request body:
{
	"nombre": "Avena en hojuelas Mark",
	"descripcion": "La Avena en Hojuelas Mark es uno de los cereales más completos. La fibra, vitaminas, minerales y otros nutrientes te aportan una excelente nutrición",
    	"precio": 5300
}

Response Status: 400

Response body:
{
	"apiError": {
		"status": "PRECONDITION_FAILED",
		"timestamp": "10-03-2022 10:00:10",
		"message": "Price is not valid"
	}
}
```


## Entrega

1. Haga _commit_ y _push_ en su repositorio bifurcado
2. Haga un _release_ con la etiqueta 1.0.0 y el nombre _release-s1-p1_
3. Suba el _release_ como respuesta a la actividad de Bloque Neón
4. Luego de subir la actividad no haga ninguna modificación al repositorio. Cualquer modificación, por mínima que sea, anula automáticamente el parcial
