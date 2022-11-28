FROM openjdk:17-jre
LABEL authors="Piccin Célestin, Jorand Kévin"

COPY MockMock-1.4.1-SNAPSHOT.one-jar.jar /opt/app/MockMock.jar

# Pour le serveur SMTP
EXPOSE 25

# Pour l'interface web
EXPOSE 8282

CMD java -jar /opt/app/MockMock.jar
