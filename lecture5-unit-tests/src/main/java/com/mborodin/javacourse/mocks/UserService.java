package com.mborodin.javacourse.mocks;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String getUserFullName(long id) {
        User user = userRepository.findById(id);
        if (user == null) {
            return "Unknown User";
        }
        return user.firstName() + " " + user.lastName();
    }
}
