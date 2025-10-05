package taller

import org.scalatest.funsuite.AnyFunSuite
import org.junit.runner.RunWith
import org.scalatestplus.junit.JUnitRunner
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
}
