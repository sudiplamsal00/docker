This project is intened for testing docker image for java jar/war file. 

Build process:
1. Build the openjdk-mvn file for the maven image creation.
    docker build -t custom_maven:3.8.4 -f dockerfile.openjdk-mvn
2. Build the runtime image for base runtime image creation.
    docker build -t <JAVA_FILE_NAME>_installer:<tag-version> -f dockerfile.runtime --build-arg BASE_FILE=<JAVA_FILE_NAME> .
    note: <JAVA_FILE_NAME>=war or jar 
          <tag>=1.0
3. Build the final image.
    docker build -t <Project_name>:<tag-version> -f dockerfile.java --build-arg BASE_FILE=<JAVA_FILE_NAME> .
