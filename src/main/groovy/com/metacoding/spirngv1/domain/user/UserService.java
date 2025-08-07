package com.metacoding.spirngv1.domain.user;

import com.metacoding.spirngv1.DTO.JoinRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserJpaRepository userJpaRepository;

    @Transactional
    public void 회원가입(JoinRequestDTO reqDTO) {

        //1.username 중복확인

        //2.insert
    }
}
