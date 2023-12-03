package ru.bucm.TestingApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bucm.TestingApp.models.Test;

public interface TestsRepository extends JpaRepository<Test, Integer> {
}
