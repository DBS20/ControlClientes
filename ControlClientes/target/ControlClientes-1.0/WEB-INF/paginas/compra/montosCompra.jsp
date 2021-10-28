<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="es_MX"/>
<section id="actions" class="py-4 mb-4 bg-light">
    <div class="container">
        <div class="row">
            <div class="col-md-6">
                <div class="card text-center bg-danger text-white">
                    <div name="montoMax" class="card-body">
                      Monto Máximo: <fmt:formatNumber value="${montoMax}" type="currency" />
                    </div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="card text-center bg-success text-white">
                    <div name="montoMin" class="card-body">
                      Monto Mínimo: <fmt:formatNumber value="${montoMin}" type="currency" />
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>