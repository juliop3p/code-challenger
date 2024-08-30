package insurance.quote.app.infrastructure.jpa;

import insurance.quote.app.infrastructure.model.QuoteEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface QuoteRepository extends MongoRepository<QuoteEntity, String> {
}