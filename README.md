<div align="center">

# Converters4DeltaSpike
[![Matrix]][matrix-community] [![Discord]][discord-guild] [![Maven Central]][maven-page] [![Docs]][documentation] [![Build]][gitlab] [![Coverage]][gitlab] [![Donate]][elypia-donate]
</div>

## About
DeltaSpike supports a limited number types for configuration by default, 
please see a list of supported types on the official 
[documentation][deltaspike-supported-types]. Converters4DeltaSpike provides 
additional [`ConfigResolver.Converter`] implementations for various types in 
Java which are optimized and thoroughly tested. This is helpful for applications, 
frameworks, or libraries, with a lot of flexibility in configuration including 
the ability to use regular expression, dates, or enumerated types.

This will add converters for the following types and types derived (`extends`) 
from them:
<!-- Listed in alphabetical order. -->
* [`Character`](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/Character.html)
* [`Color`](https://docs.oracle.com/en/java/javase/11/docs/api/java.desktop/java/awt/Color.html)
* [`Dimension`](https://docs.oracle.com/en/java/javase/11/docs/api/java.desktop/java/awt/Dimension.html)
* [`Duration`](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/time/Duration.html)
* [`Enum`](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/Enum.html)
* [`File`](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/io/File.html)
* [`InetAddress`](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/net/InetAddress.html)
* [`Instant`](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/time/Instant.html)
* [`Locale`](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/Locale.html)
* [`Pattern`](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/regex/Pattern.html)
* [`Period`](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/time/Period.html)
* [`Point`](https://docs.oracle.com/en/java/javase/11/docs/api/java.desktop/java/awt/Point.html)
* [`URI`](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/net/URI.html)
* [`URL`](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/net/URL.html)
* [`UUID`](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/UUID.html)

The [Gradle]/[Maven] import strings can be found at the maven-central badge above!

## Usage
There are no special conditions or usage guidelines for this project; 
you can just follow the regular documentation provided by the 
Apache DeltaSpike project.

Please see the [documentation][deltaspike-typed-resolver-api] for more information.

### Example
The most simple example is to create an interface based configuration and use the
`@ConfigProperty#converter()` property to specify the desired converter for 
the type required.

```java
@Configuration(prefix = "config.")
public interface MyCustomConfig {

    /** Accepts: / */
    @ConfigProperty(name = "seperator", converter = CharacterConverter.class)
    Character getSeperator();

    /** Accepts: #FFF */
    @ConfigProperty(name = "color", converter = ColorConverter.class)
    Color getColor();

    /** Accepts: P2D */ 
    @ConfigProperty(name = "interval", converter = DurationConverter.class)
    Duration getInterval();

    /** Accepts: java.time.DayOfWeek#MONDAY */
    @ConfigProperty(name = "day", converter = EnumConverter.class)
    DayOfWeek getDay();
    
    /** Accepts: en-US */
    @ConfigProperty(name = "locale", converter = LocaleConverter.class)
    Locale getLocale();
    
    /** Accepts: \\s+ */
    @ConfigProperty(name = "delimiter", converter = PatternConverter.class)
    Pattern getDelimiter();
}
```

## Open-Source
This project is open-source under the [Apache 2.0]!  
While not legal advice, you can find a [TL;DR] that sums up what
you're allowed and not allowed to do along with any requirements if you want to 
use or derive work from this source code!  

[matrix-community]: https://matrix.to/#/+elypia:matrix.org "Matrix Invite"
[discord-guild]: https://discord.com/invite/hprGMaM "Discord Invite"
[maven-page]: https://search.maven.org/search?q=g:org.elypia.converters4deltaspike "Maven Central"
[documentation]: https://elypia.gitlab.io/converters4deltaspike "Project Documentation"
[gitlab]: https://gitlab.com/Elypia/converters4deltaspike/commits/master "Repository on GitLab"
[elypia-donate]: https://elypia.org/donate "Donate to Elypia"
[Gradle]: https://gradle.org/ "Depend via Gradle"
[Maven]: https://maven.apache.org/ "Depend via Maven"
[`ConfigResolver.Converter`]: https://deltaspike.apache.org/javadoc/1.9.3/org/apache/deltaspike/core/api/config/ConfigResolver.Converter.html
[deltaspike-supported-types]: https://deltaspike.apache.org/documentation/configuration.html#_supported_types
[deltaspike-typed-resolver-api]: https://deltaspike.apache.org/documentation/configuration.html#TypedResolverAPI
[Apache 2.0]: https://www.apache.org/licenses/LICENSE-2.0 "Apache 2.0 License"
[TL;DR]: https://tldrlegal.com/license/apache-license-2.0-(apache-2.0) "TL;DR of Apache 2.0"

[Matrix]: https://img.shields.io/matrix/elypia:matrix.org?logo=matrix "Matrix Shield"
[Discord]: https://discord.com/api/guilds/184657525990359041/widget.png "Discord Shield"
[Maven Central]: https://img.shields.io/maven-central/v/org.elypia.converters4deltaspike/converters4deltaspike "Download Shield"
[Docs]: https://img.shields.io/badge/docs-converters4deltaspike-blue.svg "Documentation Shield"
[Build]: https://gitlab.com/Elypia/converters4deltaspike/badges/master/pipeline.svg "GitLab Build Shield"
[Coverage]: https://gitlab.com/Elypia/converters4deltaspike/badges/master/coverage.svg "GitLab Coverage Shield"
[Donate]: https://img.shields.io/badge/donate-elypia-blueviolet "Donate Shield"
