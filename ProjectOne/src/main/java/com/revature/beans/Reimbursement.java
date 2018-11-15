package com.revature.beans;

public class Reimbursement {
	
	private int id;
	private int eId;
	private int mId;
	private String reimburseReq;
	private String reimburseFor;
	private float reimburseAmt;
	private String submitTime;
	private String resolveTime;
	private String name;
	private String mName;
	
	public Reimbursement(int id, int eId, int mId, String reimburseReq, String reimburseFor, float reimburseAmt,
			String submitTime, String resolveTime, String name, String mName) {
		super();
		this.id = id;
		this.eId = eId;
		this.mId = mId;
		this.reimburseReq = reimburseReq;
		this.reimburseFor = reimburseFor;
		this.reimburseAmt = reimburseAmt;
		this.submitTime = submitTime;
		this.resolveTime = resolveTime;
		this.name = name;
		this.mName = mName;
	}
	
	public Reimbursement(int id, int eId, int mId, String reimburseReq, String reimburseFor, float reimburseAmt,
			String submitTime, String resolveTime, String name) {
		super();
		this.id = id;
		this.eId = eId;
		this.mId = mId;
		this.reimburseReq = reimburseReq;
		this.reimburseFor = reimburseFor;
		this.reimburseAmt = reimburseAmt;
		this.submitTime = submitTime;
		this.resolveTime = resolveTime;
		this.name = name;
	}
	
	public Reimbursement(int id, int eId, int mId, String reimburseReq, String reimburseFor, float reimburseAmt,
			String submitTime, String resolveTime) {
		super();
		this.id = id;
		this.eId = eId;
		this.mId = mId;
		this.reimburseReq = reimburseReq;
		this.reimburseFor = reimburseFor;
		this.reimburseAmt = reimburseAmt;
		this.submitTime = submitTime;
		this.resolveTime = resolveTime;
	}
	
	public Reimbursement(String name, String reimburseFor, float reimburseAmt, String submitTime, String reimburseReq, String mName, String resolveTime) {
		super();
		this.name = name;
		this.reimburseFor = reimburseFor;
		this.submitTime = submitTime;
		this.reimburseAmt = reimburseAmt;
		this.reimburseReq = reimburseReq;
		this.mName = mName;
		this.resolveTime = resolveTime;
	}
	
	public Reimbursement(int id, String name, String reimburseFor, float reimburseAmt, String submitTime) {
		super();
		this.id = id;
		this.name = name;
		this.reimburseFor = reimburseFor;
		this.reimburseAmt = reimburseAmt;
		this.submitTime = submitTime;
	}
	
	public Reimbursement(String name, String reimburseReq, String reimburseFor, float reimburseAmt, String submitTime) {
		super();
		this.name = name;
		this.reimburseReq = reimburseReq;
		this.reimburseFor = reimburseFor;
		this.reimburseAmt = reimburseAmt;
		this.submitTime = submitTime;
	}
	
	public Reimbursement(String name, String reimburseFor, float reimburseAmt, String submitTime) {
		super();
		this.name = name;
		this.reimburseFor = reimburseFor;
		this.reimburseAmt = reimburseAmt;
		this.submitTime = submitTime;
	}
	
	public Reimbursement(int id) {
		super();
		this.id = id;
	}

	public Reimbursement() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int geteId() {
		return eId;
	}

	public void seteId(int eId) {
		this.eId = eId;
	}

	public int getmId() {
		return mId;
	}

	public void setmId(int mId) {
		this.mId = mId;
	}

	public String getReimburseReq() {
		return reimburseReq;
	}

	public void setReimburseReq(String reimburseReq) {
		this.reimburseReq = reimburseReq;
	}

	public String getReimburseFor() {
		return reimburseFor;
	}

	public void setReimburseFor(String reimburseFor) {
		this.reimburseFor = reimburseFor;
	}

	public float getReimburseAmt() {
		return reimburseAmt;
	}

	public void setReimburseAmt(float reimburseAmt) {
		this.reimburseAmt = reimburseAmt;
	}

	public String getSubmitTime() {
		return submitTime;
	}

	public void setSubmitTime(String submitTime) {
		this.submitTime = submitTime;
	}

	public String getResolveTime() {
		return resolveTime;
	}

	public void setResolveTime(String resolveTime) {
		this.resolveTime = resolveTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + eId;
		result = prime * result + id;
		result = prime * result + mId;
		result = prime * result + ((mName == null) ? 0 : mName.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + Float.floatToIntBits(reimburseAmt);
		result = prime * result + ((reimburseFor == null) ? 0 : reimburseFor.hashCode());
		result = prime * result + ((reimburseReq == null) ? 0 : reimburseReq.hashCode());
		result = prime * result + ((resolveTime == null) ? 0 : resolveTime.hashCode());
		result = prime * result + ((submitTime == null) ? 0 : submitTime.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reimbursement other = (Reimbursement) obj;
		if (eId != other.eId)
			return false;
		if (id != other.id)
			return false;
		if (mId != other.mId)
			return false;
		if (mName == null) {
			if (other.mName != null)
				return false;
		} else if (!mName.equals(other.mName))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (Float.floatToIntBits(reimburseAmt) != Float.floatToIntBits(other.reimburseAmt))
			return false;
		if (reimburseFor == null) {
			if (other.reimburseFor != null)
				return false;
		} else if (!reimburseFor.equals(other.reimburseFor))
			return false;
		if (reimburseReq == null) {
			if (other.reimburseReq != null)
				return false;
		} else if (!reimburseReq.equals(other.reimburseReq))
			return false;
		if (resolveTime == null) {
			if (other.resolveTime != null)
				return false;
		} else if (!resolveTime.equals(other.resolveTime))
			return false;
		if (submitTime == null) {
			if (other.submitTime != null)
				return false;
		} else if (!submitTime.equals(other.submitTime))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Reimbursement [id=" + id + ", eId=" + eId + ", mId=" + mId + ", reimburseReq=" + reimburseReq
				+ ", reimburseFor=" + reimburseFor + ", reimburseAmt=" + reimburseAmt + ", submitTime=" + submitTime
				+ ", resolveTime=" + resolveTime + ", name=" + name + ", mName=" + mName + "]";
	}
	
	
	
	
}