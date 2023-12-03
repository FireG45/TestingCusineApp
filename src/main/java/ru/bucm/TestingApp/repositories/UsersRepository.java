package ru.bucm.TestingApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bucm.TestingApp.models.User;

public interface UsersRepository extends JpaRepository<User, Integer> {
}
