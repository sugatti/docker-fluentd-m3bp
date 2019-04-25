FROM centos:centos7

ENV ASAKUSA_HOME /workspace/asakusa
ENV JAVA_HOME /usr/lib/jvm/java
ENV PATH ${PATH}:${JAVA_HOME}/bin:${ASAKUSA_HOME}/bin
ADD ./workspace /workspace
ADD ./directio /directio
WORKDIR /workspace

RUN yum install -y cmake make gcc-c++ hwloc java-1.8.0-openjdk java-1.8.0-openjdk-devel

CMD ["bash", "/workspace/build.sh"]
