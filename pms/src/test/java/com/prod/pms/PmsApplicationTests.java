//package com.prod.pms;
//
//import com.prod.pms.constants.Role;
//import com.prod.pms.domain.user.entity.UserInfo;
//import com.prod.pms.domain.user.repository.UserInfoRepository;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@SpringBootTest
//class PmsApplicationTests {
//
//	@Autowired
//	private PasswordEncoder passwordEncoder;
//
//	@Autowired
//	private UserInfoRepository userInfoRepository;
//	@Test
//	void contextLoads() {
//
//		List<Role> roles = new ArrayList<>();
//		roles.add(Role.SUPER_ADMIN);
//
//		UserInfo userInfo = UserInfo
//				.builder()
//				.userId("tester")
//				.userPassword(passwordEncoder.encode("test123"))
//				.userEmail("ekgnsl2002@gmail.com")
//				.userPhone("010-1234-5678")
//				.userBirth("1994-06-02")
//				.userKorName("테스터")
//				.roles(roles)
//				.companyId("6107151380")
//				.useFlag("Y")
//				.build();
//
//		userInfoRepository.save(userInfo);
//
//
//
//	}
//
//}
