FROM centos:centos7
RUN yum install -y epel-release
RUN yum install -y java-1.8.0-openjdk which

ADD ./build/install/scumm-api-boot /opt/scumm/scumm-api


WORKDIR /opt/scumm/scumm-api
ENTRYPOINT ["./bin/scumm-api"]


