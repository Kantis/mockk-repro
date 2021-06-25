import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.mockk.mockkStatic
import kotlin.system.measureTimeMillis

class MyTest : FunSpec(
    {
        context("some context") {
            mockkStatic(MyTypeAlias::doFunThings)

            test("Things should not take too much time") {
                val aliased = MyTypeAlias(5) { false }
                var logEveryReptition = false
                var time = 0L

                var invocations = 0
                for (i in 1..10_000_000) {
                    invocations++
                    if (i % 10_000 == 0 || logEveryReptition) println("Invocation ${i - 1} finished in ${time}ms")

                    time = measureTimeMillis {
                        aliased.doFunThings()
                    }

                    if (i > 100 && time > 50 && !logEveryReptition) {
                        println("Enabling logging every repetition after $i invocations (last invocation: ${time}ms)")
                        logEveryReptition = true
                    }

                    if (time > 1000) {
                        println("cancelling on $i")
                        break
                    }
                }

                invocations shouldBe 10_000_000
            }
        }
    }
)
