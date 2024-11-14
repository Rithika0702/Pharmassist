package com.jsp.pharmassist.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jsp.pharmassist.repository.AdminRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService{
	
	private final AdminRepository adminRepository;

	public UserDetailServiceImpl(AdminRepository adminRepository) {
		super();
		this.adminRepository = adminRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username)  {

     return adminRepository.findByAdminEmail(username)
                           .map(UserDetailsImpl::new)
                           .orElseThrow(() -> new UsernameNotFoundException("Failed to Authenticate user"));
	}

}
