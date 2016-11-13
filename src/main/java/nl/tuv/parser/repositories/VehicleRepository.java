package nl.tuv.parser.repositories;

import nl.tuv.parser.domain.Vehicle;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by kristisvaskys on 13/11/2016.
 */
public interface VehicleRepository extends CrudRepository<Vehicle, Long> {
}
