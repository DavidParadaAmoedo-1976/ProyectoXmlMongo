package modelo.enums;

public enum MenuEnum {

    SALIR("Salir"),
    MODIFICAR_VALOR_XML("Modificar el valor de un elemento de un XML según un ID."),
    ELIMINAR_PRODUCTO("Eliminar un producto según su ID"),
    OBTENER_POR_ORDEN_ALFABETICO("Obtener todos los productos por orden alfabético del nombre (se mostrarán los siguientes campos: id, nombre, precio, disponibilidad y categoria)"),
    LISTAR_DISPONIVILIDAD_MAYOR_A_X("Listar productos con una disponibilidad mayor a X unidades (se mostrarán los siguientes campos: id, nombre, precio, disponibilidad y categoria)."),
    MOSTRAR_MAS_CARO("Mostrar la categoría, el nombre y el precio del producto más caro para cada categoría. En el caso de haber varios se devolverá el de la primera posición.Consulta 4: Mostrar el nombre de los productos y su fabricante para aquellos productos cuya descripción incluya una subcadena. Se deberá mostrar la información ordenada según el nombre del fabricante de forma inversa al alfabeto."),
    MOSTRAR_TOTAL_PRODUCTOS_POR_CATEGORIA("Mostrar la cantidad total de productos en cada categoría (teniendo en cuenta el elemento disponibilidad) y calcular el porcentaje que representa respecto al total de productos.");


    private final String TEXTO;

    MenuEnum(String TEXTO) {
        this.TEXTO = TEXTO;
    }

    public String getTexto() {
        return TEXTO;
    }
}
