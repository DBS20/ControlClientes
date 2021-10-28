/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import datos.*;
import dominio.*;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/ServletControladorCompras")
public class ServletControladorCompras extends HttpServlet{
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        

        
        //TODO: acciones de Editar y Eliminar Compra 
        String accionCompra = request.getParameter("accionCompra");
        if (accionCompra != null) {
            switch (accionCompra) {
                case "editarCompra":
                    this.editarCompra(request, response);
                    break;
                case "eliminarCompra":
                    this.eliminarCompra(request, response);
                    break;
                default:
                    this.accionRetornarPaginaCompras(request, response);
            }
        } else {
            this.accionRetornarPaginaCompras(request, response);
        }
    }

    //TODO: redireccionar a pagina principal de Compras
    private void accionRetornarPaginaCompras(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        List<Compra> compras = new CompraDaoJDBC().listar();
        List<Cliente> clientes = new ClienteDaoJDBC().listar();
        List<Compra> compras2 = new CompraDaoJDBC().innerJoin();
        System.out.println("compras = " + compras);
        HttpSession sesion = request.getSession();
        sesion.setAttribute("compras", compras);
        sesion.setAttribute("clientes", clientes);
        sesion.setAttribute("compras2", compras2);
        sesion.setAttribute("totalCompras", compras.size());
        sesion.setAttribute("montoTotal", this.calcularMontoTotal(compras));
        sesion.setAttribute("montoMax", this.calcularMontoMax(compras));
        sesion.setAttribute("montoMin", this.calcularMontoMin(compras));
        request.getRequestDispatcher("compras.jsp").forward(request, response);
        response.sendRedirect("compras.jsp");
    }
    

    //TODO: calcular monto total de todos los clientes
    private double calcularMontoTotal(List<Compra> compras) {
        double montoTotal = 0;
        for (Compra compra : compras) {
            montoTotal += compra.getMonto();
        }
        return montoTotal;
    }
    
    //TODO: calcular monto máximo de todos los clientes
    private double calcularMontoMax(List<Compra> compras) {
        double montoMax = 0;
        for (Compra compra : compras) {
            if(compra.getMonto() > montoMax)
            {
                montoMax = compra.getMonto();
            }
        }
        return montoMax;
    }
    
    //TODO: calcular monto máximo de todos los clientes
    private double calcularMontoMin(List<Compra> compras) {
        
        double montoMin = 0;
        for (Compra compra : compras) {
           
            if(montoMin == 0 || montoMin>compra.getMonto())
            {
                montoMin=compra.getMonto();
            }
            
        }
        return montoMin;
    }
    
  
    //TODO: editar Compra de un Cliente(idCompra - en consideración)
    private void editarCompra(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //recuperamos el idCompra
        int idCompra = Integer.parseInt(request.getParameter("idCompra"));
        List<Compra> compras = new CompraDaoJDBC().listar();
        
        Compra compra = new Compra();
        
        for (Compra com : compras) {
            if(com.getIdCompra()== idCompra)
            {
                compra.setIdCompra(com.getIdCompra());
                compra.setIdCliente(com.getIdCliente());
                compra.setMonto(com.getMonto());
            }
        }
        
        request.setAttribute("compra", compra);
        String jspEditarCompra = "/WEB-INF/paginas/compra/editarCompra.jsp";
        // se crea ruta para navegar y que despacha el servlet
        request.getRequestDispatcher(jspEditarCompra).forward(request, response);
    }
    
    
    

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        //TODO: acciones de Insertar y Modificar Compra
        String accionCompra = request.getParameter("accionCompra");
        if (accionCompra != null) {
            switch (accionCompra) {
                case "insertarCompra":
                    this.insertarCompra(request, response);
                    break;
                case "modificarCompra":
                    this.modificarCompra(request, response);
                    break;
                default:
                    this.accionRetornarPaginaCompras(request, response);
            }
        } else {
            this.accionRetornarPaginaCompras(request, response);
        }
    }

    
    //TODO: agregar nueva Compra
    private void insertarCompra(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //recuperamos los valores del formulario agregarCliente
        int idCliente =0;
        String idcliente = request.getParameter("idCliente");
        idCliente = Integer.parseInt(idcliente);
        
        double monto = 0;
        String montoString = request.getParameter("monto");
        if (montoString != null && !"".equals(montoString)) {
            monto = Double.parseDouble(montoString);
        }

        //Creamos el objeto de compra (modelo)
        Compra compra = new Compra(idCliente,monto);

        //Insertamos el nuevo objeto en la base de datos
        int compraAgregada = new CompraDaoJDBC().insertar(compra);
        
        System.out.println("Compra agregada = " + compraAgregada);

        //Redirigimos hacia accion RetornarPaginaCompra
        this.accionRetornarPaginaCompras(request, response);
    }
    
    
    //TODO: modificación de la Compra
    private void modificarCompra(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //recuperamos los valores del formulario editarCliente
     
        
        int idCompra = Integer.parseInt(request.getParameter("idCompra"));
        int idCliente = Integer.parseInt(request.getParameter("idCliente"));
        double monto = 0;
        String montoString = request.getParameter("monto");
        if (montoString != null && !"".equals(montoString)) {
            monto = Double.parseDouble(montoString);
        }

        //Creamos el objeto de compra (modelo)
        Compra compra = new Compra(idCompra,idCliente,monto);

        //Modificar el  objeto en la base de datos
        int compraModificada = new CompraDaoJDBC().actualizar(compra);
        System.out.println("Compra modificada = " + compraModificada);

        //Redirigimos hacia accion RetornarPaginaCompra
        this.accionRetornarPaginaCompras(request, response);
    }
    
    
    //TODO: eliminar Compra
    private void eliminarCompra(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //recuperamos los valores del formulario editarCliente
        int idCompra = Integer.parseInt(request.getParameter("idCompra"));
     

        //Creamos el objeto de cliente (modelo)
        Compra compra = new Compra(idCompra);

        //Eliminamos el  objeto en la base de datos
        int compraEliminada = new CompraDaoJDBC().eliminar(compra);
        System.out.println("Compra eliminada = " + compraEliminada);

        //Redirigimos hacia accion RetornarPaginaCompra
        this.accionRetornarPaginaCompras(request, response);
    }

        
        
    
    
}
