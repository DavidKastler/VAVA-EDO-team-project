package vava.edo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import vava.edo.model.Role;
import vava.edo.schema.UserLogin;
import vava.edo.schema.UserRegister;
import vava.edo.repository.UserRepository;
import vava.edo.model.User;

import java.util.List;

/**
 * Service that operates over users database table
 */
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
     * Method that checks if user has admin privileges
     * @param userId    user ID you want to check
     * @return          true / false
     */
    public boolean isAdmin(int userId) {
        return getUser(userId).getUserRole().isAdminRights();
    }


    /**
     * Method to check password of user
     * @param user      user you want to check
     * @param password  password you want to validate
     */
    public void checkPassword(User user, String password) {
        if (password == null || password.length() == 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password for given user is null");
        }
        if (!password.equals(user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Password for given user is incorrect");
        }
    }


    /**
     * Method to check password of user
     * @param userId      user ID you want to check
     * @param password  password you want to validate
     */
    public void checkPassword(int userId, String password) {
        checkPassword(getUser(userId), password);
    }


    /**
     * Method finds user by its username
     * @param username  username of user you want to find
     * @return          found user by username
     */
    public User getUserByUserName(String username) {
        if (username == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username is none.");
        }
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found.");
        }

        return user;
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
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }


    /**
     * Method that updates found user by ID based on given UserDto class parameters
     * @param userId            user ID you want to change
     * @param updatedUserDto    userDto class with updated parameters
     * @return                  updated user
     */
    @Transactional
    public User editUser(int userId, UserRegister updatedUserDto) {
        User userToEdit = getUser(userId);
        Role updatedRole = roleService.getRole(updatedUserDto.getRoleId());

        userToEdit.setUsername(updatedUserDto.getUsername());
        userToEdit.setPassword(updatedUserDto.getUsername());
        userToEdit.setUserRole(updatedRole);

        return userToEdit;
    }


    /**
     * Method that updates found user by ID based on given UserDto class parameters
     * @param userId            user ID you want to change
     * @param updatedUserDto    userDto class with updated parameters
     * @return                  updated user
     */
    @Transactional
    public User editUser(int userId, UserLogin updatedUserDto) {
        User userToEdit = getUser(userId);

        userToEdit.setUsername(updatedUserDto.getUsername());
        userToEdit.setPassword(updatedUserDto.getPassword());

        return userToEdit;
    }


    /**
     * Method converts DTO object to User object, finds wanted role for user if exists
     * and saves it to database
     * @param userDto   user Data Transfer Object you want to convert to user
     * @return          created user
     */
    public User addUser(UserRegister userDto) {
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
