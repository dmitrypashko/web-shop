package com.gmail.dmitrypashko.dmitry;

import com.gmail.dmitrypashko.dmitry.model.User;
import com.gmail.dmitrypashko.dmitry.modelDTO.UserDTO;

import java.util.List;

public interface IUserService {


    void saveUser(User user);

    User getUserByEmail(String email);

    List<UserDTO> getAllUsers(int pagination);

    List<Integer> getPaginationList();

    void changeRoleUser(Long id, String role);

    void changeStatusUser(Long id, String status);


}
