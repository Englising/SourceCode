package org.englising.com.englisingbe.user.repository;


import org.englising.com.englisingbe.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserId(Long userId);
    Optional<User> findByEmail(String email);
    boolean existsByNicknameAndUserIdNot(String nickname, Long userId);

    int countByNicknameStartingWith(String nickname);



}
