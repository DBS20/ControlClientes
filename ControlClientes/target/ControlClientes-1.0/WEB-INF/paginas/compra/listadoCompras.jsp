<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="es_MX"/>
<section id="compras">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="card">
                    <div class="card-header">
                        <h4>Listado de Compras</h4>
                    </div>
                    <table class="table table-striped">
                        <thead class="thead-dark">
                            <tr>
                                <th>#</th>
                                <th>Id Cliente</th>
                                <th>Monto</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>                            
                            <!-- Iteramos cada elemento de la lista de clientes -->
                            <c:forEach var="compra" items="${compras}" varStatus="status" >
                                <tr>
                                    <td>${compra.idCompra}</td>
                                    <td>${compra.idCliente}</td>
                                    <%--Agregamos conversión de numero a moneda--%>
                                    <td> <fmt:formatNumber value="${compra.monto}" type="currency"/> </td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/ServletControladorCompras?accionCompra=editarCompra&idCompra=${compra.idCompra}"
                                           class="btn btn-secondary">
                                            <i class="fas fa-angle-double-right"></i> Editar
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <br><br>
        <div class="row">
            <!--Inicio Tarjetas para los totales-->
            <div class="col-md-6">
                <div class="card text-center bg-warning text-white mb-3">
                    <div class="card-body">
                        <h3>Monto Total</h3>
                        <h4 class="display-4">
                            <fmt:formatNumber value="${montoTotal}" type="currency" />
                        </h4>
                    </div>
                </div>    
            </div>
            <div class="col-md-6">
                <div class="card text-center bg-light text-dark mb-3">
                    <div class="card-body">
                        <h3>Total Compras</h3>
                        <h4 class="display-4">
                            <i class="fas fa-users"></i> ${totalCompras}
                        </h4>
                    </div>
                </div> 
            </div>       
            <!--Fin Tarjetas para los totales-->
        </div>
        <br><br>
         <div class="row">
            <div class="col-md-12">
                <div class="card">
                    <div class="card-header">
                        <h4>Listado de Compras/CLIENTES</h4>
                    </div>
                    <table class="table table-striped">
                        <thead class="thead-dark">
                            <tr>
                                <th>#</th>
                                <th>Nombre</th>
                                <th>Saldo</th>
                                <th>Monto</th>
                            </tr>
                        </thead>
                        <tbody>                            
                            <!-- Iteramos cada elemento de la lista de clientes -->
                            
                                <c:forEach var="compras2" items="${compras2}" >
                                <tr>
                                    <td>${compras2.idCompra}</td>
                                    <td>${compras2.nombre}</td>
                                    <td> <fmt:formatNumber value="${compras2.saldo}" type="currency"/> </td>
                                    <td><fmt:formatNumber value="${compras2.monto}" type="currency"/></td>
                                    
                                </tr>
                                </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</section>

<!-- Agregar compra MODAL -->
<jsp:include page="/WEB-INF/paginas/compra/agregarCompra.jsp"/>
