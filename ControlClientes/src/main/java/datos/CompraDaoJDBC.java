
package datos;

import dominio.Compra;
import java.sql.*;
import java.util.*;

public class CompraDaoJDBC {
    
     private static final String SQL_SELECT = "SELECT idcompra, idcliente, monto "
            + " FROM compra";

    private static final String SQL_SELECT_BY_ID = "SELECT idcompra, idcliente, monto "
            + " FROM compra WHERE idcompra = ?";

    private static final String SQL_INSERT = "INSERT INTO compra(idcliente, monto) "
            + " VALUES(?, ?)";

    private static final String SQL_UPDATE = "UPDATE compra "
            + " SET idcliente=?, monto=? WHERE idcompra=?";

    private static final String SQL_DELETE = "DELETE FROM compra WHERE idcompra = ?";
    
    
    //TODO: trae todas las compras realizadas
    public List<Compra> listar() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Compra compra = null;
        List<Compra> compras = new ArrayList<>();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int idCompra = rs.getInt("idcompra");
                int idCliente = rs.getInt("idcliente");
                double monto = rs.getDouble("monto");

                compra = new Compra(idCompra, idCliente, monto);
                compras.add(compra);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return compras;
    }
    
    //TODO: obtener Compra por id 
    public Compra encontrar(Compra compra) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
            stmt.setInt(1, compra.getIdCliente());
            rs = stmt.executeQuery();
            rs.absolute(1);//nos posicionamos en el primer registro devuelto

            int idCompra = rs.getInt("idcompra");
            int idCliente = rs.getInt("idcliente");
            double monto = rs.getDouble("monto");
            
            compra.setIdCompra(idCompra);
            compra.setIdCliente(idCliente);
            compra.setMonto(monto);

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return compra;
    }
    
    //TODO: generar nueva Compra
    public int insertar(Compra compra) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setInt(1, compra.getIdCliente());
            stmt.setDouble(2, compra.getMonto());

            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }
    
    //TODO: actualizar una Compra
    public int actualizar(Compra compra) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setInt(1, compra.getIdCliente());
            stmt.setDouble(2, compra.getMonto());
            stmt.setInt(3, compra.getIdCompra());

            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }
    
    
    //TODO: eliminar una Compra
     public int eliminar(Compra compra) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, compra.getIdCompra());

            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }
}
