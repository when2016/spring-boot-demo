# 拉取JDK8的系统镜像
FROM java:8
MAINTAINER wanghongen <329016414@qq.com>

# 设置时区
ENV TZ=Asia/Shanghai
# 时区写入系统文件
RUN ln -snf /usr/share/zoneinfo/$TZ  /etc/localtime && echo $TZ > /etc/timezone

VOLUME /tmp
# 加入打包好的jar文件(xxxxx改为自己文件名)
ADD spring-boot-demo-1.0-SNAPSHOT.jar app.jar

ENTRYPOINT ["java","-jar","app.jar"]

ENV SERVER_PORT '80'
#CMD java -jar app.jar

