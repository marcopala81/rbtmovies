# Movies Comparator README.md

## Prerequisites
### Java 7 SDK
- REQUIRED: Download and configure the Java 7 SDK (the development kit, not the JRE)

http://www.oracle.com/technetwork/java/javase/downloads/jdk7-downloads-1880260.html
- REQUIRED: Create an environment variable pointing to the Java SDK dir
- Example: JAVA_HOME=C:\Program Files\Java\jdk1.7.0_79
- REQUIRED: Add the Java home to the windows PATH variable
- Example: %PATH%;%JAVA_HOME%\bin (append to path)
- Check configuration with 'java -version' on the CMD console

### Maven 3
- REQUIRED: Download and configure Maven 3

https://maven.apache.org/download.cgi
- REQUIRED: Create an environment variable pointing to the Maven dir
- Example: M2_HOME=C:\Programs\apache-maven-3.3.9
- REQUIRED: Add the Maven home to the windows PATH variable
- Example: %PATH%;%M2_HOME%\bin (append to path)
- RECOMMENDED: Configure the <localRepository> node in the %M2_HOME%\config\settings.xml
- Example: C:\Programs\apache-maven-3.3.9\m2repo
- Check configuration with 'mvn -version' on the CMD console

### Git binaries (required for the 'gitlog' profile)
- REQUIRED: Download and configure Git for Windows

https://git-for-windows.github.io/
- REQUIRED: Create an environment variable pointing to the Git dir
- Example: GIT_HOME=C:\Program Files\Git\cmd
- REQUIRED: Add the Git home to the windows PATH variable
- Example: %PATH%;%GIT_HOME% (append to path);
- Check with 'git --version' on the CMD console

## Compiling
### Sources
- REQUIRED: Clone Github repository at the root level ('rbtmovies')
- Example: cd C:\dev\projects
- Example: git clone https://github.com/marcopala81/rbtmovies -o newdir

### CheckOut base directory
- REQUIRED: Update parent POM with the actual checkout directory under 'project.checkout.dir'
- Example: C:\dev\projects\newdir

### Compiling and building sources
- Example: cd C:\dev\projects\newdir\movies-comparator
- Example: mvn clean install [-DskipTests]

### Generate the site for the project
- Example: mvn site-deploy [-DskipTests] [-P gitlog]

## Running with Jetty
### Web Module
- REQUIRED: Launch from the Web Module folder
- Example: cd C:\dev\projects\newdir\movies-comparator\movies-comparator-web
- Example: mvn jetty:run
- Application will be served on default address: http://localhost:8080
