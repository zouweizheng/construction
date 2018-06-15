FROM gitlab.nj-itc.com.cn:4000/root/alpine:latest
ENV JAVA_HOME="/usr/lib/jvm/java-1.8-openjdk" JAVA_OPTS="-Xmx960m"
RUN apk -U add git maven openjdk8 tzdata && \
    ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && \
    echo "Asia/Shanghai" > /etc/timezone&& \
    wget http://10.251.84.231/centos/Docker/settings.xml&&cp settings.xml /usr/share/java/maven-3/conf/&&\
    git clone https://root:ahww2014@gitlab.nj-itc.com.cn/root/paas.git\
    &&cd paas/activiti&&mvn package&&cp /paas/activiti/target/activiti* /&&rm -rf /cmh&&rm -rf ~/.m2&&apk del git maven\
    &&mv /activiti*\.jar /activiti.jar
EXPOSE 10021
CMD java -jar activiti.jar
