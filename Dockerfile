FROM url... /appdynamics:v21.1.1.31776 AS appdynamics
FROM url ... /jdk-11:latest
COPY --from=appdynamics var/appdynamics/ /var/appdynamics/
ENV TZ="Africa/Johannesburg"
RUN mkdir /app
WORKDIR /app
RUN apk add bash curl --update
COPY target/app.jar bootstrap.sh ./
EXPOSE 8080
CMD ["/bin/bash", "/app/bootstrap.sh"]
