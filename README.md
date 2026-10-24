# Taller Sesión 4 - Sistema de Nómina del INTEP

**Estudiante:** Santiago Pareja Quintero  
**Asignatura:** Programación II  
**Repositorio:** programacion2-taller-sesion4  

---

## Parte A. Comprensión de conceptos

### 1. Prueba del «es un»
* **Frases válidas:**
  * "Un `Docente` **es un** `Empleado`."
  * "Un `Administrativo` **es un** `Empleado`."
* **Frase incorrecta:** "Un `Empleado` **es un** `Docente`."
* **Explicación:** La herencia en POO se modela de lo general a lo específico. No todo empleado del INTEP es un docente de hora cátedra (puede ser administrativo o directivo). Si invirtiéramos la jerarquía, forzaríamos a todos los empleados de la institución a registrar atributos específicos de la docencia como las horas trabajadas.

### 2. Uso y posición de `super(...)`
* **Función en el código:** En el constructor de `Docente` escribí:

  ```java
  super(cedula, nombre, dependencia);
  ```

  Esa línea llama al constructor de `Empleado` y le pasa los tres datos para que él asigne `cedula`, `nombre` y `dependencia`. Ahora `Docente` no necesita repetir esas asignaciones, porque reutiliza las del padre.

* **¿Por qué va en la primera línea?:** Porque Java exige que la parte del objeto que viene del padre se construya antes que la de la hija. Si no escribo `super(...)`, Java intenta llamar a un constructor vacío `super()` del padre. Como `Empleado` solo tiene el constructor de tres parámetros, el código no compilaría.

### 3. Atributos `protected` vs `private`
* **Si se usara `private`:** En `Empleado` los declaré así:

  ```java
  protected String cedula;
  protected String nombre;
  protected String dependencia;
  ```

  Si estuvieran en `private`, las clases hijas (`Docente` y `Administrativo`) no podrían acceder directamente a `cedula`, `nombre` o `dependencia`, y cualquier línea de una hija que los use directamente daría error de compilación.
* **Alternativa:** Declarar los atributos como `private` en `Empleado` y exponer métodos de acceso públicos/protegidos (`getters` / `setters` como `getCedula()`), manteniendo un encapsulamiento más estricto sin usar `protected`.

### 4. Anotación `@Override`
* **Propósito:** Le indica formalmente al compilador que la intención del método es sobrescribir un comportamiento heredado de la clase padre.
* **Error que evita:** Previene errores de sintaxis por errores tipográficos en el nombre del método o firmas de parámetros. Si escribes por error `calcularPagos()`, el compilador marcará un error de inmediato en lugar de crearlo silenciosamente como un método nuevo.

### 5. Ventajas de usar `super.resumen()`
* **Ganancia en el programa:** Evita la duplicación de código y facilita el mantenimiento (principio DRY). Si la representación general de un empleado cambia en el futuro, solo se modifica la clase `Empleado` y todas las clases hijas reflejan el cambio automáticamente.

### 6. Sobrescribir (Override) vs Sobrecargar (Overload)
* **Sobrescribir (`Override`):** Ocurre cuando una clase hija redefine un método heredado manteniendo la misma firma (nombre, parámetros y tipo de retorno). *Ejemplo:* Redefinir `calcularPagoMensual()` en `Docente` y `Administrativo`.
* **Sobrecargar (`Overload`):** Ocurre cuando en una misma clase se definen varios métodos con el mismo nombre pero diferente firma (distinta cantidad o tipo de parámetros). *Ejemplo:* Definir dos constructores en `Empleado`, uno con argumentos y otro sin parámetros.

---

## Parte B. Diseño de la jerarquía

### B1. Tabla de reparto

| Dato o comportamiento | ¿Dónde va? | Justificación |
| :--- | :--- | :--- |
| `cedula`, `nombre`, `dependencia` | `Empleado` | Atributos compartidos por todos los empleados de la institución. |
| `horasCatedra`, `valorHora` | `Docente` | Datos exclusivos para el cálculo del pago a docentes por hora cátedra. |
| `salarioBasico`, `diasVacaciones` | `Administrativo` | Atributos propios y exclusivos del personal administrativo. |
| `resumen()` | Padre y sobrescrito | Método general en `Empleado`, redefinido en las hijas para incluir sus datos específicos. |
| `calcularPagoMensual()` | Padre y sobrescrito | Definido en la clase padre con un retorno base (0.0) y redefinido en cada clase hija según su lógica de pago. |
| `tomarVacaciones(int dias)` | `Administrativo` | Comportamiento exclusivo del personal administrativo para el control de sus días disponibles. |