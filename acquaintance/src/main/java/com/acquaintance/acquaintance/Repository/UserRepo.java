package com.acquaintance.acquaintance.Repository;

import com.acquaintance.acquaintance.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByName (String username);
}