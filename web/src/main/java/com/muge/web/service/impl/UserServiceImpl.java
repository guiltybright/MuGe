package com.muge.web.service.impl;

import com.muge.web.entity.User;
import com.muge.web.repository.UserRepository;
import com.muge.web.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository userRepository;

    @Transactional(readOnly = false)
    public void save(User user) {
        this.userRepository.save(user);
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return this.userRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(String id) {
        this.userRepository.deleteById(id);
    }

    @Override
    public boolean checkUserName(String userName) {
        return this.userRepository.countUserByUserName(userName) > 0;
    }
}
