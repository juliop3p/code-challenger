package insurance.quote.app.infrastructure.jpa;

import insurance.quote.app.infrastructure.model.QuoteEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuoteRepository extends CrudRepository<QuoteEntity, Long> {
}