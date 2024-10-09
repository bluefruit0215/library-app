package com.group.libraryapp.service.user;

import com.group.libraryapp.domain.user.User;
import com.group.libraryapp.domain.user.UserRepository;
import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceV2 {
    private final UserRepository userRepository;

    public UserServiceV2(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 아래 있는 함수가 시작될 때 start transaction;을 해준다 (트랜잭션을 시작)
    // 함수가 예외 없이 잘 끝났다면 commit
    // 혹시라도 문제가 있다면 rollback
    @Transactional
    public void saveUser(UserCreateRequest request){
        User u = userRepository.save(new User(request.getName(), request.getAge()));
        //throw new IllegalArgumentException();
    }

    @Transactional(readOnly = true)
    public List<UserResponse> getUsers(){
        List<User> users = userRepository.findAll();
//        return users.stream()
//                .map(user -> new UserResponse(user.getId(), user.getName(), user.getAge()))
//                .collect(Collectors.toList());

        return users.stream()
                .map(UserResponse::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void updateUser(UserUpdateRequest request){
        // select * from user where id = ?;
        // Optional<User>
        User user = userRepository.findById(request.getId())
                .orElseThrow(IllegalArgumentException::new);

        user.updateName(request.getName());
        //userRepository.save(user);  // 영속성 컨텍스트가 적용되어 생략해도 저장이 된다
    }

    @Transactional
    public void deleteUser(String name) {
        // SELECT * FROM user
        User user = userRepository.findByName(name).orElseThrow(IllegalArgumentException::new);
//        if(!userRepository.existsByName(name)) {
//            throw new IllegalArgumentException();
//        }
//
//        User user = userRepository.findByName(name);

        userRepository.delete(user);
    }

}
