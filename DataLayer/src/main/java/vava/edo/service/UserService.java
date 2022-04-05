package vava.edo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import vava.edo.model.exeption.UserNotFoundException;
import vava.edo.model.Role;
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
     * @param userId    user ID you want to chceck
     * @return          true / false
     */
    public boolean isAdmin(int userId) {
        return getUser(userId).getUserRole().isAdminRights();
    }


    /**
     * Metthod finds user by its username
     * @param username  username of user you wan to find
     * @return          found user by username
     */
    public User getUserByUserName(String username) {
        if (username == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username is none.");
        }
        return userRepository.findByUsername(username);
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
     * Method converts DTO object to User object, finds wanted role for user if exists
     * and saves it to database
     * @param userDto   user Data Transfer Object you want to converts to user
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
