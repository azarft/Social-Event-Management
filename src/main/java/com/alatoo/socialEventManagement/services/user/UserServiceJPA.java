package com.alatoo.socialEventManagement.services.user;

import com.alatoo.socialEventManagement.dto.UserDTO;
import com.alatoo.socialEventManagement.entities.User;
import com.alatoo.socialEventManagement.mappers.UserMapper;
import com.alatoo.socialEventManagement.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceJPA implements UserService{
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceJPA(UserRepository userRepository, UserMapper userMapper){
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDTO> findAllUsers() {
        List<User> users = (List<User>) userRepository.findAll();
        return users.stream()
                .map(userMapper::userToUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UserDTO> findUserByID(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return Optional.ofNullable(
                userMapper.userToUserDto(optionalUser.orElse(null))
        );
    }

    @Override
    public UserDTO saveUser(UserDTO dto) {
        User savedUser = userRepository.save(userMapper.userDtoToUser(dto));
        return userMapper.userToUserDto(savedUser);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
