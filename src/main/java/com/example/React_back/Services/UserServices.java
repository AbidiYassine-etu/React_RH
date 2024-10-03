package com.example.React_back.Services;

import com.example.React_back.Models.User;

import java.util.List;

public interface UserServices {
    User findUserByID(Long id);
    User addUser(User user);
    User updateUser(User user);
    void deleteUser(Long id);
    List<User> findAllUsers(); // Ajoute cette méthode pour récupérer tous les utilisateurs
}
