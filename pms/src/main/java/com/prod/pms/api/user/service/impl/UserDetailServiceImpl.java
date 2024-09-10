package com.prod.pms.api.user.service.impl;

import com.prod.pms.domain.user.repository.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {
    private final UserInfoRepository userInfoRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userInfoRepository.findById(username).orElseThrow(()-> new UsernameNotFoundException("NO EXIST USER"));
    }



}
