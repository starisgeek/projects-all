package com.yunfenghui.erp.user.dao;

import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.yunfenghui.erp.common.Commons;
import com.yunfenghui.erp.user.model.Permission;
import com.yunfenghui.test.BaseDaoTest;

public class PermissionDaoTest extends BaseDaoTest {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private PermissionDao permissionDao;

	@Test
	public void testQueryPermissionsByCategoryAndIsMenu() {
		List<Permission> permissions = permissionDao
				.queryPermissionsByCategoryAndIsMenu(Commons.CATEGORY_PLATFORM, Commons.YES);
		logPermissions(permissions);
	}

	@Test
	public void testQueryPermissionsByUserIdAndIsMenu() {
		List<Permission> permissions = permissionDao.queryPermissionsByUserIdAndIsMenu(1,
				Commons.YES);
		logPermissions(permissions);
	}

	@Test
	public void testQueryPermissionsByUserId() {
		int userId = 1;
		List<Permission> permissions = permissionDao.queryPermissionsByUserId(userId);
		logPermissions(permissions);
	}

	private void logPermissions(List<Permission> permissions) {
		if (permissions != null && !permissions.isEmpty()) {
			for (Permission p : permissions) {
				logger.info("{}", p.getId());
			}
		} else {
			logger.info("Permissions not found");
		}
	}
}
