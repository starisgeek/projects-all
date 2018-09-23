package com.yunfenghui.jf.api.filter.support;

public class Subject {
	private String appId;
	private int partnerId;
	private String publicKey;

	private static ThreadLocal<Subject> holder = new ThreadLocal<>();

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public int getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(int partnerId) {
		this.partnerId = partnerId;
	}

	public String getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}

	public static void bind(Subject subject) {
		holder.set(subject);
	}

	public static Subject get() {
		return holder.get();
	}

	public static void unbind() {
		holder.remove();
	}

	public static Builder newBuilder() {
		return new Builder();
	}

	public static class Builder {
		private final Subject subject;

		private Builder() {
			subject = new Subject();
		}

		public Builder appId(String appId) {
			subject.setAppId(appId);
			return this;
		}

		public Builder partnerId(int partnerId) {
			subject.setPartnerId(partnerId);
			return this;
		}

		public Builder publicKey(String publicKey) {
			subject.setPublicKey(publicKey);
			return this;
		}

		public Subject build() {
			return subject;
		}
	}
}
