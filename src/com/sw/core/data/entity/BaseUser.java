package com.sw.core.data.entity;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class BaseUser extends BaseEntity implements UserDetails {
	
	private static final long serialVersionUID = -5132612672760878653L;
	//spring安全鉴权验证必要属性，用户账号
    private String username;
    //核心代码调用属性，访问ip地址
    private String accessIp;
    //核心代码调用属性，系统样式目录
    private String style;
    
    private Collection<GrantedAuthority> grantedAuthority = null;
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	@Override
	public boolean isEnabled() {
		return true;
	}
	
	@Override
	public Collection<GrantedAuthority> getAuthorities() {
	    return  grantedAuthority;
	}
   
	public void setAuthorities(Collection<GrantedAuthority> grantedAuthority) {
		this.grantedAuthority = grantedAuthority;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public String getAccessIp() {
		return accessIp;
	}
	public void setAccessIp(String accessIp) {
		this.accessIp = accessIp;
	}

	@Override
	public String getPassword() {
		return null;
	}

	@Override
	public String getUsername() {
		return null;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}
	
}