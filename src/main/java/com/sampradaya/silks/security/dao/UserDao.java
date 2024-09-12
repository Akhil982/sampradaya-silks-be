package com.sampradaya.silks.security.dao;

import com.sampradaya.silks.security.entity.Role;
import com.sampradaya.silks.security.entity.User;
import com.sampradaya.silks.security.repo.UserRepo;
import com.sampradaya.silks.security.util.DatabaseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserDao {

    private final UserRepo userRepo;

    public List<User> findByFirstNameIgnoreCaseStartsWithAndRole(String startsWith, Role role, String sortBy) {
        Sort sort = DatabaseUtil.buildSortObject(sortBy);
        return userRepo.findByFirstNameIgnoreCaseStartsWithAndRole(startsWith, role, sort);
    }

    public List<User> findByRole(Role role, String sortBy) {
        Sort sort = DatabaseUtil.buildSortObject(sortBy);
        return userRepo.findByRole(role, sort);
    }

    @Transactional
    public Integer deleteById(String id) {
        return userRepo.deleteByUserId(id);
    }

    public User save(User tpUser) {
        return userRepo.save(tpUser);
    }

    public List<User> saveAll(List<User> spocList) {
        return userRepo.saveAll(spocList);
    }

    public User findById(String tpId) {
        return userRepo.findByUserId(tpId);
    }

    @Transactional
    public Integer updateFields(String email, String firstName, String lastName, String userId) {
        return userRepo.updateFields(email, firstName, lastName, userId);
    }

    public List<User> findByRoleAndFirstNameIgnoreCaseStartsWith(Role role, String startsWith, String sortBy) {
        Sort sort = DatabaseUtil.buildSortObject(sortBy);
        return userRepo.findByRoleAndFirstNameIgnoreCaseStartsWith(role, startsWith, sort);
    }

    public boolean existsByEmail(String email) {
        return userRepo.existsByEmail(email);
    }

    public boolean existsByMobileNumber(String mobileNum) {
        return userRepo.existsByMobileNumber(mobileNum);
    }

    public Optional<User> findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    public Optional<User> findByMobileNumber(String mobileNo) {
        return userRepo.findByMobileNumber(mobileNo);
    }

    public Optional<User> findFirstByMobileNumber(String mobileNo) {
        return userRepo.findFirstByMobileNumber(mobileNo);
    }
}
