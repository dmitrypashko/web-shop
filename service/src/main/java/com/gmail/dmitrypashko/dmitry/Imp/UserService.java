package com.gmail.dmitrypashko.dmitry.Imp;

import com.gmail.dmitrypashko.dmitry.IUserRepository;
import com.gmail.dmitrypashko.dmitry.IUserService;
import com.gmail.dmitrypashko.dmitry.model.Role;
import com.gmail.dmitrypashko.dmitry.model.StatusUser;
import com.gmail.dmitrypashko.dmitry.model.User;
import com.gmail.dmitrypashko.dmitry.modelDTO.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements IUserService {
    @Autowired
    private IUserRepository userDao;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Transactional
    @Override
    public void saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRole(Role.ROLE_USER);
        user.setStatusUser(StatusUser.STATUS_UNBLOCKED);
        userDao.save(user);
    }
    @Transactional
    @Override
    public User getUserByEmail(String email) {
        return userDao.getUser(email);
    }

    @Transactional
    @Override
    public List<UserDTO> getAllUsers(int pagination) {
        List<User> users = userDao.getAll(pagination);
        List<UserDTO> usersDTO = new ArrayList<>();
        for (User user : users) {
            UserDTO userDTO = new UserDTO();
            userDTO.setId(user.getId());
            userDTO.setEmail(user.getEmail());
            userDTO.setName(user.getName());
            userDTO.setRole(user.getRole().name());
            userDTO.setStatus(user.getStatusUser().name());
            usersDTO.add(userDTO);
        }

        return usersDTO;
    }

    @Override
    @Transactional
    public List<Integer> getPaginationList() {
        return userDao.getPaginationList();
    }

    @Override
    @Transactional
    public void changeRoleUser(Long id, String role) {
        userDao.changeRoleUser(id,role);
    }

    @Override
    @Transactional
    public void changeStatusUser(Long id, String status) {
        userDao.changeStatusUser(id,status);
    }


}
