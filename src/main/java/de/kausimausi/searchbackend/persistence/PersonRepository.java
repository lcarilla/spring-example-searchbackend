package de.kausimausi.searchbackend.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    @Query("SELECT e FROM Person e WHERE (:firstName is null OR e.firstName = :firstName) " +
            "AND (:lastName is null OR e.lastName = :lastName)")
    Page<Person> findByAttributes(Pageable pageable,
                                  @Param("firstName") String firstName,
                                  @Param("lastName") String lastName);
}
