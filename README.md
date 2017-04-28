[![Build Status](https://travis-ci.org/lavender-flowerdew/java-tibetan-text-tools.svgended-?branch=master)](https://travis-ci.org/lavender-flowerdew/java-tibetan-text-tools)
[![Coverage Status](https://coveralls.io/repos/github/lavender-flowerdew/java-tibetan-text-toolse/badge.svg?branch=master)](https://coveralls.io/github/lavender-flowerdew/java-tibetan-text-tools?branch=master)

# Java Tibetan Text Tools

Perhaps misnamed for now but hope to collect a set of useful methods for working with Tibetan unicode text. Library is for Java without dependencies on Scala, however build and test is Scala (2.12) based.

## To Do

- [x] Fix mappings in WYLIE
- [x] Add tests
- [ ] Convert to pattern matcher rather than blind loops e.g. `while (m.find()) { val g = m.toMatchResult().group(); val r = map.get(g).getOrElse("*"); m.appendReplacement(sb, r+"*"); println(s"'${g}' '${r}'");}`

