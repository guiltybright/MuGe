package com.muge.web.service.impl;

import com.muge.web.entity.User;
import com.muge.web.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl {

    @Resource
    private UserRepository userRepository;

    @Transactional(readOnly = false)
    public void save(User user) {
        this.userRepository.save(user);
    }
}
