package taller

import scala.annotation.tailrec

class ConjuntosDifusos {
  type ConjDifuso = Int => Double

  def pertenece(elem: Int, s: ConjDifuso): Double = s(elem)

  def grande(d: Int, e: Int): ConjDifuso = {
    // Comprobaciones de que e y d están en el rango ideal
    if (d < 1) throw new IllegalArgumentException("d debe ser mayor o igual que 1")
    if (e <= 1) throw new IllegalArgumentException("e debe ser mayor que 1")
    // Se aplica la fórmula de grande
    (n: Int) => math.pow(n.toDouble / (n + d).toDouble, e.toDouble)
  }
  def complemento(c: ConjDifuso): ConjDifuso = {
    // Se usa la función formulada en el PDF; 1 - S1(x)
    (n: Int) => 1.0 - c(n)
  }

  def union(cd1: ConjDifuso, cd2: ConjDifuso): ConjDifuso =
    //Se aplica la formula especificada en el PDF
    (n: Int) => math.max(cd1(n), cd2(n))

  def interseccion(cd1: ConjDifuso, cd2: ConjDifuso): ConjDifuso =
    //Se aplica la formula especificada en el PDF
    (n: Int) => math.min(cd1(n), cd2(n))

  def inclusion(cd1: ConjDifuso, cd2: ConjDifuso): Boolean = {
    // Se usa una función auxiliar de recursión de cola que recorre el intervalo [0,1000]
    @tailrec
    def inclusionAux(n: Int): Boolean =
      if (n > 1000) true   // Condición de parada
      else if (cd1(n) > cd2(n)) false   // Caso en que elemento de S1 no está contenido en elemento de S2
      else inclusionAux(n + 1)   // Llamada recursiva
    inclusionAux(0)   // Se inicia en n = 0
  }

  def igualdad(cd1: ConjDifuso, cd2: ConjDifuso): Boolean = {
    // Si se cumplen ambas condiciones, significa que ningún elemento de S1 es diferente del elemento de S2 correspondiente
    inclusion(cd1, cd2) && inclusion(cd2, cd1)
  }
}