package dao.xml;

import org.basex.core.Context;
import org.basex.core.cmd.XQuery;

public class ProductoXMLDAO {

    private static final String DB_NAME = "productos";

    /* ======================
       OPERACIONES CRUD XML
       ====================== */

    public void modificarElementoPorId(String id, String elemento, String nuevoValor) {
        Context context = new Context();
            try {
            String xquery = """
                replace value of node
                /productos/producto[@id='%s']/%s
                with '%s'
            """.formatted(id, elemento, nuevoValor);

            new XQuery(xquery).execute(context);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void eliminarProductoPorId(String id) {
        Context context = new Context();
        try {
            String xquery = """
                delete node /productos/producto[@id='%s']
            """.formatted(id);

            new XQuery(xquery).execute(context);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* ======================
       CONSULTAS XQUERY
       ====================== */

    // Consulta 1
    public String productosOrdenadosPorNombre() {
        String xquery = """
            for $p in /productos/producto
            order by $p/nombre
            return concat(
                $p/@id, " | ",
                $p/nombre, " | ",
                $p/precio, " | ",
                $p/disponibilidad, " | ",
                $p/categoria
            )
        """;
        return ejecutarConsulta(xquery);
    }

    // Consulta 2
    public String productosDisponibilidadMayorQue(int x) {
        String xquery = """
            for $p in /productos/producto[disponibilidad > %d]
            return concat(
                $p/@id, " | ",
                $p/nombre, " | ",
                $p/precio, " | ",
                $p/disponibilidad, " | ",
                $p/categoria
            )
        """.formatted(x);
        return ejecutarConsulta(xquery);
    }

    // Consulta 3
    public String productoMasCaroPorCategoria() {
        String xquery = """
            for $c in distinct-values(/productos/producto/categoria)
            let $p := /productos/producto[categoria = $c]
                       order by precio descending
            return concat(
                $c, " | ",
                $p[1]/nombre, " | ",
                $p[1]/precio
            )
        """;
        return ejecutarConsulta(xquery);
    }

    // Consulta 4
    public String buscarPorDescripcion(String subcadena) {
        String xquery = """
            for $p in /productos/producto[contains(descripcion, '%s')]
            order by $p/fabricante descending
            return concat(
                $p/nombre, " | ",
                $p/fabricante
            )
        """.formatted(subcadena);
        return ejecutarConsulta(xquery);
    }

    // Consulta 5
    public String totalPorCategoria() {
        String xquery = """
            let $total := sum(/productos/producto/disponibilidad)
            for $c in distinct-values(/productos/producto/categoria)
            let $cantidad := sum(/productos/producto[categoria=$c]/disponibilidad)
            let $porcentaje := ($cantidad div $total) * 100
            return concat(
                $c, " | ",
                $cantidad, " | ",
                format-number($porcentaje, '0.00'), "%"
            )
        """;
        return ejecutarConsulta(xquery);
    }

    /* ======================
       MÉTODO AUXILIAR
       ====================== */

    private String ejecutarConsulta(String xquery) {
        Context context = new Context();
        try {
            return new XQuery(xquery).execute(context);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
