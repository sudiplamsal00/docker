function base_image_creator() {
    docker build -t random_name:1.0 -f dockerfile.creator
}


BASE_FILE=$1
echo "Enter yes if you want to build the "