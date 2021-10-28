<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="es_MX"/>
<div class="modal fade" id="agregarCompraModal">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header bg-info text-white">
                <h5 class="modal-title">Agregar Compra</h5> 
                <button class="close" data-dismiss="modal">
                    <span>&times;</span>
                </button>
            </div>
            
            <form action="${pageContext.request.contextPath}/ServletControladorCompras?accionCompra=insertarCompra"
                  method="POST" class="was-validated">
                
                <div class="modal-body">
                    <div class="form-group">
                        <label for="idCliente">ID Cliente</label>
                        
                            <select class="form-control" name="idCliente" class="default" required>
                                <c:forEach var="cliente" items="${clientes}">
                                <option value="${cliente.idCliente}">${cliente.idCliente}</option>
                                </c:forEach>
                            </select>
                    </div>
                    
                    <div class="form-group">
                        <label for="monto">Monto</label>
                        <input type="number" class="form-control" name="monto" required step="any">
                    </div>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-primary" type="submit">Guardar</button>
                </div>    
            </form>
        </div>
    </div>
</div>
