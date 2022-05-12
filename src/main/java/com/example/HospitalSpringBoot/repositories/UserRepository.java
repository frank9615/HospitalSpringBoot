package com.example.HospitalSpringBoot.repositories;

import com.example.HospitalSpringBoot.entities.User;
import com.example.HospitalSpringBoot.enums.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.lang.NonNull;

import java.util.List;

public interface UserRepository extends JpaSpecificationExecutor, PagingAndSortingRepository<User, Long> {
    public User findByUsername(String username);

    List<User> findByRoleEquals(@NonNull Role role);

    Page<User> findAll(Specification spec, Pageable pageable);

}
