rm -f *.jar
mvn clean install -DskipTests
cp target/*.jar familyvideo.jar

docker-compose build && docker-compose up