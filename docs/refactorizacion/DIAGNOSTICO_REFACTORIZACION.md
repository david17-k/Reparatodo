# Diagnóstico de refactorización – Reparatodo

## 1. Contexto

Se identificó que el servicio técnico **Reparatodo** presentaba una problemática relacionada con los tiempos prolongados de reparación y la falta de transparencia durante la prestación del servicio, lo que generaba inconformidad y reclamos por parte de los clientes.

Con el propósito de mejorar estos aspectos, la empresa contrató expertos que recomendaron el desarrollo de un software para gestionar los procesos de atención y reparación de dispositivos electrónicos.

El sistema permitirá que los clientes consulten el estado de sus órdenes y lleven un seguimiento de las reparaciones realizadas a sus dispositivos. Asimismo, facilitará al personal del servicio técnico la gestión y actualización de las órdenes de acuerdo con el estado en que se encuentre cada equipo, proporcionando mayor control y trazabilidad durante el proceso de reparación.

Adicionalmente, cuando una orden alcance el estado de **reparada**, el sistema enviará una notificación por correo electrónico al centro de atención telefónica (*call center*), con el fin de informar al cliente que su dispositivo está listo para ser recogido.

### 1.1 Equipo

El proyecto Reparatodo es desarrollado por un equipo de trabajo académico como parte de la asignatura de Ingeniería de Software II.

**Integrantes:** Juan David Castillo Hernandez.

### 1.2 Repositorio

- **Nombre del proyecto:** Reparatodo.
- **Repositorio:*https://github.com/david17-k/Reparatodo.git* .
- **Rama de trabajo:** main.
- **Lenguaje de programación:** Java.
- **Framework:** Spring Boot.
- **Persistencia:** Spring Data JPA y Hibernate.
- **Base de datos:** Oracle Database.
- **Interfaz de usuario:** Thymeleaf.

### 1.3 Funcionalidad auditada

La funcionalidad seleccionada para la auditoría es **Tomar orden**, cuyo propósito es permitir que un técnico registrado asuma una orden de reparación disponible en el sistema.

Para realizar esta operación, el sistema recibe la identificación del técnico y de la orden. Antes de actualizar la información, se realizan validaciones para comprobar que el técnico exista, que no tenga otra orden asignada y que la orden solicitada no esté asignada a otro técnico.

Cuando las validaciones se cumplen, el sistema actualiza la información correspondiente y cambia el estado del técnico a `OCUPADO`.

Los principales componentes involucrados son:

- `TomarOrdenController`: recibe las solicitudes y coordina la operación.
- `TecnicoServiceImpl`: ejecuta las reglas de negocio relacionadas con la asignación.
- `TecnicoDAO`: gestiona las operaciones de acceso y actualización de datos.
- `TecnicoRepository`: permite consultar y persistir la información mediante Spring Data JPA.
- `TecnicoMapper`: transforma los objetos entre DTO y entidades.

### 1.4 Requisitos relacionados

| Código | Requisito | Relación con la funcionalidad |
|---|---|---|
| BR-01 | El técnico tomará una orden del sistema. | Permite asignar una orden de reparación a un técnico registrado. |
| BR-02 | Reducir los atrasos de las reparaciones. | Busca facilitar la asignación de órdenes y evitar demoras en su atención. |
| BR-03 | Agilizar las reparaciones. | Facilita que los técnicos puedan asumir órdenes disponibles e iniciar su atención. |

La auditoría busca identificar posibles problemas en la validación de datos, las reglas de asignación y el manejo de operaciones simultáneas, con el propósito de establecer mejoras que contribuyan a la calidad, mantenibilidad y confiabilidad del sistema.

## 2. Arquitectura real

### 2.1 Componentes involucrados

La funcionalidad **Tomar orden** utiliza una arquitectura por capas, en la que cada componente tiene una responsabilidad específica dentro del proceso de asignación de órdenes.

| Componente | Capa | Responsabilidad |
|---|---|---|
| `TomarOrdenController` | Presentación | Recibe la solicitud del usuario, obtiene los datos del técnico y de la orden, y ejecuta el servicio correspondiente. |
| `TecnicoServiceImpl` | Negocio | Contiene las reglas de negocio, verifica las condiciones para asignar una orden y coordina la actualización del técnico. |
| `TecnicoDAO` | Acceso a datos | Consulta y actualiza la información del técnico mediante el repositorio. |
| `TecnicoRepository` | Persistencia | Proporciona las operaciones de consulta y almacenamiento utilizando Spring Data JPA. |
| `TecnicoMapper` | Transformación de datos | Convierte los datos entre los DTO y las entidades utilizadas por la aplicación. |
| `OrdenService` | Negocio | Verifica si la orden solicitada ya tiene un técnico asignado. |

### 2.2 Dependencias entre componentes

La comunicación entre los componentes sigue una estructura en la que el controlador delega las operaciones al servicio, el servicio utiliza los componentes de acceso a datos y consulta otros servicios cuando necesita validar reglas de negocio.

Las principales dependencias son:

- `TomarOrdenController` depende de `TecnicoService` para ejecutar la asignación del técnico.
- `TecnicoServiceImpl` depende de `TecnicoDAO` para consultar y actualizar los técnicos.
- `TecnicoServiceImpl` depende de `OrdenService` para comprobar si una orden ya tiene un técnico asignado.
- `TecnicoDAO` depende de `TecnicoRepository` para acceder a la base de datos.
- `TecnicoDAO` utiliza `TecnicoMapper` para realizar las conversiones entre objetos.

### 2.3 Flujo de la funcionalidad

El proceso de tomar una orden se desarrolla de la siguiente manera:

1. El usuario selecciona una orden disponible e introduce la identificación del técnico.
2. `TomarOrdenController` recibe el identificador de la orden y la cédula del técnico.
3. El controlador construye un objeto `ActualizarTecnico` con el estado `OCUPADO` y el identificador de la orden.
4. El controlador llama al método `actualizarTecnico()` de `TecnicoService`.
5. `TecnicoServiceImpl` verifica que el técnico exista.
6. Se comprueba que el técnico no tenga otra orden asignada.
7. Se consulta `OrdenService` para verificar que la orden no esté asignada a otro técnico.
8. Se validan los datos necesarios para realizar la actualización.
9. `TecnicoDAO` recupera el técnico, utiliza `TecnicoMapper` para actualizar los datos y guarda los cambios mediante `TecnicoRepository`.
10. El controlador recibe el resultado y comunica al usuario si la operación fue exitosa o si ocurrió algún error.

### 2.4 Diagrama de componentes

El siguiente diagrama representa las dependencias principales de la funcionalidad:

```mermaid
flowchart TD
    A[Usuario] --> B[TomarOrdenController]
    B --> C[TecnicoServiceImpl]
    C --> D[TecnicoDAO]
    C --> E[OrdenService]
    D --> F[TecnicoRepository]
    D --> G[TecnicoMapper]
    F --> H[(Base de datos Oracle)]
    G --> D
    E --> I[Consulta de asignación de orden]
```

### 2.5 Observaciones arquitectónicas

La funcionalidad presenta una separación básica de responsabilidades mediante capas de presentación, negocio y persistencia. Esta organización facilita la identificación de los componentes responsables de cada operación.

Sin embargo, durante la auditoría se identificaron aspectos que requieren revisión:

- La validación de los datos se realiza después de algunas consultas en el servicio.
- La verificación de que un técnico y una orden estén disponibles podría requerir protección adicional ante solicitudes simultáneas.
- No se observa en el código revisado una validación completa de las transiciones permitidas entre estados del técnico y de la orden.

Estos aspectos se analizarán con mayor detalle en los hallazgos de auditoría y las propuestas de refactorización.**
## 3. Hallazgos de auditoría

Se identificaron tres hallazgos relacionados con la validación de datos, la asignación de órdenes y el control de los estados del técnico. Estos hallazgos se seleccionaron por su posible impacto en la confiabilidad y el correcto funcionamiento de la funcionalidad **Tomar orden**.

### 3.1 Hallazgo H-03: Validación de datos después de las consultas

- **ID:** H-03
- **Ruta:** `src/main/java/Reparatodo/Repa/Bussines/Service/Impl/TecnicoServiceImpl.java` (ajustar según la ubicación real del archivo).
- **Método:** `actualizarTecnico(Long id, ActualizarTecnico actualizarTecnico)`.
- **Líneas:** [Completar con las líneas exactas del método].
- **Evidencia:**

```java
if (!tecnicoDAO.findById(id).isPresent()) {
    throw new TecnicoNoEncontradoException(id);
}

if (tecnicoDAO.existsByOrdenEntity(id)) {
    throw new TecnicoOrdenAsignada(id);
}

if (ordenService.tecnicoAsignado(actualizarTecnico.getIdOrden())) {
    throw new OrdenConTecnicoAsignadoException(id);
}

validarDatos(actualizarTecnico);
```

- **Categoría:** Oportunidad de mejora / posible defecto funcional por validar los datos después de ejecutar consultas.
- **Justificación:** La validación de los datos de entrada se realiza después de consultar la existencia del técnico y las asignaciones. Es conveniente validar primero los datos necesarios para evitar ejecutar consultas con información inválida.
- **Consecuencia:** Se pueden realizar consultas innecesarias y generar errores o resultados poco claros cuando los datos recibidos no cumplen las condiciones esperadas. No se ha demostrado todavía un fallo funcional reproducible.

### 3.2 Hallazgo H-09: Riesgo de asignación simultánea de órdenes

- **ID:** H-09
- **Ruta:** `src/main/java/Reparatodo/Repa/Bussines/Service/Impl/TecnicoServiceImpl.java` (ajustar según la ubicación real del archivo).
- **Método:** `actualizarTecnico(Long id, ActualizarTecnico actualizarTecnico)`.
- **Líneas:** [Completar con las líneas de las comprobaciones de asignación y actualización].
- **Evidencia:**

```java
if (tecnicoDAO.existsByOrdenEntity(id)) {
    throw new TecnicoOrdenAsignada(id);
}

if (ordenService.tecnicoAsignado(actualizarTecnico.getIdOrden())) {
    throw new OrdenConTecnicoAsignadoException(id);
}

TecnicoDTO actualizarTecnicos =
        tecnicoDAO.actulizarTecnico(id, actualizarTecnico)
                .orElseThrow(() ->
                        new RuntimeException("Error al actualizar el tecnico"));
```

- **Categoría:** Riesgo arquitectónico.
- **Justificación:** El sistema verifica que el técnico no tenga otra orden y que la orden solicitada no esté asignada. Sin embargo, las consultas de validación y la actualización se ejecutan como operaciones separadas. Si dos solicitudes llegan simultáneamente, ambas podrían superar las comprobaciones antes de que alguna guarde los cambios.
- **Consecuencia:** Existe el riesgo de que se produzcan asignaciones inconsistentes, por ejemplo, que una misma orden termine asociada a más de un técnico o que un técnico reciba más de una orden. Este comportamiento debe comprobarse mediante pruebas de concurrencia y revisión de las restricciones de la base de datos.

### 3.3 Hallazgo H-11: Validación incompleta de estados

- **ID:** H-11
- **Ruta:** `src/main/java/Reparatodo/Repa/Bussines/Service/Impl/TecnicoServiceImpl.java` (ajustar según la ubicación real del archivo).
- **Método:** `actualizarTecnico(Long id, ActualizarTecnico actualizarTecnico)` y `validarDatos(ActualizarTecnico actualizarTecnico)`.
- **Líneas:** [Completar con las líneas de ambos métodos].
- **Evidencia:**

```java
private void validarDatos(ActualizarTecnico actualizarTecnico) {
    if (actualizarTecnico.getIdOrden() == null
            || actualizarTecnico.getIdOrden() <= 0) {
        // Validación del identificador de la orden.
    }
}
```

La validación mostrada comprueba que el identificador de la orden sea válido, pero en el fragmento revisado no se observa una comprobación de las transiciones permitidas entre los estados del técnico y de la orden.

- **Categoría:** Riesgo arquitectónico.
- **Justificación:** La funcionalidad cambia el estado del técnico a `OCUPADO`, pero es necesario verificar que las reglas de transición de estados se encuentren centralizadas y se apliquen correctamente. No se puede afirmar que la validación esté ausente en todo el sistema sin revisar los demás servicios y componentes.
- **Consecuencia:** Si no existen controles adicionales, podrían permitirse transiciones de estado no válidas o inconsistencias entre el estado del técnico y el de la orden.

### 3.4 Resumen de hallazgos

| ID | Hallazgo | Categoría |
|---|---|---|
| H-01 | Validación de datos después de las consultas. | Oportunidad de mejora / posible defecto funcional. |
| H-02 | Riesgo de asignación simultánea de órdenes. | Riesgo arquitectónico. |
| H-03 | Validación incompleta de estados en el código revisado. | Riesgo arquitectónico. |

Los hallazgos identificados representan aspectos que deben analizarse y verificarse antes de realizar cambios en el código. Su clasificación no significa que todos sean defectos funcionales confirmados.
## 4. Priorización de hallazgos

Para establecer el orden de atención de los hallazgos se utiliza una escala de valoración de 1 a 3, considerando el impacto que puede tener cada problema, el riesgo asociado y el esfuerzo necesario para implementar una solución.

### 4.1 Criterios de evaluación

| Valor | Impacto | Riesgo | Esfuerzo |
|---|---|---|---|
| 1 | Bajo | Bajo | Bajo |
| 2 | Medio | Medio | Medio |
| 3 | Alto | Alto | Alto |

En el esfuerzo, una puntuación mayor representa una solución más compleja o que requiere más trabajo.

### 4.2 Matriz de priorización

| ID | Hallazgo | Impacto | Riesgo | Esfuerzo |
|---|---|---:|---:|---:|
| H-03 | Validación de datos después de las consultas. | 2 | 2 | 1 |
| H-09 | Riesgo de asignación simultánea de órdenes. | 3 | 3 | 3 |
| H-11 | Validación incompleta de estados. | 3 | 2 | 2 |

### 4.3 Justificación de las puntuaciones

**H-03: Validación de datos después de las consultas**

- **Impacto: 2 (medio).** Puede ocasionar consultas innecesarias y un manejo menos claro de los datos inválidos.
- **Riesgo: 2 (medio).** Puede afectar el comportamiento del proceso cuando se reciben identificadores incorrectos.
- **Esfuerzo: 1 (bajo).** La mejora consiste principalmente en trasladar la llamada a `validarDatos()` al inicio del método `actualizarTecnico()`.
- **Razón de selección:** Es un cambio localizado, sencillo de implementar y de verificar mediante pruebas unitarias.

**H-09: Riesgo de asignación simultánea de órdenes**

- **Impacto: 3 (alto).** Una asignación inconsistente puede afectar directamente la gestión de las órdenes y los técnicos.
- **Riesgo: 3 (alto).** Dos solicitudes simultáneas podrían superar las comprobaciones antes de que se actualicen los datos.
- **Esfuerzo: 3 (alto).** Puede requerir transacciones, bloqueos, restricciones en la base de datos y pruebas de concurrencia.
- **Razón de selección:** Es un hallazgo importante porque compromete la integridad de la información, aunque requiere un análisis técnico más profundo antes de implementar una solución.

**H-11: Validación incompleta de estados**

- **Impacto: 3 (alto).** Una transición incorrecta podría generar inconsistencias en el proceso de reparación.
- **Riesgo: 2 (medio).** No se ha confirmado que el sistema permita transiciones inválidas, ya que las validaciones podrían existir en otros componentes.
- **Esfuerzo: 2 (medio).** Requiere revisar las reglas de negocio, los estados existentes y los servicios que modifican las órdenes y los técnicos.
- **Razón de selección:** Es necesario verificar que las transiciones de estado sean coherentes y estén controladas antes de ampliar o modificar el flujo de asignación.

### 4.4 Razón de selección del hallazgo para refactorización

Se selecciona el hallazgo **H-03** como primera refactorización debido a que presenta un esfuerzo bajo y permite mejorar el orden de ejecución de las validaciones sin modificar la arquitectura general del sistema.

La solución consiste en ejecutar `validarDatos(actualizarTecnico)` antes de realizar las consultas de existencia y asignación. De esta manera, se evita consultar información utilizando datos que todavía no han sido validados.

Los hallazgos H-09 y H-11 se mantienen como aspectos pendientes de análisis y verificación, debido a que requieren revisar otros componentes y realizar pruebas adicionales.

La priorización no significa que H-03 sea el hallazgo de mayor impacto, sino que representa el cambio más viable para iniciar el proceso de refactorización.

## 5. Intervención

### 5.1 Cambio elegido

Se seleccionó el hallazgo **H-03: Validación de datos después de las consultas**, identificado en el método `actualizarTecnico()` de la clase `TecnicoServiceImpl`.

La intervención consiste en cambiar el orden de ejecución de las instrucciones para que los datos recibidos sean validados antes de realizar consultas a la base de datos.

El objetivo es mejorar la organización del método, evitar consultas innecesarias cuando los datos de entrada son inválidos y facilitar el mantenimiento del código.

**Tipo de intervención:** Refactorización localizada.

**Estado:** Propuesta pendiente de implementación y pruebas.

### 5.2 Diseño antes de la refactorización

En la implementación original, el método realiza primero las consultas para comprobar la existencia del técnico y las asignaciones de órdenes. Después ejecuta la validación de los datos recibidos.

Flujo original:

1. Consultar si el técnico existe.
2. Verificar si el técnico ya tiene una orden asignada.
3. Verificar si la orden solicitada ya tiene un técnico.
4. Validar los datos de entrada.
5. Actualizar la información del técnico.

Código original:

```java
@Override
public TecnicoDTO actualizarTecnico(
        Long id,
        ActualizarTecnico actualizarTecnico) {

    if (!tecnicoDAO.findById(id).isPresent()) {
        throw new TecnicoNoEncontradoException(id);
    }

    if (tecnicoDAO.existsByOrdenEntity(id)) {
        throw new TecnicoOrdenAsignada(id);
    }

    if (ordenService.tecnicoAsignado(actualizarTecnico.getIdOrden())) {
        throw new OrdenConTecnicoAsignadoException(id);
    }

    validarDatos(actualizarTecnico);

    TecnicoDTO actualizarTecnicos =
            tecnicoDAO.actulizarTecnico(id, actualizarTecnico)
                    .orElseThrow(() ->
                            new RuntimeException(
                                    "Error al actualizar el tecnico"));

    return actualizarTecnicos;
}
```

### 5.3 Diseño después de la refactorización

La propuesta consiste en trasladar la validación al inicio del método, antes de realizar las consultas.

Flujo propuesto:

1. Validar los datos de entrada.
2. Consultar si el técnico existe.
3. Verificar si el técnico ya tiene una orden asignada.
4. Verificar si la orden solicitada ya tiene un técnico.
5. Actualizar la información del técnico.

Código propuesto:

```java
@Override
public TecnicoDTO actualizarTecnico(
        Long id,
        ActualizarTecnico actualizarTecnico) {

    validarDatos(actualizarTecnico);

    if (!tecnicoDAO.findById(id).isPresent()) {
        throw new TecnicoNoEncontradoException(id);
    }

    if (tecnicoDAO.existsByOrdenEntity(id)) {
        throw new TecnicoOrdenAsignada(id);
    }

    if (ordenService.tecnicoAsignado(actualizarTecnico.getIdOrden())) {
        throw new OrdenConTecnicoAsignadoException(id);
    }

    return tecnicoDAO.actulizarTecnico(id, actualizarTecnico)
            .orElseThrow(() ->
                    new RuntimeException(
                            "Error al actualizar el tecnico"));
}
```

### 5.4 Comparación antes y después

| Aspecto | Antes | Después |
|---|---|---|
| Validación de datos | Se ejecuta después de las consultas. | Se ejecuta al inicio del método. |
| Consultas con datos inválidos | Pueden ejecutarse antes de validar. | Se evita ejecutarlas cuando falla la validación inicial. |
| Reglas de asignación | Se mantienen. | Se mantienen. |
| Persistencia | Se realiza mediante `TecnicoDAO`. | Se mantiene mediante `TecnicoDAO`. |
| Resultado esperado para datos válidos | Actualización del técnico. | Se conserva la actualización del técnico. |

### 5.5 Archivos afectados

| Archivo | Modificación |
|---|---|
| `TecnicoServiceImpl.java` | Modificar el orden de ejecución de las instrucciones dentro de `actualizarTecnico()`. |
| `DIAGNOSTICO_REFACTORIZACION.md` | Documentar el hallazgo, la intervención y los resultados de las pruebas. |

No se requiere modificar `TecnicoController`, `TecnicoDAO`, `TecnicoRepository` ni `TecnicoMapper`, ya que la intervención seleccionada se limita al orden de ejecución de las validaciones dentro del servicio.

### 5.6 Justificación del diseño

La intervención conserva las responsabilidades existentes de cada componente y no modifica las reglas de negocio relacionadas con la asignación de órdenes.

Se considera una refactorización localizada porque reorganiza el código sin cambiar la estructura general de la arquitectura.

Para entradas válidas, se espera mantener el resultado de la operación. Para entradas inválidas, el sistema realizará primero la validación y podrá devolver el error correspondiente antes de consultar la información del técnico o de la orden.

El cambio deberá verificarse mediante pruebas que comprueben tanto los casos válidos como los inválidos.

## 6. Verificación

### 6.1 Objetivo de las pruebas

Verificar que la refactorización realizada en el método `actualizarTecnico()` de la clase `TecnicoServiceImpl` mantenga el comportamiento esperado del sistema y ejecute la validación de los datos de entrada antes de realizar las consultas de asignación.

Las pruebas buscan comprobar que:

- Los datos inválidos sean rechazados antes de realizar consultas.
- Un técnico inexistente genere la excepción correspondiente.
- Un técnico que ya tenga una orden asignada no pueda tomar otra.
- Una orden que ya tenga un técnico asignado no pueda ser asignada nuevamente.
- Una solicitud válida permita actualizar correctamente la información del técnico.

### 6.2 Pruebas ejecutadas

| ID | Caso de prueba | Resultado esperado | Resultado obtenido |
|---|---|---|---|
| CP-01 | Enviar un identificador de orden nulo. | Rechazar la solicitud mediante la validación de datos. | [Completar] |
| CP-02 | Enviar un identificador de orden igual o menor que cero. | Rechazar la solicitud mediante la validación de datos. | [Completar] |
| CP-03 | Intentar actualizar un técnico inexistente con datos válidos. | Generar `TecnicoNoEncontradoException`. | [Completar] |
| CP-04 | Intentar asignar otra orden a un técnico que ya tiene una. | Generar `TecnicoOrdenAsignada`. | [Completar] |
| CP-05 | Intentar asignar una orden que ya tiene un técnico. | Generar `OrdenConTecnicoAsignadoException`. | [Completar] |
| CP-06 | Asignar una orden disponible a un técnico válido. | Actualizar correctamente la información del técnico. | [Completar] |

**Estado de las pruebas:** Pendiente de ejecución y registro de resultados.

### 6.3 Resultados obtenidos

Una vez ejecutadas las pruebas, se registrarán los resultados para determinar si la intervención cumple con el comportamiento esperado.

La refactorización se considerará satisfactoria cuando las validaciones se ejecuten en el orden establecido y se mantengan las reglas de negocio de la asignación de órdenes.

Los resultados deben indicar si cada caso fue aprobado o rechazado, junto con una breve explicación cuando se presente algún comportamiento diferente al esperado.

### 6.4 Evidencias antes y después

Para demostrar la intervención realizada se recopilarán las siguientes evidencias:

| Evidencia | Descripción |
|---|---|
| EV-01 | Captura del método `actualizarTecnico()` antes de la refactorización, mostrando que `validarDatos()` se ejecutaba después de las consultas. |
| EV-02 | Captura del método `actualizarTecnico()` después de la refactorización, mostrando que `validarDatos()` se ejecuta al inicio. |
| EV-03 | Captura de la ejecución de las pruebas correspondientes a los datos inválidos. |
| EV-04 | Captura de la ejecución de una prueba de asignación exitosa. |

Las capturas se almacenarán en la carpeta `docs/refactorizacion/evidencias/`.

### 6.5 Limitaciones

Durante la verificación se deben tener en cuenta las siguientes limitaciones:

- La intervención se limita al orden de ejecución de las validaciones dentro del método `actualizarTecnico()`.
- No se modifica la arquitectura general ni la estructura de la base de datos.
- Las pruebas de esta intervención no garantizan que el sistema esté protegido frente a asignaciones simultáneas.
- No se verifica de manera integral la transición de estados del técnico y de las órdenes.
- Los hallazgos H-09 y H-11 requieren análisis y pruebas adicionales.

Por lo tanto, esta intervención permite mejorar la organización de las validaciones, pero no representa una solución definitiva para todos los riesgos identificados durante la auditoría.

### 6.6 Conclusión de la verificación

La verificación permitirá determinar si el cambio realizado conserva el comportamiento esperado de la funcionalidad **Tomar orden** y mejora el proceso de validación de los datos.

La aceptación definitiva de la refactorización dependerá de los resultados obtenidos durante la ejecución de las pruebas y de la evidencia recopilada antes y después del cambio.

## 7. Cierre

### 7.1 Lecciones aprendidas

Durante la auditoría de la funcionalidad **Tomar orden** del sistema Reparatodo se identificó la importancia de mantener una correcta organización del código y de aplicar adecuadamente las validaciones antes de ejecutar operaciones sobre la información almacenada.

Entre las principales lecciones aprendidas se encuentran:

- **Importancia de validar los datos:** las validaciones deben ejecutarse antes de realizar consultas o modificaciones innecesarias en la base de datos.
- **Separación de responsabilidades:** mantener una arquitectura organizada por capas facilita la identificación de problemas y el mantenimiento del sistema.
- **Integridad de la información:** las operaciones de asignación deben protegerse para evitar inconsistencias cuando varios usuarios realizan solicitudes simultáneamente.
- **Control de estados:** es necesario establecer reglas claras para garantizar que los técnicos y las órdenes cambien de estado de manera coherente.
- **Importancia de las pruebas:** las modificaciones realizadas deben verificarse para comprobar que se conserva el comportamiento esperado de la aplicación.

La auditoría permitió comprender que la refactorización no consiste únicamente en modificar código, sino también en mejorar su organización, legibilidad, mantenibilidad y confiabilidad sin alterar innecesariamente las funcionalidades existentes.

### 7.2 Deuda técnica pendiente

Aunque se seleccionó H-03 como primera intervención, durante la auditoría se identificaron otros aspectos que requieren atención:

| ID | Deuda técnica o riesgo pendiente | Acción recomendada | Prioridad |
|---|---|---|---|
| H-09 | Posibles inconsistencias por asignaciones simultáneas de órdenes. | Revisar el manejo de transacciones, bloqueos y restricciones de integridad, y ejecutar pruebas de concurrencia. | Alta |
| H-11 | No se observa una validación completa de las transiciones de estado en el código revisado. | Revisar las reglas de negocio y centralizar las validaciones de estados cuando sea necesario. | Alta |
| H-01 | Captura genérica de excepciones en el controlador. | Mejorar el manejo de errores y utilizar excepciones específicas. | Media |
| H-02 | Identificación de errores mediante comparación de mensajes de texto. | Utilizar excepciones específicas para distinguir los diferentes casos. | Media |
| H-05 | Uso de `System.out.println()` para registrar información. | Implementar un sistema de registros mediante un logger. | Baja |
| H-06 | Uso de excepciones genéricas. | Definir excepciones específicas para los errores del proceso. | Media |
| H-12 | Declaración redundante de `findById()` en el repositorio. | Eliminar la declaración si no existe una necesidad específica de sobrescribir el método heredado. | Baja |

Estos aspectos no forman parte de la intervención principal seleccionada, por lo que quedan registrados como oportunidades de mejora y riesgos pendientes de análisis.

### 7.3 Relación con el proceso de desarrollo

La auditoría y la refactorización se integran al proceso de desarrollo de Reparatodo como actividades orientadas a mejorar continuamente la calidad del software.

El proceso realizado contempla las siguientes etapas:

1. **Identificación del problema:** se revisó la funcionalidad Tomar orden para detectar posibles dificultades en su implementación.
2. **Análisis del código:** se estudiaron los componentes, las dependencias y el flujo de ejecución.
3. **Identificación de hallazgos:** se documentaron tres hallazgos principales relacionados con validaciones, asignación simultánea y control de estados.
4. **Priorización:** se evaluaron los hallazgos según su impacto, riesgo y esfuerzo de solución.
5. **Selección de la intervención:** se eligió H-03 por ser un cambio localizado y de bajo esfuerzo.
6. **Refactorización:** se propuso reorganizar el método `actualizarTecnico()` para ejecutar las validaciones antes de las consultas.
7. **Verificación:** se definieron pruebas para comprobar que el cambio conserve el comportamiento esperado.
8. **Documentación:** se registraron los hallazgos, las modificaciones y las evidencias para facilitar futuras revisiones.

Estas actividades permiten incorporar la mejora continua al desarrollo del proyecto, facilitando que los problemas identificados sean documentados, priorizados, corregidos y verificados antes de incorporar los cambios a la versión principal del sistema.

### 7.4 Conclusión final

La auditoría realizada sobre la funcionalidad **Tomar orden** permitió identificar aspectos que pueden afectar la eficiencia, confiabilidad y mantenibilidad del sistema Reparatodo.

La intervención seleccionada busca mejorar el orden de ejecución de las validaciones, evitando consultas innecesarias cuando los datos recibidos son inválidos. Asimismo, se identificaron riesgos relacionados con la concurrencia y el control de estados que deberán abordarse en futuras mejoras.

En conclusión, este proceso contribuye al fortalecimiento de la calidad del software y al cumplimiento del propósito principal de Reparatodo: mejorar la gestión de las reparaciones, reducir los tiempos de atención y ofrecer mayor transparencia y trazabilidad a los clientes.
