## Summer Framework
<i>Java framework inspired by [Spring Framework](https://spring.io).</i>

## Getting started
### Quick start

#### Starting application
```java
import io.github.matsior.summerframework.core.Context;
import io.github.matsior.summerframework.core.SummerApplication;

public class MyApplication {

  public static void main(String[] args) {
    SummerApplication.start(MyApplication.class);
  }

}
```

#### Dependency Injection

- By constructor:
```java
import io.github.matsior.summerframework.core.Autowired;
import io.github.matsior.summerframework.core.Seed;

@Seed
class MyService {

  private MySecondService mySecondService;

  @Autowired
  MyService(MySecondService mySecondService) {
    this.mySecondService = mySecondService;
  }

}

@Seed
class MySecondService {

}
```

- By setters:
```java
import io.github.matsior.summerframework.core.Autowired;
import io.github.matsior.summerframework.core.Seed;

@Seed
class MyService {

  private MySecondService mySecondService;

  @Autowired
  void setMySecondService(MySecondService mySecondService) {
    this.mySecondService = mySecondService;
  }

}

@Seed
class MySecondService {

}
```

- By fields:
```java
import io.github.matsior.summerframework.core.Autowired;
import io.github.matsior.summerframework.core.Seed;

@Seed
class MyService {

  @Autowired
  private MySecondService mySecondService;

}

@Seed
class MySecondService {

}
```

#### Retrieving Seed from Context:

```java
import io.github.matsior.summerframework.core.Context;
import io.github.matsior.summerframework.core.SummerApplication;

public class MyApplication {

  public static void main(String[] args) {
    Context context = SummerApplication.start(MyApplication.class);
    MyService myService = context.getSeedHolder().getSeed(MyService.class);
  }

}
```


### Vocabulary
- Seed - TBD
- Context - TBD

### Features
TBD

## Roadmap
### Must have
- Dependency injection
- Artifact in remote repository
- External properties support
- Http server
- Http client
### Should have
- Documentation generated from Java Docs
- SQL client
### Could have
- Pipeline for automatic deployment
### Won't have

## Future releases
### v0.0.1
<i>Planned date: 2024.03.29</i> <b>DELAYED</b><br>
Package should be deployed on remote artifact repository and ready to use with Maven based projects along with dependency injection functionality.

### v0.0.2
<i>Planned date: 2024.04.26</i><br>
HTTP server should be available.

### v0.0.3
<i>Planned date: 2024.05.24</i><br>
External properties support and HTTP client should be available.

## FAQ
<b>Why?</b><br>
<i>
Inspired by https://100commitow.pl/.<br>
Attempt to implement something different from everyday business code.<br>
Learn how Spring Framework internally works.<br>
Learn what problems could appear during libraries implementation and how to face them.<br>
</i>

<b>What Summer Framework is?</b><br>
<i>
Java framework to fasten implementation process by reducing boilerplate code.<br>
</i>

<b>What Summer Framework is not?</b><br>
<i>
Production solution.<br>
Spring Framework replacement.
</i>

## Sources
- https://spring.io/
- https://github.com/TheCheerfulDev/injectinator
- https://dzone.com/articles/how-to-publish-artifacts-to-maven-central
- https://dev.to/julbrs/beginner-guide-to-maven-central-publishing-3jio
- https://central.sonatype.org/publish/publish-maven/
