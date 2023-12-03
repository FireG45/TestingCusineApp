package ru.bucm.TestingApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bucm.TestingApp.models.User;
import ru.bucm.TestingApp.repositories.UsersRepository;

import java.util.List;

@Service
public class UsersService {

    final private UsersRepository usersRepository;

    @Autowired
    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public List<User> findAll() {
        return usersRepository.findAll();
    }
}
