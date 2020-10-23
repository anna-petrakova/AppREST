package records;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import netService.NetClient;

public class RecordManager {
	private Map<String, Record> recordsMap;
	private NetClient netClient = new NetClient();
			
	public Collection<Record> getRecords() {
		if (recordsMap != null) {
			return recordsMap.values();
		}
		List<Record> records = netClient.loadData();
		recordsMap = new HashMap<>();
		for (Record r : records) {
			recordsMap.put(r.get_id(), r);
		}
		return recordsMap.values();
	}
	
	public Record getRecordWithDetails(String id) {
		Record record = findRecordById(id);
		if (record.getDetails() != null) {
			return record;
		}
		RecordDetails details = netClient.getDetailsForRecord(record);
		record.setDetails(details);
		
		return record;
	}
	
	public byte[] getAvatarDataForRecord(String id) {
		Record record = findRecordById(id);
		if (record == null) return new byte[0];
		if (record.getAvatar() != null) {
			return record.getAvatar();
		}
		byte[] image = netClient.getAvatarForRecord(record);
		record.setAvatar(image);
		return image;
	}
	
	public Record findRecordById(String id) {
		return recordsMap.get(id);
	}

}
