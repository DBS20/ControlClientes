
package dominio;


public class Compra extends Cliente{
    private int idCompra;
    private int idCliente;
    private double monto;

    public Compra() {
    }

    //inner join
    public Compra(int idCompra, int idCliente, String nombre, double saldo,double monto) {
        super(idCliente, nombre, saldo);
        this.idCompra = idCompra;
        this.monto = monto;
    }

    //inner join
    public Compra(int idCompra, String nombre, double saldo, double monto) {
        super(nombre, saldo);
        this.idCompra = idCompra;
        this.monto = monto;
    }

    
    
    public Compra(int idCompra) {
        this.idCompra = idCompra;
    }

    public Compra(int idCliente, double monto) {
        this.idCliente = idCliente;
        this.monto = monto;
    }

    public Compra(int idCompra, int idCliente, double monto) {
        this.idCompra = idCompra;
        this.idCliente = idCliente;
        this.monto = monto;
    }

    public int getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Compra{idCompra=").append(idCompra);
        sb.append(", idCliente=").append(idCliente);
        sb.append(", monto=").append(monto);
        sb.append('}');
        return sb.toString();
    }
    
    
}
