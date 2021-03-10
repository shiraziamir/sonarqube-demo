FROM microsoft/dotnet:2.2.104-sdk AS sonarqube

# Install OpenJDK-8
RUN apt-get update && \
    apt-get install -y openjdk-8-jdk && \
    apt-get install -y ant && \
    apt-get clean;

# Fix certificate issues
RUN apt-get update && \
    apt-get install ca-certificates-java && \
    apt-get clean && \
    update-ca-certificates -f;

# Setup JAVA_HOME
ENV JAVA_HOME /usr/lib/jvm/java-8-openjdk-amd64/
RUN export JAVA_HOM

# Env variables
ENV NODE_VERSION 10.13.0
ENV NODE_DOWNLOAD_SHA b4b5d8f73148dcf277df413bb16827be476f4fa117cbbec2aaabc8cc0a8588e1

# Install node.js
RUN curl -SL "https://nodejs.org/dist/v${NODE_VERSION}/node-v${NODE_VERSION}-linux-x64.tar.gz" --output nodejs.tar.gz \
    && echo "$NODE_DOWNLOAD_SHA nodejs.tar.gz" | sha256sum -c - \
    && tar -xzf "nodejs.tar.gz" -C /usr/local --strip-components=1 \
    && rm nodejs.tar.gz \
    && ln -s /usr/local/bin/node /usr/local/bin/nodejs

# Get required packages for sonar scanner
RUN apt-get update && apt-get -y install curl bash unzip yarn bzip2

WORKDIR /root

ENV LATEST='sonar-scanner-cli-3.3.0.1492-linux.zip'

# Get & install sonar scanner
RUN env && \
curl -OL 'https://binaries.sonarsource.com/Distribution/sonar-scanner-cli/'$LATEST && \
mkdir sonar_scanner && unzip -d sonar_scanner $LATEST && mv sonar_scanner/* sonar_home && \
rm -rf sonar_scanner $LATEST

# Add sonar scanner to PATH
ENV SONAR_RUNNER_HOME=/root/sonar_home
ENV PATH ${SONAR_RUNNER_HOME}/bin:$PATH

ARG SONAR_HOST
ARG SONAR_LOGIN_TOKEN

# make temporary folder for seed analysis for javascript scanner
WORKDIR /root/temp1
RUN mkdir src
RUN touch src/test.js
# Init sonarscanner cache with plugins
RUN sonar-scanner -Dsonar.host.url=$SONAR_HOST -Dsonar.login=$SONAR_LOGIN_TOKEN -Dsonar.analysis.mode=preview -Dsonar.projectKey="pluginsSeedJS" -Dsonar.sources="src"
RUN rm /root/temp1 -rf

WORKDIR /root

# Remove temporary folder

