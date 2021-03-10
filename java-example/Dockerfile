FROM sonar-scanner-image:latest AS sonarqube_scan

WORKDIR /app

COPY . .

RUN ls -list

RUN mvn sonar:sonar -Dsonar.host.url=http://localhost:9000 -Dsonar.login=$SONAR_LOGIN_TOKEN
