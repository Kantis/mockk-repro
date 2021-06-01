import io.kotest.core.spec.style.FunSpec
import io.mockk.mockkStatic
import kotlin.system.measureTimeMillis

class MyTest : FunSpec(
    {
        context("some context") {
            mockkStatic(MyTypeAlias::doFunThings)

            test("Things should not take too much time") {
                val aliased = MyTypeAlias(5) { false }

                for (i in 1..1_000_000) {
                    if (i % 10_000 == 0) println(i)

                    val time = measureTimeMillis {
                        aliased.doFunThings()
                    }

                    if (time > 1000) {
                        println("cancelling on $i")
                    }
                }
            }
        }
    }
)
