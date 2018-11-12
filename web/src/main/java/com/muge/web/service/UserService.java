package com.muge.web.service;

import com.muge.web.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    void save(User user);

    Page<User> findAll(Pageable pageable);

    void delete(String id);

    boolean checkUserName(String userName);
}
