package com.yunfenghui.erp.user.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import com.yunfenghui.erp.common.Commons;

public class Permission implements Serializable {
	private static final long serialVersionUID = 1L;
	// 主键
	private String id;
	// 名称
	private String name;
	// url
	private String url;
	private String category;
	// 父菜单id
	private String parentId;
	// 子节点
	private List<Permission> children;
	// 是否为菜单(0-否、1-是)
	private int isMenu;
	// 是否可配置(0-否、1-是)
	private int isConfigurable;
	// 是否选中
	private boolean checked;

	public Permission() {
	}

	public Permission(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public List<Permission> getChildren() {
		return children;
	}

	public void setChildren(List<Permission> children) {
		this.children = children;
	}

	public void addChild(Permission child) {
		if (children == null) {
			children = new LinkedList<>();
		}
		children.add(child);
	}

	public int getIsMenu() {
		return isMenu;
	}

	public void setIsMenu(int isMenu) {
		this.isMenu = isMenu;
	}

	public int getIsConfigurable() {
		return isConfigurable;
	}

	public void setIsConfigurable(int isConfigurable) {
		this.isConfigurable = isConfigurable;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		buildTreeAsString(this, 0, result);
		return result.toString();
	}

	public String getPermissionAsString() {
		return url != null ? Commons.URI_SEPARATOR + category
				+ (!url.startsWith(Commons.URI_SEPARATOR) ? (Commons.URI_SEPARATOR + url) : url)
				+ Commons.URI_SUFFIX : null;
	}

	private static void buildTreeAsString(Permission node, int depth, StringBuilder builder) {
		if (node != null) {
			for (int i = 0; i < depth; i++) {
				builder.append("   ");
			}
			builder.append("[id]=" + node.id + ", [name]=" + node.name + ", [url]=" + node.url
					+ ", [category]=" + node.category);
			builder.append("\r\n");
			if (node.children != null) {
				for (Permission child : node.children) {
					buildTreeAsString(child, depth + 1, builder);
				}
			}
		}
	}
}
