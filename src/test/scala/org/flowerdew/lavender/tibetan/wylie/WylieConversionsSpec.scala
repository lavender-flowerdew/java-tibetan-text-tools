/*
 * Copyright 2017 Lavender Flowerdew
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.flowerdew.lavender.tibetan.wylie

import org.flowerdew.lavender._
import org.scalatest._
import org.scalatest.Matchers._

class WylieConversionsSpec extends BaseSpec {
  describe("Wylie Conversions") {
    describe("with Extended Wylie string") {
      it("should handle OM") {
        // set up
        val input = "~om"
        val expected = "\u0F00"

        // test
        val result = WylieConversions.toUchen(input)

        // check
        result should equal (expected)
      }
      it("should return unicode uchen string of Guru Rinpoche Mantra (༔ༀ་ཨཱཿ་ཧཱུྃ་བཛྲ་གུ་རུ་པདྨ་སིདྡྷི་ཧཱུྃ༔)") {
        // set up
        val input = ":oM aAH hU~M badz+ra gu ru pad+ma sid+dhi hU~M:"
        val expected = "༔ༀ་ཨཱཿ་ཧཱུྃ་བཛྲ་གུ་རུ་པདྨ་སིདྡྷི་ཧཱུྃ༔"

        // test
        val result = WylieConversions.toUchen(input)

        // check
        result should equal (expected)
      }

      val ligatures = Seq(("rk", "རྐ"), ("rg", "རྒ"), ("rng", "རྔ"), ("rj","རྗ"),
                          ("rny", "རྙ"), ("rt", "རྟ"), ("rd", "རྡ"), ("rn","རྣ"),
                          ("rb", "རྦ"), ("rm", "རྨ"), ("rts", "རྩ"), ("rdz","རྫ"),
                          ("lk", "ལྐ"), ("lg", "ལྒ"), ("lng", "ལྔ"), ("lc","ལྕ"),
                          ("lj", "ལྗ"), ("lt", "ལྟ"), ("ld", "ལྡ"), ("lp","ལྤ"),
                          ("lb", "ལྦ"), ("lh","ལྷ"), ("sk", "སྐ"), ("sg","སྒ"),
                          ("sng", "སྔ"), ("sny", "སྙ"), ("st", "སྟ"), ("sd","སྡ"),
                          ("sn", "སྣ"), ("sp", "སྤ"), ("sb", "སྦ"), ("sm","སྨ"),
                          ("sts","སྩ"), ("kw", "ཀྭ"), ("khw", "ཁྭ"), ("gw","གྭ"),
                          ("cw", "ཅྭ"), ("nyw", "ཉྭ"), ("tw", "ཏྭ"), ("dw","དྭ"),
                          ("tsw", "ཙྭ"), ("tshw", "ཚྭ"), ("zhw", "ཞྭ"), ("zw","ཟྭ"),
                          ("rw", "རྭ"), ("shw", "ཤྭ"), ("sw", "སྭ"), ("hw","ཧྭ"),
                          ("ky", "ཀྱ"), ("khy", "ཁྱ"), ("gy", "གྱ"), ("py","པྱ"),
                          ("phy", "ཕྱ"), ("by", "བྱ"), ("my","མྱ"), ("kr","ཀྲ"),
                          ("khr", "ཁྲ"), ("gr", "གྲ"), ("tr", "ཏྲ"), ("thr","ཐྲ"),
                          ("dr", "དྲ"), ("pr", "པྲ"), ("phr", "ཕྲ"), ("br","བྲ"),
                          ("mr", "མྲ"), ("shr", "ཤྲ"), ("sr", "སྲ"), ("hr","ཧྲ"),
                          ("kl", "ཀླ"), ("gl", "གླ"), ("bl", "བླ"), ("zl","ཟླ"),
                          ("rl", "རླ"), ("sl","སླ"), ("rky", "རྐྱ"), ("rgy","རྒྱ"),
                          ("rmy", "རྨྱ"), ("rgw", "རྒྭ"), ("rtsw","རྩྭ"), ("sky","སྐྱ"),
                          ("sgy", "སྒྱ"), ("spy", "སྤྱ"), ("sby", "སྦྱ"), ("smy","སྨྱ"),
                          ("skr", "སྐྲ"), ("sgr", "སྒྲ"), ("snr", "སྣྲ"), ("spr","སྤྲ"),
                          ("sbr", "སྦྲ"), ("smr","སྨྲ"), ("grw", "གྲྭ"), ("drw","དྲྭ"),
                          ("phyw","ཕྱྭ"))

      describe("Conversions") {
        describe("should support ligatures") {
          ligatures.foreach{ case (input: String, expected: String) =>
            it(s"""should produce "${expected}" when given "${input}""") {
              WylieConversions.toUchen(input) should equal (expected)
            }
          }
        }
      }
    }
  }
}
