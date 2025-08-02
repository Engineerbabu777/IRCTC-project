package ticket.booking.services;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ticket.booking.entities.User;

public class UserBookingService {

    private User user;
    private List<User> userList;

    private ObjectMapper objectMapper = new ObjectMapper();
    private static final String USERS_PATH = "app/src/main/java/localDB/users.json";

    public UserBookingService(User user1) throws IOException {
        this.user = user1;
        File users = new File(USERS_PATH);

        userList = objectMapper.readValue(users, new TypeReference<List<User>>() {
        });
    }

    public Boolean loginUser() {
          Optional<User> foundUser = userList.stream().filter(user1 -> {
            return user1.getName().equals(user.getName()) && UserServiceUtil.checkPassword(user1.getPassword(), user.getPassword()).findFirst();
          })

          return foundUser.isPresent();
    }

    public Boolean signUp(User user1) {
        try {
            userList.add(user1);
            // saveUserListToFile();
            return Boolean.TRUE;
        } catch (Exception e) {
            return Boolean.FALSE;
        }
    }

}
