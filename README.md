# FinanKids

## Sistema de gestión y educación financiera familiar

FinanKids es un prototipo de sistema informático orientado a la educación financiera de niños y adolescentes dentro del ámbito familiar.

El proyecto busca proporcionar un entorno controlado en el cual los menores puedan aprender progresivamente conceptos relacionados con la administración del dinero, el ahorro, la planificación y el establecimiento de objetivos, bajo el acompañamiento de sus padres o tutores.

FinanKids no realiza operaciones con dinero real ni se conecta con bancos, tarjetas, billeteras virtuales u otras entidades financieras. Los saldos y movimientos administrados por el sistema representan operaciones internas con fines educativos.

## Objetivo

El objetivo del sistema es proporcionar a niños y adolescentes un entorno digital, inclusivo y adaptable de educación y gestión financiera familiar que les permita aprender a administrar recursos, establecer prioridades, ahorrar, alcanzar objetivos y comprender la relación entre responsabilidad, esfuerzo y recompensa.

Los importes utilizados para asignaciones, recompensas y metas son configurados por cada familia de acuerdo con su propia realidad económica.

## Funcionalidades implementadas

El prototipo desarrollado incluye:

- Gestión de usuarios con roles de tutor y menor.
- Conexión entre la aplicación Java y la base de datos.
- Registro y consulta de movimientos financieros simulados.
- Gestión de solicitudes de dinero.
- Creación y seguimiento de metas de ahorro.
- Creación y asignación de tareas.
- Asociación opcional de recompensas económicas a las tareas.
- Validación de tareas por parte del tutor.
- Acreditación de recompensas.
- Registro de movimientos generados por las recompensas.

## Tecnologías utilizadas

- Java 21
- Maven
- JDBC
- MySQL / MariaDB
- XAMPP
- Visual Studio Code

## Estructura del proyecto

```text
FinanKids/
├── database/
│   └── finankids_db.sql
├── src/
│   └── main/
│       └── java/
│           └── com/
│               └── finankids/
├── .gitignore
├── pom.xml
└── README.md
Base de datos

La base de datos utilizada por el proyecto se denomina: finankids_db
El archivo necesario para reconstruir la base de datos se encuentra en: database/finankids_db.sql
La base contiene las tablas necesarias para gestionar usuarios, movimientos, solicitudes de dinero, metas de ahorro, tareas y recompensas.
Configuración de la conexión
La conexión con la base de datos se realiza mediante JDBC desde la clase: ConexionBD.java
Configuración utilizada durante el desarrollo:
Host: localhost
Puerto: 3306
Base de datos: finankids_db
Usuario: root
La contraseña debe configurarse de acuerdo con el entorno local donde se ejecute el proyecto.
Compilación:
El proyecto utiliza Maven. Para verificar la compilación se puede ejecutar desde la raíz del proyecto:
mvn clean compile
Una compilación correcta debe finalizar indicando:
BUILD SUCCESS
Pruebas implementadas:
El proyecto contiene clases de prueba destinadas a verificar las principales operaciones desarrolladas, entre ellas:
PruebaConexion.java
PruebaUsuarioDAO.java
PruebaTutorDAO.java
PruebaMovimientoDAO.java
PruebaSolicitudDineroDAO.java
PruebaMetaAhorroDAO.java
PruebaTareaDAO.java
PruebaRecompensaDAO.java
PruebaValidacionTarea.java
Estas pruebas permiten verificar la comunicación entre Java y MySQL y comprobar la persistencia y actualización de la información.
Ejemplo del flujo de tareas y recompensas
Uno de los procesos implementados permite representar el siguiente flujo:
El tutor crea una tarea para el menor.
La tarea puede tener o no una recompensa económica asociada.
El menor informa que realizó la tarea.
El tutor valida su cumplimiento.
Si la tarea posee una recompensa, esta se acredita.
El sistema registra el movimiento correspondiente.
De esta forma, FinanKids permite relacionar responsabilidad, esfuerzo, administración de recursos y educación financiera sin establecer que todas las responsabilidades familiares deban tener necesariamente una recompensa económica.
Importación de la base de datos
Para utilizar el proyecto en otro entorno debe importarse el archivo: database/finankids_db.sql
en un servidor MySQL o MariaDB.
Posteriormente debe verificarse la configuración de conexión definida en ConexionBD.java.
Alcance académico:
FinanKids fue desarrollado como un prototipo académico. Su objetivo es demostrar la aplicación de conceptos de análisis de sistemas, programación orientada a objetos, persistencia de datos y conexión entre Java y MySQL.
No constituye una aplicación financiera productiva ni procesa transacciones monetarias reales.
Autora
Carolina Orse
Licenciatura en Informática
Proyecto académico – FinanKids