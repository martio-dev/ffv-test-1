# ffv-test-1

## Getting started ###

Here there are instructions to execute and test application.

### Prerequisites ###

The code is available as [Maven](https://maven.apache.org/) project, hence `mvn` application must be available on the system.

Internet connection is required to allow Maven downloading necessary resources.

### Running ###

The pom file provides complete configuration to execute test as. Starting from base directory of repository, the basic command to execute main application is the following:

```bash
mvn clean compile exec:java -q -f ffv-test-1/pom.xml
```

equivalent to  

```bash
mvn clean compile exec:java -q -f ffv-test-1/pom.xml -Dexec.mainClass=it.mvtex.iws.t20170611.salestaxes.SalesTaxesSolution -Dexec.args=""
```

## Running the tests ##

```bash
mvn clean compile test -f ffv-test-1/pom.xml
```

## Built With ##

 * [Maven](https://maven.apache.org/) - Dependency Management
