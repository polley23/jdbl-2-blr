package com.gfg.jdbl_2_user_service_major_project;

import com.gfg.jdbl_2_user_service_major_project.Entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {
}
