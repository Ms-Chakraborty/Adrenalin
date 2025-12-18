package com.titiksha.tickets.repositories;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.titiksha.tickets.domain.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User,UUID> {

}
