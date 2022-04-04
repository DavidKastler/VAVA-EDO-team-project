package vava.edo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vava.edo.model.exeption.UserNotFoundException;
import vava.edo.model.Role;
import vava.edo.model.dto.UserDto;
import vava.edo.repository.UserRepository;
import vava.edo.model.User;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;

    @Autowired
    public UserService(UserRepository userRepository, RoleService roleService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
    }


    /**
     * Method returns all users in database
     * @return  list of all users
     */
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    /**
     * Method finds user by its ID, if it is not found throws exception
     * @param userId    user ID you want to find
     * @return          found user
     */
    public User getUser(int userId) {
        return userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException(userId));

    }


    /**
     * Method converts DTO object to User object, finds wanted role for user if exists
     * and saves it to database
     * @param userDto   user Data Transfer Object you want to converts to user
     * @return          created user
     */
    public User addUser(UserDto userDto) {
        Role userRole = roleService.getRole(userDto.getRoleId());
        User user = User.from(userDto);
        user.setUserRole(userRole);

        userRepository.save(user);
        return user;
    }


    /**
     * Method for deleting users from database by ID
     * @param userId    user ID you want to delete
     * @return          deleted user
     */
    public User deleteUser(int userId) {
        User user = getUser(userId);
        System.out.println(user.toString());
        userRepository.delete(user);
        return user;
    }

}
