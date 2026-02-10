package modelo.enums;



public enum MenuEnum {

    SALIR("Salir"),
    MODIFICAR_VALOR_XML("Modificar el valor de un elemento de un XML según un ID."),
    ELIMINAR_PRODUCTO("Eliminar un producto según su ID"),
    OBTENER_POR_ORDEN_ALFABETICO("Obtener todos los productos por orden alfabético del nombre (se mostrarán los siguientes campos: id, nombre, precio, disponibilidad y categoria)"),
    LISTAR_DISPONIVILIDAD_MAYOR_A_X("Listar productos con una disponibilidad mayor a X unidades (se mostrarán los siguientes campos: id, nombre, precio, disponibilidad y categoria)."),
    MOSTRAR_MAS_CARO("Mostrar la categoría, el nombre y el precio del producto más caro para cada categoría. En el caso de haber varios se devolverá el de la primera posición.Consulta 4: Mostrar el nombre de los productos y su fabricante para aquellos productos cuya descripción incluya una subcadena. Se deberá mostrar la información ordenada según el nombre del fabricante de forma inversa al alfabeto."),
    MOSTRAR_TOTAL_PRODUCTOS_POR_CATEGORIA("Mostrar la cantidad total de productos en cada categoría (teniendo en cuenta el elemento disponibilidad) y calcular el porcentaje que representa respecto al total de productos."),
    CREAR_CLIENTE("Crear un nuevo cliente (no podrá haber email repetidos)."),
    IDENTIFICAR_CLIENTE_PO_EMAIL("Identificar cliente según el email. Dado el email se obtendrá el ID del cliente de forma que las siguientes consultas se harán sobre ese cliente. Para cambiar de cliente se tendrá que volver a seleccionar esta opción."),
    BORRAR_CLIENTE("Borrar un cliente."),
    MODIFICAR_VALOR_INFO_CLIENTE("Modificar el valor de un campo de la información del cliente."),
    AÑADIR_PRODUCTO_AL_CARRITO_DEL_CLIENTE("Añadir producto al carrito del cliente. Se pedirá: id del producto y cantidad, así como si se desea seguir introduciendo más productos."),
    MOSTRAR_CARRITO_DEL_CLIENTE("Mostrar el carrito del cliente. Se mostrarán los datos del carrito y el precio total."),
    MOSTRAR_PEDIDOS_DEL_CLIENTE("Mostrar pedidos del cliente."),
    PAGAR_CARRITO_DEL_CLIENTE("Pagar el carrito de un cliente: se mostrará el carrito junto con una orden de confirmación. Si la orden es positiva se pasarán todos los productos a formar parte de un nuevo pedido."),
    CALCULAR_TOTAL_CARRITOS_Y_LISTAR("Teniendo en cuenta todos los clientes, calcular el total de la compra para cada carrito y listar los resultados ordenados por el total de forma ascendente. (No es necesario tener en cuenta la multiplicación de precio_unitario * cantidad con sumar los precio_unitario es suficiente)."),
    CALCULAR_TOTAL_GASTOS_Y_LISTAR("Teniendo en cuenta todos los clientes, obtener el total gastado por cada cliente en todos sus pedidos.");


    private final String TEXTO;

    MenuEnum(String TEXTO) {
        this.TEXTO = TEXTO;
    }

    public String getTexto() {
        return TEXTO;
    }
}
