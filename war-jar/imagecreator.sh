function image_creator() {
    base=$1
    echo "Creating maven runtime image"
    docker build -t custom_maven:3.8.6 -f dockerfile.openjdk-mvn .
    echo "Creating base image for deployment"
    docker build -t base_installer:1.0 --build-arg BASE_FILE=$base -f dockerfile.base .
    echo "creating deployment image"
    echo ${base}
    docker build -t ${base}_image:1.0 --build-arg BASE_FILE=$base -f dockerfile.java .
}

BASE_FILE=$1
echo "Creating image for ${BASE_FILE}"
image_creator $BASE_FILE

