package com.muge.web.repository;

import com.muge.web.entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<User, String> {

    int countUserByUserName(String userName);
}
