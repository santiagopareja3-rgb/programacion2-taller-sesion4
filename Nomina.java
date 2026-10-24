public class Nomina {
    public static void main(String[] args) {
        System.out.println("=== 1. Creación de empleados ===");
        Docente d1 = new Docente("1111111111", "Ana Gómez", "Sistemas", 60, 35000);
        Administrativo a1 = new Administrativo("2222222222", "Carlos Ruiz", "Secretaría", 1800000);
        System.out.println("Docente y administrativo creados.\n");

        System.out.println("=== 2. Resumen de ambos ===");
        System.out.println(d1.resumen());
        System.out.println(a1.resumen() + "\n");

        System.out.println("=== 3. Pago mensual ===");
        System.out.println("Docente: $" + String.format("%,.0f", d1.calcularPagoMensual()));
        System.out.println("Administrativo: $" + String.format("%,.0f", a1.calcularPagoMensual()) + "\n");

        System.out.println("=== 4. Vacaciones válidas (5 días) ===");
        a1.tomarVacaciones(5);
        System.out.println();

        System.out.println("=== 5. Intento de tomar más días de los disponibles (20) ===");
        a1.tomarVacaciones(20);
        System.out.println();

        System.out.println("=== 6. Resumen final del administrativo ===");
        System.out.println(a1.resumen());
    }
}