package org.tastydrinkysquish.drink.repository;

import org.tastydrinkysquish.drink.entity.DrinkUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<DrinkUser, Long> {

    DrinkUser findByUserName(String name);
    boolean existsByUserName(String userName);
}
