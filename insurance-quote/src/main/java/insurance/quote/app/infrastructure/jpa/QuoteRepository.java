package insurance.quote.app.infrastructure.jpa;

import insurance.quote.app.infrastructure.model.QuoteEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface QuoteRepository extends JpaRepository<QuoteEntity, String> {

  @EntityGraph(attributePaths = {"coverages", "customer"})
  Optional<QuoteEntity> findById(String id);

  @Query("SELECT q FROM QuoteEntity q LEFT JOIN FETCH q.coverages LEFT JOIN FETCH q.customer WHERE q.id = :id")
  QuoteEntity findByIdWithAssociations(String id);
}