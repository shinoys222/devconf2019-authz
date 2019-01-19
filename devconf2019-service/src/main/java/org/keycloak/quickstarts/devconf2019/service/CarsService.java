package org.keycloak.quickstarts.devconf2019.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author <a href="mailto:mposolda@redhat.com">Marek Posolda</a>
 */
@Component
public class CarsService {

    @Autowired
    private InMemoryCarsDB db;

    public CarRepresentation generateCarForUser(String userId, String username) {
        InMemoryCarsDB.Car car = db.giveRandomCarToUser(userId, username);
        return new CarRepresentation(car.getName(), car.getDescription(), null, car.getOwner());
    }


    // Key is ownerUsername, Values are all cars of this owner. Image is not set
    public Map<String, List<CarRepresentation>> getCars(String userId) {
        Map<String, List<CarRepresentation>> carsRep = new HashMap<>();

        db.getCarsWithOwner().forEach((InMemoryCarsDB.Car car) -> {
            String ownerUsername = car.getOwner().getOwnerUsername();

            List<CarRepresentation> ownerCars;
            if (!carsRep.containsKey(ownerUsername)) {
                carsRep.put(ownerUsername, new ArrayList<>());
            }

            carsRep.get(ownerUsername).add(new CarRepresentation(car.getName(), car.getDescription(), null,
                    car.getOwner()));
        });

        return carsRep;
    }



}
