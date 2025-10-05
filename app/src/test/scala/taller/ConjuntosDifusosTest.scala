package taller
import org.scalatest.funsuite.AnyFunSuite
import org.junit.runner.RunWith
import org.scalatestplus.junit.JUnitRunner
import taller.ConjuntosDifusos
// Try se usa para comprobar que una construcción NO lanza excepción, sin matchers adicionales
import scala.util.Try

// Anotación para ejecutar esta suite con el runner de JUnit
@RunWith(classOf[JUnitRunner])
class ConjuntosDifusosTest extends AnyFunSuite {

  // Instancia de la clase bajo prueba
  val conj = new ConjuntosDifusos()

  // -------------------------
  // PRUEBAS PARA grande
  // -------------------------

  test("grande valida parametros: d >= 1 y e > 1") {
    // Debe lanzar IllegalArgumentException si d < 1
    assertThrows[IllegalArgumentException] {
      conj.grande(0, 2)
    } // d < 1 -> falla

    // Debe lanzar IllegalArgumentException si e <= 1
    assertThrows[IllegalArgumentException] {
      conj.grande(1, 1)
    } // e <= 1 -> falla

    // Caso válido: d == 1 y e > 1 no debe fallar.
    assert(Try(conj.grande(1, 2)).isSuccess) // d == 1 y e > 1 -> ok
  }

  test("grande d=1, e=2 con casos exactos (n+1 potencia de 2)") {
    // Definimos la función de pertenencia "grande" con d=1, e=2:
    // μ(n) = (n / (n + 1))^2
    val g = conj.grande(1, 2)

    // Elegimos n tales que (n + 1) sea potencia de 2 para que n/(n+1) sea fracción exacta en binario.
    // Así, las comparaciones con '==' son seguras.

    assert(g(0) == 0.0)      // (0/1)^2  = 0.0  -> borde inferior
    assert(g(1) == 0.25)     // (1/2)^2  = 1/4
    assert(g(3) == 0.5625)   // (3/4)^2  = 9/16 = 0.5625
    assert(g(7) == 0.765625) // (7/8)^2  = 49/64 = 0.765625
  }

  test("grande d=3, e=2 con casos exactos (n+3 potencia de 2)") {
    // Ahora μ(n) = (n / (n + 3))^2 con d=3, e=2
    val g = conj.grande(3, 2)

    // De nuevo, elegimos n tales que (n + 3) sea potencia de 2 para asegurar exactitud binaria.
    assert(g(1) == 0.0625)    // (1/4)^2  = 1/16  = 0.0625  (porque 1+3=4)
    assert(g(5) == 0.390625)  // (5/8)^2  = 25/64 = 0.390625 (porque 5+3=8)
  }
  // -------------------------
  // PRUEBAS PARA complemento
  // -------------------------
  test("complemento con base n/4 acotada en [0,1]") {
    // Se realiza la operación con base n/4 para obtener un decimal exacto y no tener errores en las comparaciones con "=="
    val base: conj.ConjDifuso = n => math.min(n.toDouble / 4.0, 1.0) // 0, .25, .5, .75, 1.0 (Función Int => Double)
    val comp = conj.complemento(base)  // Complemento de la función "base", su resultado depende de la 'n' ingresada
    assert(comp(0) == 1.0)      // 1 - 0 = 1
    assert(comp(2) == 0.5)      // 1 - .5 = .5
    assert(comp(3) == 0.25)     // 1 - .75 = .25
    assert(comp(4) == 0.0)      // 1 - 1 = 0
  }

  // -------------------------
  // PRUEBAS PARA unión e intersección
  // -------------------------
  test("union e interseccion con valores exactos en binario") {
    // Operaciones para obtener decimales que permitan comparaciones precisas en las pruebas
    val S1: conj.ConjDifuso = n => math.min(n.toDouble / 4.0, 1.0) // 0, .25, .5, .75, 1.0
    val S2: conj.ConjDifuso = n => math.min(n.toDouble / 2.0, 1.0) // 0, .5, 1.0, 1.0, 1.0

    // Se realizan las operaciones para comparar con resultados esperados ya calculados
    val u = conj.union(S1, S2)
    val i = conj.interseccion(S1, S2)

    // Unión (max)
    assert(u(1) == 0.5)   // max(.25, .5) = .5
    assert(u(2) == 1.0)   // max(.5, 1.0) = 1.0
    assert(u(3) == 1.0)   // max(.75, 1.0) = 1.0

    // Intersección (min)
    assert(i(1) == 0.25)  // min(.25, .5) = .25
    assert(i(2) == 0.5)   // min(.5, 1.0) = .5
    assert(i(3) == 0.75)  // min(.75, 1.0) = .75
  }

  // -------------------------
  // PRUEBAS PARA inclusión e igualdad
  // -------------------------
  test("inclusion y igualdad con fracciones exactas") {
    // Operaciones para obtener Conjuntos difusos con un grado de pertenencia preciso y que no afecte a las comparaciones de las pruebas
    val A: conj.ConjDifuso = n => math.min(n.toDouble / 4.0, 1.0)          // 0, .25, .5, .75, 1.0
    val B: conj.ConjDifuso = n => math.min((n.toDouble / 4.0) + 0.25, 1.0) // desplaza +0.25 (exacto)
    val C: conj.ConjDifuso = n => math.min(n.toDouble / 4.0, 1.0)          // igual a A

    assert(conj.inclusion(A, B))   // B >= A en todo n
    assert(!conj.inclusion(B, A))  // inversa falsa
    assert(conj.igualdad(A, C))    // iguales
    assert(!conj.igualdad(A, B)) // No iguales
  }
}