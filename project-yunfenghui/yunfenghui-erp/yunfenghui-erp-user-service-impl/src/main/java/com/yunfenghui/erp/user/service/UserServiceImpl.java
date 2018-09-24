package com.yunfenghui.erp.user.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Sets;
import com.yunfenghui.common.service.PasswordService;
import com.yunfenghui.common.service.PasswordService.SaltGenerator;
import com.yunfenghui.erp.common.Commons;
import com.yunfenghui.erp.common.ERPException;
import com.yunfenghui.erp.user.dao.PermissionDao;
import com.yunfenghui.erp.user.dao.UserDao;
import com.yunfenghui.erp.user.model.Permission;
import com.yunfenghui.erp.user.model.Role;
import com.yunfenghui.erp.user.model.User;
import com.yunfenghui.erp.user.util.UserMessageCode;

@Service(UserService.ID)
public class UserServiceImpl implements UserService {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private UserDao userDao;
	@Autowired
	private PermissionDao permissionDao;
	@Resource(name = "passwordService")
	private PasswordService passwordService;
	@Resource(name = "saltGenerator")
	private SaltGenerator saltGenerator;

	private static final String DEFAULT_INIT_PASSWORD = "123456";

	private static final Comparator<Permission> PERMISSION_COMPARATOR = new PermissionComparator();

	@Override
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, rollbackFor = ERPException.class)
	public void createUser(User user) throws ERPException {
		User found = userDao.queryUserByLoginPhoneAndShopId(user.getLoginPhone(), user.getShopId());
		if (found != null) {
			logger.error("Failed to createUser, because loginPhone:{}, shopId:{} exists",
					user.getLoginPhone(), user.getShopId());
			throw new ERPException(UserMessageCode.USER_LOGIN_PHONE_EXISTS);
		}

		int count = userDao.queryCountByShopIdAndIsSuper(user.getShopId(), Commons.YES);
		if (count > 0) {
			logger.error("Failed to createUser, because super:{} exists in shop:{}, found:{}",
					user.getLoginPhone(), user.getShopId(), count);
			throw new ERPException(UserMessageCode.USER_SHOP_SUPER_EXISTS);
		}

		user.setPasswordSalt(saltGenerator.generate());
		user.setPassword(passwordService.encrypt(DEFAULT_INIT_PASSWORD, user.getPasswordSalt()));

		userDao.insertUser(user);
	}

	@Override
	public User getUserByLoginPhoneAndShopId(String loginPhone, int shopId) {
		return userDao.queryUserByLoginPhoneAndShopId(loginPhone, shopId);
	}

	@Override
	public User getUserById(int id) {
		return userDao.queryUserById(id);
	}

	@Override
	public void createRole(Role role) throws ERPException {
		// TODO Auto-generated method stub

	}

	@Override
	public Role getRoleById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Role> getAllRolesByShopId(int shopId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Permission getPermissionsAsMenuTree(int userId) {
		User user = userDao.queryUserById(userId);
		if (user != null) {
			List<Permission> permissions;
			if (user.getIsSuper() == Commons.YES) {
				permissions = permissionDao.queryPermissionsByCategoryAndIsMenu(user.getCategory(),
						Commons.YES);
			} else {
				permissions = permissionDao.queryPermissionsByUserIdAndIsMenu(user.getId(),
						Commons.YES);
			}
			return buildPermissionsAsTree(permissions);
		}
		return null;
	}

	@Override
	public Permission getPermissionsAsMenuByRoleId(int roleId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Set<String> getAllPermissionStrings(int userId) {
		// 这里需要cache
		List<Permission> permissions = permissionDao.queryPermissionsByUserId(userId);
		Set<String> permissionStrings;
		if (permissions != null && !permissions.isEmpty()) {
			permissionStrings = Sets.newHashSetWithExpectedSize(permissions.size());
			for (Permission p : permissions) {
				String permissionString = p.getPermissionAsString();
				if (!permissionStrings.contains(permissionString)) {
					permissionStrings.add(permissionString);
				}
			}
		} else {
			permissionStrings = Collections.EMPTY_SET;
		}

		return permissionStrings;
	}

	private static Permission buildPermissionsAsTree(List<Permission> permissions) {
		if (permissions != null && !permissions.isEmpty()) {
			addRootPermissionIfRequired(permissions);
			Collections.sort(permissions, PERMISSION_COMPARATOR);
			Permission current, next;
			for (int i = 0, len = permissions.size(); i < len - 1; i++) {
				current = permissions.get(i);
				for (int j = i + 1; j < len; j++) {
					next = permissions.get(j);
					if (next.getParentId().equals(current.getId())) {
						current.addChild(next);
					}
				}
			}
			Permission permissionTree = permissions.get(0);
			recheck(permissionTree);
			return permissionTree;
		}
		return null;
	}

	private static void recheck(Permission p) {
		if (p != null) {
			List<Permission> children = p.getChildren();
			if (children != null && !children.isEmpty()) {
				p.setChecked(false);
				for (Permission child : children) {
					recheck(child);
				}
			}
		}
	}

	private static void addRootPermissionIfRequired(List<Permission> permissions) {
		Permission permission = permissions.iterator().next();
		if (!StringUtils.isBlank(permission.getParentId())) {
			String permissionId = permission.getParentId();
			Permission root = new Permission(permissionId);
			permissions.add(0, root);
		}
	}

	private static class PermissionComparator implements Comparator<Permission> {
		@Override
		public int compare(Permission o1, Permission o2) {
			if (o1.getId().compareTo(o2.getId()) < 0) {
				return -1;
			} else if (o1.getId().compareTo(o2.getId()) > 0) {
				return 1;
			} else {
				return 0;
			}
		}
	}
}
