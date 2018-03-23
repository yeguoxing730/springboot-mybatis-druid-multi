package com.boot.web;

import com.boot.entity.UserEntity;
import com.boot.mapper.master.MasterUserMapper;
import com.boot.mapper.slave.SlaveUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class UserController {

    @Resource
    private MasterUserMapper masterUserMapper;

    @Resource
	private SlaveUserMapper slaveUserMapper;
	
	@RequestMapping("/getUsers")
	public List<UserEntity> getUsers() {
		List<UserEntity> users=masterUserMapper.getAll();
		return users;
	}
	
    @RequestMapping("/getUser")
    public UserEntity getUser(Long id) {
    	UserEntity user=slaveUserMapper.getOne(id);
        return user;
    }
    
    @RequestMapping("/add")
    public void save(UserEntity user) {
        slaveUserMapper.insert(user);
    }
    
    @RequestMapping(value="update")
    public void update(UserEntity user) {
        slaveUserMapper.update(user);
    }
    
    @RequestMapping(value="/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
        masterUserMapper.delete(id);
    }
    
}