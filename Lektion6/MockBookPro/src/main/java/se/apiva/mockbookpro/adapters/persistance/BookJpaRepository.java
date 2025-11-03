package se.apiva.mockbookpro.adapters.persistance;

import org.springframework.data.repository.CrudRepository;

public interface BookJpaRepository extends CrudRepository<BookJpaEntity,Long> {
}
