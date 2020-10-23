package netService;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.json.JSONArray;
import org.json.JSONObject;

import records.Company;
import records.Record;
import records.RecordDetails;

public class NetClient {
	private List<Record> records = new ArrayList<>();
	
	public List<Record> loadData() {
		getRecords();
		return records;
	}	
	
	private void getRecords() {
		int page = 1;
		boolean toNextPage = true;
		while (toNextPage) {
			try {
				URL url = new URL("http://46.36.40.152:3000/api/lemon/users?page=" + page);
	            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	            conn.setRequestMethod("GET");
	            conn.setRequestProperty("Accept", "application/json");
	            if (conn.getResponseCode() != 200) {
	                throw new RuntimeException("Failed : HTTP Error code : "
	                        + conn.getResponseCode());
	            }
	            InputStreamReader in = new InputStreamReader(conn.getInputStream());
	            BufferedReader br = new BufferedReader(in);
	            String output;
	            while ((output = br.readLine()) != null) {
	                JSONArray arr = new JSONObject(output).getJSONArray("data");
	                if (arr.length() == 0) {
	                	toNextPage = false;
	                } else {
		                parseRecords(arr);
	                }
	            }
	            conn.disconnect();
			} catch (Exception e) {
	            System.out.println("Exception in NetClientGet:- " + e);
	        }
			page++;
		}
	}
	
	private void parseRecords(JSONArray arr) {
		for (int i = 0; i < arr.length(); i++) {
			Record record = new Record();
		    String _id = arr.getJSONObject(i).getString("_id");
		    String firstName = arr.getJSONObject(i).getString("first_name");
		    String lastName = arr.getJSONObject(i).getString("last_name");
		    String avatarStr = arr.getJSONObject(i).getString("avatar");
		    
		    record.set_id(_id);
		    record.setFirst_name(firstName);
		    record.setLast_name(lastName);
		    record.setAvatar_url(avatarStr);
		    
		    records.add(record);
		}
	}
	
	public RecordDetails getDetailsForRecord(Record record) {
		RecordDetails details = null;
		try {
			URL url = new URL("http://46.36.40.152:3000/api/lemon/users/" + record.get_id());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP Error code : "
                        + conn.getResponseCode());
            }
            InputStreamReader in = new InputStreamReader(conn.getInputStream());
            BufferedReader br = new BufferedReader(in);
            JSONObject detailsStr = new JSONObject(br.readLine());
        	details = parseDetailsForRecord(detailsStr);
            conn.disconnect();
		} catch (Exception e) {
            System.out.println("Exception in NetClientGet:- " + e);
        }
		return details;
	}
		
	private RecordDetails parseDetailsForRecord(JSONObject obj) {
		RecordDetails details = new RecordDetails();
		
		int id = obj.getInt("id");
		String email = obj.getString("email");
		String ipAddress = obj.getString("ip_address");
		
		JSONArray compArr = obj.getJSONArray("company");
		String compName = compArr.getJSONObject(0).getString("name");
		String compCountry = compArr.getJSONObject(0).getString("country");
		JSONArray compLocation = compArr.getJSONObject(0).getJSONArray("location");
		
		double compLocationLat = compLocation.getJSONObject(0).getDouble("lat");
		double compLocationLong = compLocation.getJSONObject(0).getDouble("long");
		
		Company comp = new Company();
		comp.setName(compName);
		comp.setCountry(compCountry);
		comp.setLocation_lat(compLocationLat);
		comp.setLocation_long(compLocationLong);
		
		details.setId(id);
		details.setEmail(email);
		details.setIp_address(ipAddress);
		details.setCompany(comp);
					
		return details;
	}
	
	public byte[] getAvatarForRecord(Record r) {
		String urlStr = r.getAvatar_url();
		byte[] imageInByte = null;
		try {
			URL url = new URL(urlStr);
			BufferedImage originalImage = ImageIO.read(url);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(originalImage, "png", baos );
			imageInByte = baos.toByteArray();
			
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return imageInByte;
	}

}