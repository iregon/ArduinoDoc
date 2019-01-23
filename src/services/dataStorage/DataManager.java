package services.dataStorage;

public interface DataManager {
	
	public void saveData(String data, String path);
	
	public String readData(String path);
	
}
