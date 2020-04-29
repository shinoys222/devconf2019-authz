# devconf-2019-authz
Keycloak/Authorization SpringBoot example for devconf 2019

# How to have it running

1) Start Keycloak:
```
cd $KEYCLOAK_HOME/bin
./standalone.sh -Djboss.socket.binding.port-offset=100
```

To run keycloak via docker-compose use this docker-compose file - https://github.com/keycloak/keycloak-containers/blob/master/docker-compose-examples/keycloak-postgres.yml

add environment var

```
        JAVA_OPTS: -server -Xms64m -Xmx512m -XX:MetaspaceSize=96M -XX:MaxMetaspaceSize=256m -Djava.net.preferIPv4Stack=true -Djboss.modules.system.pkgs=org.jboss.byteman -Djava.awt.headless=true  --add-exports=java.base/sun.nio.ch=ALL-UNNAMED --add-exports=jdk.unsupported/sun.misc=ALL-UNNAMED --add-exports=jdk.unsupported/sun.reflect=ALL-UNNAMED -Dkeycloak.profile.feature.upload_scripts=enabled
```

and update ports as below
```
      ports:
        - 8180:8080
```


2) Import realm cars-realm.json

3) Build
```
    mvn clean install
```    

4) Run CarsServiceApp and CarsApp from IDE as below
```
cd devconf2019-service
mvn spring-boot:run   
```

and 

```
cd devconf2019-app
mvn spring-boot:run  
```

5) Go to http://localhost:8080/app and login as alice/alice or other user (See cars-realm.json for the users/passwords).

6) Switching between branches `master` for not-UMA setup (demo part1) and `authz-backup` for UMA setup (demo part2)



