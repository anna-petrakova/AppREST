package records;

public class Record {
	private String _id;
	private String first_name;
	private String last_name;
	private String avatar_url;
	private byte[] avatar;
	private RecordDetails details;
	
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getAvatar_url() {
		return avatar_url;
	}
	public void setAvatar_url(String avatar) {
		this.avatar_url = avatar;
	}
	
	public byte[] getAvatar() {
		return avatar;
	}
	public void setAvatar(byte[] icon) {
		this.avatar = icon;
	}
	public RecordDetails getDetails() {
		return details;
	}
	public void setDetails(RecordDetails details) {
		this.details = details;
	}
	
	

}
