FROM ubuntu:20.04

RUN apt-get update
RUN apt-get install -y nginx

COPY ./frontend/build /usr/share/nginx/html

RUN rm /etc/nginx/nginx.conf   
COPY ./conf/nginx/nginx.conf /etc/nginx/nginx.conf

ENTRYPOINT [ "nginx", "-g", "daemon off;" ] 
