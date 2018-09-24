USE `yunfenghui_erp_user`;
/* 初始化权限数据 (id, name, category, url, parent_id, is_menu, configurable, remark) */
/* 用户类型{1}模块{2}菜单{2}功能{2}依赖{2} */
TRUNCATE TABLE permission;
INSERT INTO permission VALUES ('000000', 'root', 'platform', '', '', 1, 1);
  INSERT INTO permission VALUES ('101000000', '收银', 'shop', '', '000000', 1, 1);
    INSERT INTO permission VALUES ('101030000', '收银台', 'shop', 'order/preadd', '101000000', 1, 1);
		INSERT INTO permission VALUES ('101030100', '保存订单', 'shop', 'order/add', '101030000', 0, 0);
		INSERT INTO permission VALUES ('101030200', '查询商品', 'shop', 'goods/includeActivityItem/query', '101030000', 0, 0);
		INSERT INTO permission VALUES ('101030300', '查询会员', 'shop', 'order/member/query', '101030000', 0, 0);
		INSERT INTO permission VALUES ('101030400', '支付页面', 'shop', 'order/payWay', '101030000', 0, 0);
		INSERT INTO permission VALUES ('101030500', '支付订单', 'shop', 'order/pay', '101030000', 0, 0);
		INSERT INTO permission VALUES ('101030600', '关闭支付', 'shop', 'order/closeLocal', '101030000', 0, 0);
		INSERT INTO permission VALUES ('101030700', '完成支付', 'shop', 'order/completePay', '101030000', 0, 0);
		INSERT INTO permission VALUES ('101030800', '取消订单', 'shop', 'order/cancel', '101030000', 0, 0);
		INSERT INTO permission VALUES ('101030900', '查询订单状态', 'shop', 'order/queryStatus', '101030000', 0, 0);
		INSERT INTO permission VALUES ('101031000', '查询订单', 'shop', 'order/get', '101030000', 0, 0);
	INSERT INTO permission VALUES ('101060000', '订单', 'shop', 'order/manage', '101000000', 1, 0);
		INSERT INTO permission VALUES ('101060100', '查询', 'shop', 'order/query', '101060000', 0, 1);
		INSERT INTO permission VALUES ('101060200', '详情', 'shop', 'order/detail', '101060000', 0, 1);
		
  INSERT INTO permission VALUES ('105000000', '商品', 'shop', '', '000000', 1, 1);
    INSERT INTO permission VALUES ('105030000', '商品管理', 'shop', 'goods/manage', '105000000', 1, 1);
      INSERT INTO permission VALUES ('105030100', '查询商品', 'shop', 'goods/query', '105030000', 0, 1);
      INSERT INTO permission VALUES ('105030300', '新增商品', 'shop', 'goods/add', '105030000', 0, 1);
        INSERT INTO permission VALUES ('105030301', '新增页面', 'shop', 'goods/preadd', '105030300', 0, 0);
        INSERT INTO permission VALUES ('105030302', '生成条码', 'shop', 'goods/barcode', '105030300', 0, 0);
        INSERT INTO permission VALUES ('105030303', '生成拼音码', 'shop', 'goods/pinyin', '105030300', 0, 0);
        INSERT INTO permission VALUES ('105030304', '查询类别', 'shop', 'goods/categories', '105030300', 0, 0);
        INSERT INTO permission VALUES ('105030305', '查询子类别', 'shop', 'goods/categoriesByParentId', '105030300', 0, 0);
        INSERT INTO permission VALUES ('105030306', '新增并上架', 'shop', 'goods/addAndPutAway', '105030300', 0, 0);
		INSERT INTO permission VALUES ('105030309', '商品品牌', 'shop', 'goods/brand/preadd', '105030300', 0, 0);
		INSERT INTO permission VALUES ('105030312', '商品品牌查询', 'shop', 'goods/brand/query', '105030300', 0, 0);
		INSERT INTO permission VALUES ('105030315', '新增商品品牌', 'shop', 'goods/brand/add', '105030300', 0, 0);
	  INSERT INTO permission VALUES ('105030600', '上架', 'shop', 'goods/putAway', '105030000', 0, 1);
	  INSERT INTO permission VALUES ('105030900', '下架', 'shop', 'goods/soldOut', '105030000', 0, 1);
	  INSERT INTO permission VALUES ('105031200', '修改销售价格', 'shop', 'goods/price/modify', '105030000', 0, 1);
		INSERT INTO permission VALUES ('105031201', '修改销售价格页面', 'shop', 'goods/price/premodify', '105031200', 0, 0);
	  INSERT INTO permission VALUES ('105031500', '修改积分比例', 'shop', 'goods/score/modify', '105030000', 0, 1);
		INSERT INTO permission VALUES ('105031501', '修改积分比例页面', 'shop', 'goods/score/premodify', '105031500', 0, 0);
	  INSERT INTO permission VALUES ('105031800', '修改风米比例', 'shop', 'goods/fengmi/modify', '105030000', 0, 1);
		INSERT INTO permission VALUES ('105031801', '修改风米比例页面', 'shop', 'goods/fengmi/premodify', '105031800', 0, 0);
	  INSERT INTO permission VALUES ('105032100', '编辑商品', 'shop', 'goods/modify', '105030000', 0, 1);
		INSERT INTO permission VALUES ('105032101', '编辑页面', 'shop', 'goods/premodify', '105032100', 0, 0);
	  
    INSERT INTO permission VALUES ('105060000', '促销', 'shop', 'activity/manage', '105000000', 1, 1);
		INSERT INTO permission VALUES ('105060100', '查询活动', 'shop', 'activity/query', '105060000', 0, 1);
		INSERT INTO permission VALUES ('105060200', '查看活动详情', 'shop', 'activity/detail', '105060000', 0, 1);
		INSERT INTO permission VALUES ('105060300', '新增活动', 'shop', 'activity/add', '105060000', 0, 1);
			INSERT INTO permission VALUES ('105060301', '新增页面', 'shop', 'activity/preadd', '105060300', 0, 0);
			INSERT INTO permission VALUES ('105060302', '添加商品页面', 'shop', 'activity/goods/preadd', '105060300', 0, 0);
			INSERT INTO permission VALUES ('105060303', '搜索商品', 'shop', 'goods/search/query', '105060300', 0, 0);
		INSERT INTO permission VALUES ('105060600', '修改活动', 'shop', 'activity/modify', '105060000', 0, 1);
			INSERT INTO permission VALUES ('105060601', '修改页面', 'shop', 'activity/premodify', '105060600', 0, 0);
		INSERT INTO permission VALUES ('105060900', '取消活动', 'shop', 'activity/cancel', '105060000', 0, 1);

  INSERT INTO permission VALUES ('110000000', '库存', 'shop', '', '000000', 1, 1);
    INSERT INTO permission VALUES ('110030000', '入库管理', 'shop', 'stock/manage', '110000000', 1, 1);
	  INSERT INTO permission VALUES ('110030100', '入库查询', 'shop', 'stock/query', '110030000', 0, 1);
	  INSERT INTO permission VALUES ('110030200', '入库详情', 'shop', 'stock/stockItems/detail', '110030000', 0, 1);
      INSERT INTO permission VALUES ('110030300', '新增入库', 'shop', 'stock/add', '110030000', 0, 1);
        INSERT INTO permission VALUES ('110030301', '新增入库页面', 'shop', 'stock/preadd', '110030300', 0, 0);
		INSERT INTO permission VALUES ('110030302', '搜索商品', 'shop', 'goods/search/query', '110030300', 0, 0);
		INSERT INTO permission VALUES ('110030303', '新增商品', 'shop', 'goods/add', '110030300', 0, 0);
		INSERT INTO permission VALUES ('110030304', '新增商品页面', 'shop', 'goods/preadd', '110030300', 0, 0);
		INSERT INTO permission VALUES ('110030305', '生成条码', 'shop', 'goods/barcode', '110030300', 0, 0);
		INSERT INTO permission VALUES ('110030306', '生成拼音码', 'shop', 'goods/pinyin', '110030300', 0, 0);
		INSERT INTO permission VALUES ('110030307', '查询类别', 'shop', 'goods/categories', '110030300', 0, 0);
		INSERT INTO permission VALUES ('110030308', '查询子类别', 'shop', 'goods/categoriesByParentId', '110030300', 0, 0);
		INSERT INTO permission VALUES ('110030309', '新增并上架', 'shop', 'goods/addAndPutAway', '110030300', 0, 0);
		INSERT INTO permission VALUES ('110030310', '生成入库单号', 'shop', 'stock/recordno', '110030300', 0, 0);
		INSERT INTO permission VALUES ('110030312', '商品品牌', 'shop', 'goods/brand/preadd', '110030300', 0, 0);
		INSERT INTO permission VALUES ('110030315', '商品品牌查询', 'shop', 'goods/brand/query', '110030300', 0, 0);
		INSERT INTO permission VALUES ('110030318', '新增商品品牌', 'shop', 'goods/brand/add', '110030300', 0, 0);
		INSERT INTO permission VALUES ('110030320', '搜索商品页面', 'shop', 'stock/search/goods', '110030300', 0, 0);
		
	INSERT INTO permission VALUES ('115000000', '销售', 'shop', '', '000000', 1, 1);
		INSERT INTO permission VALUES ('115050000', '收银订单', 'shop', 'sale/order/manage', '115000000', 1, 1);
			INSERT INTO permission VALUES ('115050500', '查询', 'shop', 'sale/order/query', '115050000', 0, 1);
			INSERT INTO permission VALUES ('115051000', '详情', 'shop', 'sale/order/detail', '115050000', 0, 1);
			INSERT INTO permission VALUES ('115051500', '退款', 'shop', 'sale/order/refund/submit', '115050000', 0, 1);
				INSERT INTO permission VALUES ('115051501', '退款页面', 'shop', 'sale/order/refund/preadd', '115051500', 0, 0);
		INSERT INTO permission VALUES ('115100000', '退款单', 'shop', 'sale/order/refund/manage', '115000000', 1, 1);
			INSERT INTO permission VALUES ('11510300', '查询退款单', 'shop', 'sale/order/refund/query', '115100000', 0, 1);
			INSERT INTO permission VALUES ('11510400', '查看退款单详情', 'shop', 'sale/order/refund/detail', '115100000', 0, 1);
			INSERT INTO permission VALUES ('11510500', '查看订单详情', 'shop', 'sale/order/detail', '115100000', 0, 1);
			INSERT INTO permission VALUES ('11510600', '取消退款', 'shop', 'sale/order/refund/cancel', '115100000', 0, 1);
			INSERT INTO permission VALUES ('11510700', '重试退款', 'shop', 'sale/order/refund/retry', '115100000', 0, 1);
		
  INSERT INTO permission VALUES ('120000000', '供应商', 'shop', '', '000000', 1, 1);
    INSERT INTO permission VALUES ('120030000', '供应商管理', 'shop', 'supplier/manage', '120000000', 1, 1);
	  INSERT INTO permission VALUES ('120030100', '供应商查询', 'shop', 'supplier/query', '120030000', 0, 1);
      INSERT INTO permission VALUES ('120030300', '新增供应商', 'shop', 'supplier/add', '120030000', 0, 1);
        INSERT INTO permission VALUES ('120030301', '新增供应商页面', 'shop', 'supplier/preadd', '120030300', 0, 0);
	  INSERT INTO permission VALUES ('120030600', '编辑供应商', 'shop', 'supplier/modify', '120030000', 0, 1);
        INSERT INTO permission VALUES ('120030601', '编辑供应商页面', 'shop', 'supplier/premodify', '120030600', 0, 0);
		
  INSERT INTO permission VALUES ('122000000', '余额账户', 'shop', '', '000000', 1, 1);
    INSERT INTO permission VALUES ('122030000', '收支明细', 'shop', 'balance/account/manage', '122000000', 1, 1);
	  INSERT INTO permission VALUES ('122030100', '明细查询', 'shop', 'balance/account/query', '122030000', 0, 1);

  INSERT INTO permission VALUES ('125000000', '员工', 'shop', '', '000000', 1, 1);
    INSERT INTO permission VALUES ('125030000', '员工管理', 'shop', 'user/manage', '125000000', 1, 1);
		INSERT INTO permission VALUES ('125030300', '查询员工', 'shop', 'user/query', '125030000', 0, 1);
		INSERT INTO permission VALUES ('125030600', '新增员工', 'shop', 'user/add', '125030000', 0, 1);
			INSERT INTO permission VALUES ('125030601', '新增页面', 'shop', 'user/preadd', '125030600', 0, 0);
			INSERT INTO permission VALUES ('125030602', '查询角色', 'shop', 'user/roles', '125030600', 0, 0);
			INSERT INTO permission VALUES ('125030603', '查询角色的权限', 'shop', 'role/permission/byRole', '125030600', 0, 0);
		INSERT INTO permission VALUES ('125030900', '修改员工', 'shop', 'user/modify', '125030000', 0, 1);
			INSERT INTO permission VALUES ('125030901', '修改页面', 'shop', 'user/premodify', '125030900', 0, 0);
			INSERT INTO permission VALUES ('125030902', '查询角色', 'shop', 'user/roles', '125030900', 0, 0);
			INSERT INTO permission VALUES ('125030903', '查询角色的权限', 'shop', 'role/permission/byRole', '125030900', 0, 0);
    INSERT INTO permission VALUES ('125060000', '角色管理', 'shop', 'role/manage', '125000000', 1, 1);
		INSERT INTO permission VALUES ('125060300', '查询角色', 'shop', 'role/query', '125060000', 0, 1);
		INSERT INTO permission VALUES ('125060600', '新增角色', 'shop', 'role/add', '125060000', 0, 1);
			INSERT INTO permission VALUES ('125060601', '新增页面', 'shop', 'role/preadd', '125060600', 0, 0);
			INSERT INTO permission VALUES ('125060602', '权限树', 'shop', 'role/permission/all', '125060600', 0, 0);
		INSERT INTO permission VALUES ('125060900', '修改角色', 'shop', 'role/modify', '125060000', 0, 1);
			INSERT INTO permission VALUES ('125060901', '修改页面', 'shop', 'role/premodify', '125060900', 0, 0);
			INSERT INTO permission VALUES ('125060902', '权限树', 'shop', 'role/permission/get', '125060900', 0, 0);
			
  INSERT INTO permission VALUES ('190000000', '设置', 'shop', '', '000000', 1, 1);
	INSERT INTO permission VALUES ('190050000', '修改密码', 'shop', 'setting/password/manage', '190000000', 1, 1);
		INSERT INTO permission VALUES ('190050100', '修改', 'shop', 'setting/password/modify', '190050000', 0, 0);
	
  
  INSERT INTO permission VALUES ('905000000', '商品', 'platform', '', '000000', 1, 1);
	INSERT INTO permission VALUES ('905030000', '商品查询', 'platform', 'goods/manage', '905000000', 1, 1);
		INSERT INTO permission VALUES ('905030100', '查询', 'platform', 'goods/query', '905030000', 0, 0);
	INSERT INTO permission VALUES ('905060000', '促销查询', 'platform', 'goods/activity/manage', '905000000', 1, 1);
		INSERT INTO permission VALUES ('905060100', '查询', 'platform', 'goods/activity/query', '905060000', 0, 0);
 
  INSERT INTO permission VALUES ('910000000', '库存', 'platform', '', '000000', 1, 1);
	INSERT INTO permission VALUES ('910030000', '入库单', 'platform', 'stock/manage', '910000000', 1, 1);
		INSERT INTO permission VALUES ('910030100', '查询', 'platform', 'stock/query', '910030000', 0, 0);
		INSERT INTO permission VALUES ('910030200', '详情', 'platform', 'stock/detail', '910030000', 0, 0);
		
  INSERT INTO permission VALUES ('915000000', '销售', 'platform', '', '000000', 1, 1);
	INSERT INTO permission VALUES ('915030000', '收银订单', 'platform', 'sale/order/manage', '915000000', 1, 1);
		INSERT INTO permission VALUES ('915030100', '查询订单', 'platform', 'sale/order/query', '915030000', 0, 0);
		INSERT INTO permission VALUES ('915030200', '订单详情', 'platform', 'sale/order/detail', '915030000', 0, 0);
		INSERT INTO permission VALUES ('915030300', '订单积分记录', 'platform', 'sale/order/score/records', '915030000', 0, 0);
	INSERT INTO permission VALUES ('915100000', '退款单', 'platform', 'sale/order/refund/manage', '915000000', 1, 1);
		INSERT INTO permission VALUES ('915100100', '查询', 'platform', 'sale/order/refund/query', '915100000', 0, 0);
		INSERT INTO permission VALUES ('915100200', '订单详情', 'platform', 'sale/order/detail', '915100000', 0, 0);
		INSERT INTO permission VALUES ('915100300', '订单积分记录', 'platform', 'sale/order/score/records', '915100000', 0, 0);
		INSERT INTO permission VALUES ('915100400', '退款单详情', 'platform', 'sale/order/refund/detail', '915100000', 0, 0);
  
  INSERT INTO permission VALUES ('920000000', '供应商', 'platform', '', '000000', 1, 1);
	INSERT INTO permission VALUES ('920030000', '供应商查询', 'platform', 'supplier/manage', '920000000', 1, 1);
		INSERT INTO permission VALUES ('920030100', '查询', 'platform', 'supplier/query', '920030000', 0, 0);
	
  INSERT INTO permission VALUES ('925000000', '门店', 'platform', '', '000000', 1, 1);
    INSERT INTO permission VALUES ('925030000', '门店管理', 'platform', 'shop/manage', '925000000', 1, 1);
	  INSERT INTO permission VALUES ('925030100', '查询门店', 'platform', 'shop/query', '925030000', 0, 1);
		INSERT INTO permission VALUES ('925030101', '根据省查市', 'platform', 'shop/area/cities', '925030100', 0, 0);
		INSERT INTO permission VALUES ('925030102', '根据市查区', 'platform', 'shop/area/districts', '925030100', 0, 0);
      INSERT INTO permission VALUES ('925030300', '新增门店', 'platform', 'shop/add', '925030000', 0, 1);
		INSERT INTO permission VALUES ('925030301', '新增页面', 'platform', 'shop/preadd', '925030300', 0, 0);
		INSERT INTO permission VALUES ('925030302', '根据省查市', 'platform', 'shop/area/cities', '925030300', 0, 0);
		INSERT INTO permission VALUES ('925030303', '根据市查区', 'platform', 'shop/area/districts', '925030300', 0, 0);	
	INSERT INTO permission VALUES ('925060000', '余额收支', 'platform', 'shop/balance/manage', '925000000', 1, 1);
	  INSERT INTO permission VALUES ('925060100', '账户明细', 'platform', 'shop/balance/query', '925060000', 0, 1);
		
  INSERT INTO permission VALUES ('930000000', '员工', 'platform', '', '000000', 1, 1);
    INSERT INTO permission VALUES ('930030000', '员工管理', 'platform', 'user/manage', '930000000', 1, 1);
		INSERT INTO permission VALUES ('930030300', '查询员工', 'platform', 'user/query', '930030000', 0, 1);
		INSERT INTO permission VALUES ('930030600', '新增员工', 'platform', 'user/add', '930030000', 0, 1);
			INSERT INTO permission VALUES ('930030601', '新增页面', 'platform', 'user/preadd', '930030600', 0, 0);
			INSERT INTO permission VALUES ('930030602', '查询角色', 'platform', 'user/roles', '930030600', 0, 0);
			INSERT INTO permission VALUES ('930030603', '查询角色的权限', 'platform', 'role/permission/byRole', '930030600', 0, 0);
		INSERT INTO permission VALUES ('930030900', '修改员工', 'platform', 'user/modify', '930030000', 0, 1);
			INSERT INTO permission VALUES ('930030901', '修改页面', 'platform', 'user/premodify', '930030900', 0, 0);
			INSERT INTO permission VALUES ('930030902', '查询角色', 'platform', 'user/roles', '930030900', 0, 0);
			INSERT INTO permission VALUES ('930030903', '查询角色的权限', 'platform', 'role/permission/byRole', '930030900', 0, 0);
    INSERT INTO permission VALUES ('930060000', '角色管理', 'platform', 'role/manage', '930000000', 1, 1);
		INSERT INTO permission VALUES ('930060300', '查询角色', 'platform', 'role/query', '930060000', 0, 1);
		INSERT INTO permission VALUES ('930060600', '新增角色', 'platform', 'role/add', '930060000', 0, 1);
			INSERT INTO permission VALUES ('930060601', '新增页面', 'platform', 'role/preadd', '930060600', 0, 0);
			INSERT INTO permission VALUES ('930060602', '权限树', 'platform', 'role/permission/all', '930060600', 0, 0);
		INSERT INTO permission VALUES ('930060900', '修改角色', 'platform', 'role/modify', '930060000', 0, 1);
			INSERT INTO permission VALUES ('930060901', '修改页面', 'platform', 'role/premodify', '930060900', 0, 0);
			INSERT INTO permission VALUES ('930060902', '权限树', 'platform', 'role/permission/get', '930060900', 0, 0);
	INSERT INTO permission VALUES ('990000000', '设置', 'platform', '', '000000', 1, 1);
		INSERT INTO permission VALUES ('990050000', '系统配置', 'platform', 'setting/system/config/manage', '990000000', 1, 1);
			INSERT INTO permission VALUES ('990050100', '修改', 'platform', 'setting/system/config/modify', '990050000', 0, 0);
		INSERT INTO permission VALUES ('990070000', '修改密码', 'platform', 'setting/password/manage', '990000000', 1, 1);
		INSERT INTO permission VALUES ('990070100', '修改', 'platform', 'setting/password/modify', '990070000', 0, 0);

/* 初始化菜单依赖关系 */
TRUNCATE TABLE permission_dependencies;
/********************************** 门店 ***********************************************/
-- 门店：收银台 
INSERT INTO permission_dependencies VALUES('101030000', '101030100');
INSERT INTO permission_dependencies VALUES('101030000', '101030200');
INSERT INTO permission_dependencies VALUES('101030000', '101030300');
INSERT INTO permission_dependencies VALUES('101030000', '101030400');
INSERT INTO permission_dependencies VALUES('101030000', '101030500');
INSERT INTO permission_dependencies VALUES('101030000', '101030600');
INSERT INTO permission_dependencies VALUES('101030000', '101030700');
INSERT INTO permission_dependencies VALUES('101030000', '101030800');
INSERT INTO permission_dependencies VALUES('101030000', '101030900');
INSERT INTO permission_dependencies VALUES('101030000', '101031000');
INSERT INTO permission_dependencies VALUES('101030000', '101060000');
INSERT INTO permission_dependencies VALUES('101030000', '101060100');
INSERT INTO permission_dependencies VALUES('101030000', '101060200');

-- 门店：商品管理：新增商品
INSERT INTO permission_dependencies VALUES('105030300', '105030100');
INSERT INTO permission_dependencies VALUES('105030300', '105030301');
INSERT INTO permission_dependencies VALUES('105030300', '105030302');
INSERT INTO permission_dependencies VALUES('105030300', '105030303');
INSERT INTO permission_dependencies VALUES('105030300', '105030304');
INSERT INTO permission_dependencies VALUES('105030300', '105030305');
INSERT INTO permission_dependencies VALUES('105030300', '105030306');
INSERT INTO permission_dependencies VALUES('105030300', '105030309');
INSERT INTO permission_dependencies VALUES('105030300', '105030312');
INSERT INTO permission_dependencies VALUES('105030300', '105030315');
-- 门店：商品管理：修改
INSERT INTO permission_dependencies VALUES('105031200', '105031201');
INSERT INTO permission_dependencies VALUES('105031500', '105031501');
INSERT INTO permission_dependencies VALUES('105031800', '105031801');
INSERT INTO permission_dependencies VALUES('105032100', '105030100');
INSERT INTO permission_dependencies VALUES('105032100', '105032101');
INSERT INTO permission_dependencies VALUES('105032100', '105030304');
INSERT INTO permission_dependencies VALUES('105032100', '105030305');
INSERT INTO permission_dependencies VALUES('105032100', '105030309');
INSERT INTO permission_dependencies VALUES('105032100', '105030312');
INSERT INTO permission_dependencies VALUES('105032100', '105030315');

-- 门店：商品管理：上架 
INSERT INTO permission_dependencies VALUES('105030600', '105030100');
-- 门店：商品管理：下架
INSERT INTO permission_dependencies VALUES('105030900', '105030100');
-- 门店：销售：收银订单：详情   
INSERT INTO permission_dependencies VALUES('115051000', '115050500');
-- 门店：销售：收银订单：退款 
INSERT INTO permission_dependencies VALUES('115051500', '115050500');
INSERT INTO permission_dependencies VALUES('115051500', '115051501');
-- 门店：退款单
INSERT INTO permission_dependencies VALUES('11510400', '11510300');
INSERT INTO permission_dependencies VALUES('11510500', '11510300');
INSERT INTO permission_dependencies VALUES('11510600', '11510300');
INSERT INTO permission_dependencies VALUES('11510700', '11510300');
-- 门店：员工：新增
INSERT INTO permission_dependencies VALUES('125030600', '125030300');
INSERT INTO permission_dependencies VALUES('125030600', '125030601');
INSERT INTO permission_dependencies VALUES('125030600', '125030602');
INSERT INTO permission_dependencies VALUES('125030600', '125030603');
-- 门店：员工：修改
INSERT INTO permission_dependencies VALUES('125030900', '125030300');
INSERT INTO permission_dependencies VALUES('125030900', '125030901');
INSERT INTO permission_dependencies VALUES('125030900', '125030902');
INSERT INTO permission_dependencies VALUES('125030900', '125030903');
-- 门店：角色：新增 
INSERT INTO permission_dependencies VALUES('125060600', '125060300');
INSERT INTO permission_dependencies VALUES('125060600', '125060601');
INSERT INTO permission_dependencies VALUES('125060600', '125060602');
-- 门店：角色：修改 
INSERT INTO permission_dependencies VALUES('125060900', '125060300');
INSERT INTO permission_dependencies VALUES('125060900', '125060901');
INSERT INTO permission_dependencies VALUES('125060900', '125060902');
-- 门店：活动：新增 
INSERT INTO permission_dependencies VALUES('105060300', '105060100');
INSERT INTO permission_dependencies VALUES('105060300', '105060301');
INSERT INTO permission_dependencies VALUES('105060300', '105060302');
INSERT INTO permission_dependencies VALUES('105060300', '105060303');
-- 门店：活动：修改
INSERT INTO permission_dependencies VALUES('105060600', '105060601');
INSERT INTO permission_dependencies VALUES('105060600', '105060302');
INSERT INTO permission_dependencies VALUES('105060600', '105060303');
-- 门店：活动：详情
INSERT INTO permission_dependencies VALUES('105060200', '105060100');

-- 门店：库存：详情
INSERT INTO permission_dependencies VALUES('110030200', '110030100');
-- 门店：库存：新增 
INSERT INTO permission_dependencies VALUES('110030300', '110030100');
INSERT INTO permission_dependencies VALUES('110030300', '110030301');
INSERT INTO permission_dependencies VALUES('110030300', '110030302');
INSERT INTO permission_dependencies VALUES('110030300', '110030303');
INSERT INTO permission_dependencies VALUES('110030300', '110030304');
INSERT INTO permission_dependencies VALUES('110030300', '110030305');
INSERT INTO permission_dependencies VALUES('110030300', '110030306');
INSERT INTO permission_dependencies VALUES('110030300', '110030307');
INSERT INTO permission_dependencies VALUES('110030300', '110030308');
INSERT INTO permission_dependencies VALUES('110030300', '110030309');
INSERT INTO permission_dependencies VALUES('110030300', '110030310');
INSERT INTO permission_dependencies VALUES('110030300', '110030312');
INSERT INTO permission_dependencies VALUES('110030300', '110030315');
INSERT INTO permission_dependencies VALUES('110030300', '110030318');
INSERT INTO permission_dependencies VALUES('110030300', '110030320');

-- 门店：供应商：新增 
INSERT INTO permission_dependencies VALUES('120030300', '120030100');
INSERT INTO permission_dependencies VALUES('120030300', '120030301');
-- 门店：供应商：修改 
INSERT INTO permission_dependencies VALUES('120030600', '120030100');
INSERT INTO permission_dependencies VALUES('120030600', '120030601');
-- 门店：设置：修改密码
INSERT INTO permission_dependencies VALUES('190050000', '190050100');

/********************************** 平台 ***********************************************/
-- 平台：商品：查询
INSERT INTO permission_dependencies VALUES('905030000', '905030100');
-- 平台：促销：查询 
INSERT INTO permission_dependencies VALUES('905060000', '905060100');
-- 平台：入库单：查询
INSERT INTO permission_dependencies VALUES('910030000', '910030100');
INSERT INTO permission_dependencies VALUES('910030000', '910030200');
-- 平台：收银订单：查询
INSERT INTO permission_dependencies VALUES('915030000', '915030100');
INSERT INTO permission_dependencies VALUES('915030000', '915030200');
INSERT INTO permission_dependencies VALUES('915030000', '915030300');
-- 平台：退款单：查询 
INSERT INTO permission_dependencies VALUES('915100000', '915100100');
INSERT INTO permission_dependencies VALUES('915100000', '915100200');
INSERT INTO permission_dependencies VALUES('915100000', '915100300');
INSERT INTO permission_dependencies VALUES('915100000', '915100400');
-- 平台：供应商：查询
INSERT INTO permission_dependencies VALUES('920030000', '920030100');
-- 平台：门店：查询 
INSERT INTO permission_dependencies VALUES('925030100', '925030101');
INSERT INTO permission_dependencies VALUES('925030100', '925030102');
-- 平台：门店：新增 
INSERT INTO permission_dependencies VALUES('925030300', '925030100');
INSERT INTO permission_dependencies VALUES('925030300', '925030301');
INSERT INTO permission_dependencies VALUES('925030300', '925030302');
INSERT INTO permission_dependencies VALUES('925030300', '925030303');
-- 平台：员工：新增 
INSERT INTO permission_dependencies VALUES('930030600', '930030300');
INSERT INTO permission_dependencies VALUES('930030600', '930030601');
INSERT INTO permission_dependencies VALUES('930030600', '930030602');
INSERT INTO permission_dependencies VALUES('930030600', '930030603');
-- 平台：员工：修改
INSERT INTO permission_dependencies VALUES('930030900', '930030300');
INSERT INTO permission_dependencies VALUES('930030900', '930030901');
INSERT INTO permission_dependencies VALUES('930030900', '930030902');
INSERT INTO permission_dependencies VALUES('930030900', '930030903');
-- 平台：角色：新增
INSERT INTO permission_dependencies VALUES('930060600', '930060300');
INSERT INTO permission_dependencies VALUES('930060600', '930060601');
INSERT INTO permission_dependencies VALUES('930060600', '930060602');
-- 平台：角色：修改
INSERT INTO permission_dependencies VALUES('930060900', '930060300');
INSERT INTO permission_dependencies VALUES('930060900', '930060901');
INSERT INTO permission_dependencies VALUES('930060900', '930060902');
-- 平台：设置：系统配置 
INSERT INTO permission_dependencies VALUES('990050000', '990050100');
-- 平台：设置：修改密码
INSERT INTO permission_dependencies VALUES('990070000', '990070100');