package org.mosaic.hub.domain.repository;


import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.StringExpression;
import java.util.Optional;
import org.mosaic.hub.domain.model.Hub;
import org.mosaic.hub.domain.model.QHub;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

public interface HubRepository extends JpaRepository<Hub, Long>,
    QuerydslPredicateExecutor<Hub>,
    QuerydslBinderCustomizer<QHub> {

  Optional<Hub> findByUuid(String uuid);

  @Override
  default void customize(QuerydslBindings bindings, QHub hub) {
    bindings.excluding(hub.id);

    bindings.bind(hub.name).all((key, values) -> {
      BooleanBuilder booleanBuilder = new BooleanBuilder();
      values.forEach(value -> booleanBuilder.or(key.containsIgnoreCase(value)));
      return Optional.of(booleanBuilder);
    });

    bindings.bind(hub.address).first(StringExpression::containsIgnoreCase);
  }
}
