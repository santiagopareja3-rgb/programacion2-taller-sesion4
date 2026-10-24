public class Docente extends Empleado {
    private int horasCatedra;
    private double valorHora;

    public Docente(String cedula, String nombre, String dependencia,
                   int horasCatedra, double valorHora) {
        super(cedula, nombre, dependencia);
        this.horasCatedra = horasCatedra;
        this.valorHora = valorHora;
    }

    @Override
    public double calcularPagoMensual() {
        return horasCatedra * valorHora;
    }

    @Override
    public String resumen() {
        return super.resumen() + " | Horas cátedra: " + horasCatedra
                + " | Valor hora: $" + String.format("%,.0f", valorHora);
    }
}