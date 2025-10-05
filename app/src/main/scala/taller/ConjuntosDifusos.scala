package taller

class ConjuntosDifusos() {
  type ConjDifuso = Int => Double
  def pertenece(elem: Int, s: ConjDifuso): Double = {
    s(elem)
  }
  def grande(d: Int, e: Int): ConjDifuso = {
    // Implementaci´on de la funci´on grande
  ...
  }
  def complemento(c: ConjDifuso): ConjDifuso = {
    // Implementaci´on de la funci´on complemento
  ...
  }
  4
  def union(cd1: ConjDifuso, cd2: ConjDifuso): ConjDifuso = {
    // Implementaci´on de la funci´on union
  ...
  }
  def interseccion(cd1: ConjDifuso, cd2: ConjDifuso): ConjDifuso = {
    // Implementaci´on de la funci´on interseccion
  ...
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