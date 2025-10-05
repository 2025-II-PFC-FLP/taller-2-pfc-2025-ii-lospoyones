package taller

import scala.annotation.tailrec

class ConjuntosDifusos() {
  type ConjDifuso = Int => Double
  def pertenece(elem: Int, s: ConjDifuso): Double = {
    s(elem)
  }

  def grande(d: Int, e: Int): ConjDifuso = {
    //Comprobaciones de que e y d esten en el rango ideal
    if (d < 1) throw new IllegalArgumentException("d debe ser mayor o igual que 1")
    if (e <= 1) throw new IllegalArgumentException("e debe ser mayor que 1")
    //Aplicamos la formula de grande
    (n: Int) => math.pow(n.toDouble / (n + d).toDouble, e.toDouble)
  }

  def complemento(c: ConjDifuso): ConjDifuso = {
    // Se aplica la fórmula expresada en el PDF 1 - fs1(x)
    (n: Int) => 1.0 - c(n)
  }

  def union(cd1: ConjDifuso, cd2: ConjDifuso): ConjDifuso = {
    //Se aplica la formula especificada en el PDF
    (n: Int) => math.max(cd1(n), cd2(n))
  }
  def interseccion(cd1: ConjDifuso, cd2: ConjDifuso): ConjDifuso = {
    //Se aplica la formula especificada en el PDF
    (n: Int) => math.min(cd1(n), cd2(n))
  }
  def inclusion(cd1: ConjDifuso, cd2: ConjDifuso): Boolean = {
    // Se agrega una función recursiva de cola para recorrer el intervalo [0,1000]
    @tailrec
    def inclusionAux(n: Int): Boolean =
      if (n > 1000) true   // Condición de parada
      else if (cd1(n) > cd2(n)) false   // Si el elemento del conjunto difuso S1 no está contenido en S2
      else inclusionAux(n + 1)   // Llamada recursiva
    inclusionAux(0)   // Se empieza en n = 0
  }
  def igualdad(cd1: ConjDifuso, cd2: ConjDifuso): Boolean = {
    // En caso de cumplirse ambas condiciones, significa que ningún elemento entre S1 y S2 es diferente de su par correspondiente
    inclusion(cd1, cd2) && inclusion(cd2, cd1)
  }
}