# ![Workcraft logo](logo.png)

[![Build status](https://travis-ci.org/workcraft/workcraft.svg)](https://travis-ci.org/workcraft/workcraft)
[![Code maintainability](https://codeclimate.com/github/workcraft/workcraft/badges/gpa.svg)](https://codeclimate.com/github/workcraft/workcraft)
[![Coverage](https://img.shields.io/coveralls/github/workcraft/workcraft.svg)](https://coveralls.io/github/workcraft/workcraft)
[![Confirmed issues](https://img.shields.io/waffle/label/workcraft/workcraft/status:confirmed.svg?label=issues)](https://waffle.io/workcraft/workcraft)
![Repo size](https://img.shields.io/github/repo-size/workcraft/workcraft.svg)
![Code size](https://img.shields.io/github/languages/code-size/workcraft/workcraft.svg)
[![Current release](https://img.shields.io/github/release/workcraft/workcraft.svg)](https://github.com/workcraft/workcraft/releases)

Workcraft is a cross-platform toolset to capture, simulate, synthesize
and verify graph models. It supports a wide range of popular graph
formalisms and provides a plugin-based framework to model and analyze
new model types. For more information about Workcraft look at
https://workcraft.org/.

### Building

Workcraft requires Java JDK 1.8 or newer for a successful build and
is assembled via [Gradle](https://gradle.org/). It is tested with
both [Open JDK](http://openjdk.java.net/) and
[Oracle JDK](http://www.oracle.com/technetwork/java/javase/downloads/index.html).

These instructions use `gradlew`, a wrapper that downloads and runs
a relatively new version of `gradle`. Alternatively a pre-installed
version can be used.

Use the `assemble` task to build the core and all the plugins:

    ./gradlew assemble

### Running

You can run Workcraft directly after building it:

    ./workcraft

### Miscellaneous

Help and tutorial pages are available in the
[workcraft-doc](https://github.com/workcraft/workcraft-doc) repo.

Templates for building Windows, Linux and OS X distributions of
Workcraft are available in the
[workcraft-dist-template](https://github.com/workcraft/workcraft-dist-template)
repo. This includes the binaries of backend tools, gate libraries
and other platform-specific content.

If you would like to contribute to Workcraft development, then read
through the [CONTRIBUTING.md](CONTRIBUTING.md) document.
