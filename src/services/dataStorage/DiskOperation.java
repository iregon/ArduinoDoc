package services.dataStorage;

public class DiskOperation implements DataManager {
	
	private LoadData read;
	private SaveData write;
	
	public DiskOperation() {
		this.read = new ReadFromDisk();
		this.write = new WriteOnDisk();
	}

	@Override
	public void saveData(String data, String path) {
		 write.write(path, data);
	}

	@Override
	public String readData(String path) {
		String text = read.read(path);
		return text;
	}
}
