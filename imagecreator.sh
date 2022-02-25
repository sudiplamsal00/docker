function base_image_creator() {
    docker build -t random_name:1.0 -f dockerfile.creator
}


BASE_FILE=$1
if [[ $BASE_FILE == "war" ]]
then 
    echo "Checking maven openjdk image"
    docker images | grep custom_maven
    
    echo "Checking war base installer image in local env"
    docker images | grep $BASE_FILE_installer
    echo "do you want to recreate the base_image [y/n]?"
    read input
    if [ "$input" == "y" ]; then
        echo "Please specify the tag version:" 
        read tag
        docker build -t $BASE_FILE_installer:$tag -f dockerfile.openjdk-mvn .
    else
        echo "Using already existing image......."
    fi
