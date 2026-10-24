public class Administrativo extends Empleado {
    private double salarioBasico;
    private int diasVacaciones;

    public Administrativo(String cedula, String nombre, String dependencia,
                          double salarioBasico) {
        super(cedula, nombre, dependencia);
        this.salarioBasico = salarioBasico;
        this.diasVacaciones = 15;
    }

    public boolean tomarVacaciones(int dias) {
        if (dias <= 0) {
            System.out.println("Rechazado: los días deben ser mayores a cero.");
            return false;
        }
        if (dias > diasVacaciones) {
            System.out.println("Rechazado: solicitó " + dias + " días y solo le quedan "
                    + diasVacaciones + ".");
            return false;
        }
        diasVacaciones -= dias;
        System.out.println("Vacaciones aprobadas: " + dias + " días. Le quedan "
                + diasVacaciones + ".");
        return true;
    }

    @Override
    public double calcularPagoMensual() {
        return salarioBasico;
    }

    @Override
    public String resumen() {
        return super.resumen() + " | Salario básico: $" + String.format("%,.0f", salarioBasico)
                + " | Días de vacaciones disponibles: " + diasVacaciones;
    }
}