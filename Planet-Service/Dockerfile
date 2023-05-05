# The FROM command will define our base image that we're working off. 
# We can build one from scratch or we can use an existing one. 
#FROM defines what our base image will be
FROM maven:3.8.5-openjdk-17

#Copy all of our files into our docker image 
COPY ./ ./ 

#RUN will execute a terminal command for us 
RUN mvn clean package 

#CMD/ENTRYPOINT - this will be our final execution, for deployment 
CMD ["java", "-jar", "Planet-Service-0.0.1-SNAPSHOT.jar"] 
