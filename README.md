# proyecto1EDD
# Cobertura de Sucursales - Proyecto 1

## Descripción

Este proyecto pertenece al curso “Estructuras de Datos” del trimestre 2425-1 de la Universidad Metropolitana.
Se trata de ayudar a una cadena de supermercados a encontrar ubicaciones para sus sucursales en Sudamérica. Las sucursales tienen que estar ubicadas a una distancia de 100 metros de cualquier sistema de transporte urbano, como parte del cumplimiento de la estrategia de la empresa.

El programa permite cargar una red de transporte y seleccionar algunas paradas para ubicar las sucursales, de esta manera se podrá determinar la cobertura comercial que ofrece cada una. La idea es que con las sucursales ubicadas a lo largo de la ciudad se pueda tener una cobertura total de la ciudad.

## Requisitos funcionales

1. **Cargar una nueva Red de Transporte desde un archivo:** el programa debe cargar un archivo JSON con la red de transporte.
2. **Mostrar el grafo:** visualizar la red de transporte como un grafo.
3. **Establecer el valor de `t`:** permitir modificar el número máximo de paradas consideradas para la cobertura.
4. **Colocar sucursales:** elegir paradas en la red para ubicar sucursales en estas.
5. **Ver cobertura de sucursales:** mostrar las paradas cubiertas dentro del límite `t` usando Búsqueda en Profundidad (DFS) y/o Búsqueda en Amplitud (BFS).
6. **Revisar la cobertura total:** evaluar si las sucursales cubren toda la ciudad. Si no es así, sugerir nuevas ubicaciones.
7. **Agregar nuevas líneas:** permitir la expansión de la red de transporte añadiendo nuevas secuencias de paradas.

## Formato del archivo JSON

Los archivos de la red de formato JSON deben seguir esta estructura:
{
   "Nombre de la Red": [
      {
         "Nombre de Línea": [
            "Parada 1",
            "Parada 2",
            {"Parada 3": "Parada 4"},
            ...
         ]
      }
   ]
}
(Las paradas entre llaves indican una conexión peatonal entre estas).

Este proyecto es de uso académico.

