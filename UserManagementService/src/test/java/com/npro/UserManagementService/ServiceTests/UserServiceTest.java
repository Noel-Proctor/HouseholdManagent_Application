package com.npro.UserManagementService.ServiceTests;

import com.npro.UserManagementService.Repositories.UserRepository;
import com.npro.UserManagementService.Service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jmx.export.annotation.ManagedOperation;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserService userService;

    @Test
    void register_User_And_Persist_Password(){



    }

}
