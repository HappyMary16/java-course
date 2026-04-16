package com.mborodin.javacourse.mocks;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void getUserFullName_userReturned() {
        // GIVEN
        long userId = 1L;
        User mockUser = new User(userId, "Олексій", "Петренко");
        when(userRepository.findById(userId)).thenReturn(mockUser);

        // WHEN
        String fullName = userService.getUserFullName(userId);

        // THEN
        assertEquals("Олексій Петренко", fullName);
        verify(userRepository).findById(userId);
    }

    @Test
    void getUserFullName_unknownUserReturned() {
        // GIVEN
        when(userRepository.findById(anyLong())).thenReturn(null);

        // WHEN
        String result = userService.getUserFullName(1);

        // THEN
        assertEquals("Unknown User", result);
        verify(userRepository).findById(anyLong());
    }

    @Test
    void getUserFullName_exception() {
        // GIVEN
        when(userRepository.findById(anyLong())).thenThrow(new RuntimeException());

        // WHEN -> THEN
        assertThrows(RuntimeException.class, () -> userService.getUserFullName(1));
        verify(userRepository).findById(anyLong());
    }
}