package com.thespot.bookings.security.repo;

import com.thespot.bookings.security.entity.Role;
import com.thespot.bookings.security.entity.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);

    List<User> findByFirstNameIgnoreCaseStartsWithAndRole(String startsWith, Role role, Sort sort);

    List<User> findByRole(Role role, Sort sort);

    @Transactional
    Integer deleteByUserId(String id);

    User findByUserId(String tpId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE cdui_sgm_user SET email = :email, first_name = :firstName, last_name = :lastName WHERE user_id = :userId", nativeQuery = true)
    Integer updateFields(@Param("email") String email,@Param("firstName") String firstName,@Param("lastName") String lastName,@Param("userId") String userId);

    List<User> findByRoleAndFirstNameIgnoreCaseStartsWith(Role role, String startsWith, Sort sortBy);

    boolean existsByEmail(String email);

    boolean existsByMobileNumber(String mobileNo);

    Optional<User> findByMobileNumber(String mobileNo);

    Optional<User> findFirstByMobileNumber(String mobileNo);
}

