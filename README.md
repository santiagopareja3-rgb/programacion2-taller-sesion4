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
* **Propósito:** Le indica al compilador que la intención del método es sobrescribir un comportamiento heredado de la clase padre. En `Docente` escribí:

  ```java
  @Override
  public double calcularPagoMensual() {
      return horasCatedra * valorHora;
  }
  ```

* **Error que evita:** Previene errores de tipeo en el nombre del método o en los parámetros. Si escribiera por error `calcularPagos()`, sin `@Override` Java lo compilaría sin quejarse como un método nuevo, y `d1.calcularPagoMensual()` seguiría usando el del padre y devolvería 0 en vez de $2.100.000. Con `@Override`, el compilador marca error de inmediato.

### 5. Ventajas de usar `super.resumen()`
* **Ganancia en el programa:** En `Docente` escribí:

  ```java
  @Override
  public String resumen() {
      return super.resumen() + " | Horas cátedra: " + horasCatedra
              + " | Valor hora: $" + String.format("%,.0f", valorHora);
  }
  ```

  Esto evita la duplicación de código y facilita el mantenimiento (principio DRY). Si la representación general de un empleado cambia en el futuro, solo se modifica la clase `Empleado` y todas las clases hijas reflejan el cambio automáticamente.
### 6. Sobrescribir (Override) vs Sobrecargar (Overload)
* **Sobrescribir (`Override`):** Ocurre cuando una clase hija redefine un método heredado manteniendo la misma firma (nombre, parámetros y tipo de retorno). *Ejemplo de mi código:* `calcularPagoMensual()` existe en `Empleado` (devuelve 0) y lo redefiní en `Docente`:

  ```java
  @Override
  public double calcularPagoMensual() {
      return horasCatedra * valorHora;
  }
  ```

  y también en `Administrativo`, donde devuelve `salarioBasico`.
* **Sobrecargar (`Overload`):** Ocurre cuando en una misma clase se definen varios métodos con el mismo nombre pero diferente firma (distinta cantidad o tipo de parámetros). *Ejemplo del curso (no está en mi proyecto):*

  ```java
  int sumar(int a, int b)
  double sumar(double a, double b)
  ```

  Mismo nombre, parámetros distintos, y Java elige cuál ejecutar según los argumentos.

---
### B1. Tabla de reparto

| Dato o comportamiento | ¿Dónde va? | Justificación |
|---|---|---|
| cédula | Empleado (clase padre) | Todo empleado la tiene |
| nombre | Empleado (clase padre) | Todo empleado lo tiene |
| dependencia | Empleado (clase padre) | Todo empleado pertenece a una |
| resumen() | Padre y sobrescrito | Existe en todos, pero cada hija agrega sus datos |
| calcularPagoMensual() | Padre y sobrescrito | Todos cobran, pero se calcula distinto |
| horasCatedra | Docente | Solo el docente de hora cátedra las tiene |
| valorHora | Docente | Solo el docente de hora cátedra lo tiene |
| salarioBasico | Administrativo | Solo el administrativo tiene salario fijo |
| diasVacaciones | Administrativo | Solo el administrativo tiene vacaciones |
| tomarVacaciones(int dias) | Administrativo | Comportamiento exclusivo del administrativo |