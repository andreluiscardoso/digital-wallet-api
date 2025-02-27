package com.andreluiscardoso.digitalwallet.domain.service;

import com.andreluiscardoso.digitalwallet.domain.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService  {

    private final UserRepository userRepository;
    
}
