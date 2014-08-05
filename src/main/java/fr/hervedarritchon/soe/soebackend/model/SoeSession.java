package fr.hervedarritchon.soe.soebackend.model;

import java.util.UUID;

import org.joda.time.DateTime;

public class SoeSession {

	private String id;

	private DateTime create;

	private User user;

	/**
	 * @return the id
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(final String id) {
		this.id = id;
	}

	/**
	 * @return the create
	 */
	public DateTime getCreate() {
		return this.create;
	}

	/**
	 * @param create
	 *            the create to set
	 */
	public void setCreate(final DateTime create) {
		this.create = create;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return this.user;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(final User user) {
		this.user = user;
	}

	/**
	 *
	 */
	public SoeSession() {
		this(null);
	}

	public SoeSession(final User user) {
		this.id = UUID.randomUUID().toString();
		this.create = new DateTime();
		this.user = user;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result)
				+ ((this.create == null) ? 0 : this.create.hashCode());
		result = (prime * result)
				+ ((this.id == null) ? 0 : this.id.hashCode());
		result = (prime * result)
				+ ((this.user == null) ? 0 : this.user.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		final SoeSession other = (SoeSession) obj;
		if (this.create == null) {
			if (other.create != null) {
				return false;
			}
		} else if (!this.create.equals(other.create)) {
			return false;
		}
		if (this.id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!this.id.equals(other.id)) {
			return false;
		}
		if (this.user == null) {
			if (other.user != null) {
				return false;
			}
		} else if (!this.user.equals(other.user)) {
			return false;
		}
		return true;
	}

}
