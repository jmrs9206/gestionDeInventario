# Sistema de Gestión de Inventario de Oficinas y Materiales

Bienvenido a la guía oficial de instalación, configuración y ejecución del proyecto. Este proyecto ha sido desarrollado como el trabajo final individual del curso, integrando una arquitectura robusta, interfaces dinámicas y persistencia segura de datos.

---

## 🚀 1. Tecnologías Utilizadas

La aplicación está construida sobre una pila tecnológica moderna y robusta para entornos de escritorio corporativos:

* **Lenguaje de Programación**: Java 21 (versión LTS).
* **Interfaz Gráfica de Usuario (GUI)**: JavaFX 21 (con FXML y CSS).
* **Gestor de Proyectos y Dependencias**: Apache Maven.
* **Motor de Persistencia de Datos**: PostgreSQL 16+ con conexión nativa vía **JDBC** (driver oficial de PostgreSQL).
* **Arquitectura**: Multicapa basada en el patrón **Modelo-Vista-Controlador (MVC)** combinada con **DAO** (*Data Access Object*) y **Capa de Servicios**.

---

## 📁 2. Estructura de Entregables del Proyecto

El repositorio está organizado conforme a las directrices de entrega del curso:

* `/src/main/java`: Código fuente completo escrito en Java, organizado limpiamente en paquetes de responsabilidad única (`model`, `view`, `controller`, `service`, `dao`, `config`).
* `/src/main/resources/view`: Diseños declarativos de interfaz de usuario en formato `.fxml` de JavaFX.
* `/src/main/resources/ddl.sql`: Script SQL con la definición de tablas, claves primarias, foráneas y vistas.
* `/src/main/resources/dml.sql`: Script SQL con los datos semilla y registros de prueba cargados por defecto.
* `README.md`: Este manual con las instrucciones completas de despliegue.
* `/src/main/resources/docs/memoria.pdf`: Memoria.pdf

---

## 🛠️ 3. Requisitos Previos

Antes de proceder con la ejecución del proyecto, asegúrese de tener instalados los siguientes componentes en su sistema operativo:

1. **Java Development Kit (JDK)**: Versión 21 o superior.
2. **Apache Maven**: Para la compilación y descarga automática de dependencias.
3. **PostgreSQL**: Servidor activo de base de datos.

---

## 💾 4. Configuración de la Base de Datos

### Paso 1: Crear la Base de Datos

Acceda a su terminal de PostgreSQL (vía `psql` o mediante un cliente visual como **pgAdmin 4** o **DBeaver**) y cree una nueva base de datos llamada `inventario`:

```sql
CREATE DATABASE inventario;
```

### Paso 2: Ejecutar los Scripts SQL (Copiar y Pegar)

Una vez creada la base de datos `inventario`, abra su editor de consultas SQL (en **pgAdmin 4**, **DBeaver** o similar) y ejecute en orden el contenido de los siguientes archivos, copiando y pegando directamente su contenido:

* **Estructura (DDL)**: Copie y ejecute el contenido del archivo ubicado en `/src/main/resources/ddl.sql` para levantar la estructura de tablas, enumerados y la vista de auditoría.
* **Datos Semilla (DML)**: Copie y ejecute el contenido del archivo ubicado en `/src/main/resources/dml.sql` para precargar todos los registros iniciales y las credenciales de prueba.

### Paso 3: Verificar Conexión en el Código

La configuración de credenciales del proyecto está centralizada en la clase `ConexionBD.java` (`org.example.config`). Verifique que los parámetros de conexión se adaptan a su entorno local:

* **URL**: `jdbc:postgresql://localhost:5432/inventario`
* **Usuario por defecto**: `postgres`
* **Contraseña por defecto**: `postgres`

---

## 💻 5. Compilación y Ejecución del Proyecto

El proyecto está preparado para ser levantado directamente desde la terminal con comandos de Maven o importándolo en su IDE favorito (IntelliJ IDEA, Eclipse o VS Code).

### Compilar y Descargar Dependencias

```bash
mvn clean compile
```

### Ejecutar la Aplicación

Inicie el entorno gráfico de JavaFX con el siguiente comando nativo de Maven o **Run InventarioApp.java**:

```bash
mvn javafx:run
```

---

## 🔑 6. Credenciales de Acceso de Prueba (Login)

Al iniciar la aplicación, se mostrará una ventana de inicio de sesión (*Login*). Utilice cualquiera de los siguientes usuarios precargados en la base de datos de prueba para acceder al sistema:

| Rol | Correo Electrónico (Login) | Contraseña |
| :--- | :--- | :--- |
| **Administrador** | `admin@email.com` | `1234` |
| **Técnico de Soporte** | `tecnico@email.com` | `4321` |

---

## 🛡️ 7. Decisiones de Diseño y Buenas Prácticas Aplicadas

* **Evitar Inyección SQL**: Todas las consultas a la base de datos se realizan mediante `PreparedStatement` parametrizados, anulando ataques de manipulación de cadenas de texto SQL.
* **Borrado Lógico (Soft Delete)**: Cumpliendo con los estándares actuales del sector, la eliminación de activos o usuarios no purga los registros físicamente de la base de datos. En su lugar, se actualiza la columna `estado` a `'BAJA'`. Esto preserva la integridad del historial de traslados y auditorías.
* **Controladores Desacoplados**: La interfaz gráfica JavaFX delega la lógica de negocio a la capa de **Servicios**, que a su vez coordina con los **DAO**. Esto asegura que las vistas permanezcan ligeras, legibles y fáciles de mantener.
