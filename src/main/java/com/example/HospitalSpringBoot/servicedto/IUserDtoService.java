package com.example.HospitalSpringBoot.servicedto;

import com.example.HospitalSpringBoot.dtos.UserDto;
import com.example.HospitalSpringBoot.enums.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IUserDtoService {
        public List<UserDto> getAll();
        public UserDto getById(Long id);
        public List<UserDto> findAllByRole(Role role);
        public Page<UserDto> getAllSpecification(String username, String name, String surname, Pageable page);
}
