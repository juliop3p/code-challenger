package insurance.quote.app.domain.repository;

import insurance.quote.app.domain.model.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface QuoteRepository extends CrudRepository<Quote, Long> {
    Quote save(Quote quote);
    Quote findById(String id);
}