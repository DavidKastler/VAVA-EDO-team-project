package vava.edo.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import vava.edo.model.Role;
import vava.edo.schema.users.UserLogin;
import vava.edo.schema.users.UserEdit;
import vava.edo.repository.UserRepository;
import vava.edo.model.User;

import java.util.List;

/**
 * Service that operates over users database table
 */
@Service
@Log4j2
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
     * Method that checks if user has account manager privileges
     * @param userId    user ID you want to check
     * @return          true / false
     */
    public boolean isAccountManager(int userId) {
        Role userRole = getUser(userId).getUserRole();
        return userRole.isAdminRights() || userRole.isManagerRights();
    }

    /**
     * Method that checks if user has team leader privileges
     * @param userId    user ID you want to check
     * @return          true / false
     */
    public boolean isTeamLeader(int userId) {
        Role userRole = getUser(userId).getUserRole();
        return userRole.isTeamLeaderRights() || userRole.isAdminRights() || userRole.isManagerRights();
    }

    /**
     * Method that checks if user has at least pleb privileges
     * @param userId    user ID you want to check
     * @return          true / false
     */
    public boolean isPleb(int userId) {
        Role userRole = getUser(userId).getUserRole();

        return userRole.isTodoAccessRights() || userRole.isTeamLeaderRights()
                || userRole.isAdminRights() || userRole.isManagerRights();
    }

    /**
     * Method to check password of user
     * @param user      user you want to check
     * @param password  password you want to validate
     */
    public void checkPassword(User user, String password) {
        if (password == null || password.length() == 0) {
            log.warn("Password must not be empty.");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password for given user is null");
        }
        if (!password.equals(user.getPassword())) {
            log.warn("Incorrect username or password.");
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
            log.warn("User name must not be empty.");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username is none.");
        }
        User user = userRepository.findByUsername(username);

        if (user == null) {
            log.warn("Incorrect username or password.");
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
    public User editUser(int userId, UserEdit updatedUserDto) {
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

        log.info("Editing {}'s profile.", userToEdit.getUsername());
        userToEdit.setUsername(updatedUserDto.getUsername());
        userToEdit.setPassword(updatedUserDto.getPassword());

        return userToEdit;
    }

    /**
     * Method converts DTO object to User object, finds wanted role for user if exists
     * and saves it to database
     * @param userEdit   user Data Transfer Object you want to convert to user
     * @return          created user
     */
    public User addUser(UserLogin userEdit) {
        Role userRole = roleService.getRole(4);
        User user = User.from(userEdit);
        user.setUserRole(userRole);
        log.info("Saving new user {} with role id {} into database.", userEdit.getUsername(), userRole.getRId());
        log.info("User {} successfully registered.", user.getUsername());
        return userRepository.save(user);
    }

    /**
     * Method for deleting users from database by ID
     * @param userId    user ID you want to delete
     * @return          deleted user
     */
    public User deleteUser(int userId) {
        User user = getUser(userId);

        log.info("Deleting user {} from database.", user);
        userRepository.delete(user);
        log.info("User {} successfully deleted.", user.getUsername());
        return user;
    }
}
