package com.monnetpayments.challenge.users.domain.repository;

import com.monnetpayments.challenge.users.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByExternalId(Integer externalId);
}
