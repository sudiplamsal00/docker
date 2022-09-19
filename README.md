This project is intened for testing docker image for java jar/war file. 

Build process:
1. Build the openjdk-mvn file for the maven image creation.
    docker build -t custom_maven:3.8.6 -f dockerfile.openjdk-mvn
2. Build the runtime image for base runtime image creation.
    docker build -t <JAVA_FILE_NAME>_installer:<TAG_VERSION> -f dockerfile.runtime --build-arg BASE_FILE=<JAVA_FILE_NAME> .
    note: <JAVA_FILE_NAME>=war or jar 
          <TAG_VERSION>=1.0
3. Build the final image.
    docker build -t <Project_name>:<TAG_VERSION> -f dockerfile.java --build-arg BASE_FILE=<JAVA_FILE_NAME> .

################## OR ########################

* Run imagecreator.sh.
    ./imagecreator.sh $BASE_FILE           # BASE_FILE is jar or war.
        