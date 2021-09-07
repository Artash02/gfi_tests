package perf

import com.intuit.karate.gatling.PreDef.karateFeature
import io.gatling.core.Predef._
import io.gatling.http.Predef.http

import scala.concurrent.duration._
import scala.language.postfixOps

class ProfilerSimulation extends Simulation {

  val profilerAdd = scenario("profile add")
    .exec(karateFeature("classpath:gfi/profilerAdd.feature"))

  setUp(
    profilerAdd.inject(rampUsers(2) during(5 seconds))
  )
}