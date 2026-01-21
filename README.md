# Sistema de Red Social - Java

Sistema de gestión de red social implementado en Java con estructuras de datos avanzadas. Permite crear usuarios, publicaciones, comentarios anidados, sistema de likes y seguimiento de actividad.

## Características

- Gestión de usuarios con IDs únicos
- Publicaciones y comentarios con timestamp automático
- Comentarios anidados de cualquier profundidad
- Sistema de likes y ranking de popularidad
- Historial de actividades por usuario
- Validación de datos completa

## Estructuras de Datos

- **HashMap**: Gestión eficiente de usuarios y publicaciones
- **Árbol N-ario**: Comentarios anidados ilimitados
- **Árbol AVL**: Índice de popularidad autobalanceado
- **Lista Doblemente Enlazada**: Historial de actividades

## Requisitos

- JDK 8 o superior
- IDE Java o compilador javac

## Estructura del Proyecto
```
red-social-java/
├── Usuario.java
├── Publicacion.java
├── Comentario.java
├── UserManager.java
├── PostTree.java
├── NodoArbol.java
├── PopularityIndex.java
├── NodoAVL.java
├── ActivityHistory.java
├── NodoDoble.java
├── SocialNetworkManager.java
└── Principal.java
```

## Instalación

1. Clonar el repositorio:
```bash
git clone https://github.com/tu-usuario/red-social-java.git
cd red-social-java
```

2. Compilar:
```bash
javac *.java
```

3. Ejecutar:
```bash
java Principal
```

## Uso
```java
SocialNetworkManager red = new SocialNetworkManager();

// Crear usuarios
red.crearUsuario("maria");
red.crearUsuario("pedro");

// Crear publicación
int pubId = red.crearPublicacion("maria", "Mi primera publicación!");

// Comentar
red.comentarPublicacion("pedro", pubId, pubId, "¡Excelente post!");

// Dar like
red.darLike(pubId);

// Top publicaciones
List<Integer> top = red.getTopNPublicaciones(5);

// Historial de usuario
List<String> historial = red.getHistorialDeUsuario("maria", 3);
```

## Funcionalidades

| Operación | Descripción |
|-----------|-------------|
| `crearUsuario(username)` | Registra un nuevo usuario |
| `crearPublicacion(user, contenido)` | Crea una publicación |
| `comentarPublicacion(user, pubId, padreId, contenido)` | Agrega un comentario |
| `darLike(publicacionId)` | Incrementa la popularidad |
| `getTopNPublicaciones(n)` | Obtiene las N publicaciones más populares |
| `getHistorialDeUsuario(user, n)` | Obtiene las últimas N actividades |

## Validaciones

El sistema valida automáticamente:
- Usuarios duplicados o vacíos
- Contenido vacío o nulo
- Referencias a usuarios/publicaciones inexistentes
- Valores inválidos en consultas

## Complejidad

| Operación | Complejidad |
|-----------|-------------|
| Crear usuario | O(1) |
| Crear publicación | O(log n) |
| Agregar comentario | O(h) |
| Dar like | O(log n) |
| Top N publicaciones | O(n) |

## Autores

Laura Pedraza y Jorge Zarate

Proyecto académico - Estructuras de Datos
