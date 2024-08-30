package insurance.quote.app.domain.port.out;

public interface GeneratePolicy {
  void execute(String quoteId);
}
