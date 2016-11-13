package nl.tuv.parser.repositories;

import nl.tuv.parser.domain.Manufacturer;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by kristisvaskys on 13/11/2016.
 */
public interface ManufacturerRepository extends CrudRepository<Manufacturer, Long> {
}
