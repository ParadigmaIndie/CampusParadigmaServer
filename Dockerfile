FROM maven as builder
COPY ./ /home/app/
RUN cd /home/app && mvn package

FROM tomcat
COPY --from=builder /home/app/target/campusparadigma.war /usr/local/tomcat/webapps/
EXPOSE 8080



