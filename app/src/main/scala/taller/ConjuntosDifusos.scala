package taller

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
    // Implementaci´on de la funci´on complemento
  ...
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
    // Implementaci´on de la funci´on inclusion
  ...
  }
  def igualdad(cd1: ConjDifuso, cd2: ConjDifuso): Boolean = {
    // Implementaci´on de la funci´on igualdad
  ...
  }
}