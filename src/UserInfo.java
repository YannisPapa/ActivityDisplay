import java.util.ArrayList;
import java.util.Scanner;

public class UserInfo {

	public String lastName;
	public String firstName;
	public ArrayList<String> action;
	public ArrayList<Double> speed;
	public ArrayList<Double> distance;
	public ArrayList<Double> longitude;
	public ArrayList<Double> latitude;
	public ArrayList<String> time;
	public ArrayList<String> date;

	public UserInfo(String lastName, String firstName) {
		this.lastName = lastName;
		this.firstName = firstName;
		action = new ArrayList<String>();
		speed = new ArrayList<Double>();
		distance = new ArrayList<Double>();
		longitude = new ArrayList<Double>();
		latitude = new ArrayList<Double>();
		time = new ArrayList<String>();
		date = new ArrayList<String>();
	}

	public void addData(String data) {

		Scanner scan = new Scanner(data);

		int count = 0;
		while (scan.hasNext()) {

			String dataScan = scan.next();
			Double temp;

			switch (count) {
				case 3:
					action.add(dataScan);
					break;
				case 4:
					temp = Double.parseDouble(dataScan);
					speed.add(temp);
					break;
				case 5:
					temp = Double.parseDouble(dataScan);
					distance.add(temp);
					break;
				case 6:
					temp = Double.parseDouble(dataScan);
					longitude.add(temp);
					break;
				case 7:
					temp = Double.parseDouble(dataScan);
					latitude.add(temp);
					break;
				case 8:
					time.add(dataScan);
					break;
				case 9:
					date.add(dataScan);
					break;
				default:
					break;
			}
			count++;
		}
		scan.close();
	}
	
	public String toString(){
		String temp = lastName + ", " + firstName + ":\n";
		temp = temp + "Actions(" + action.size() + "): " + action + "\n";
		temp = temp + "Speed(" + speed.size() + "): " + speed + "\n";
		temp = temp + "Distance(" + distance.size() + "): " + distance + "\n";
		temp = temp + "Longitude(" + longitude.size() + "): " + longitude + "\n";
		temp = temp + "Latitude(" + latitude.size() + "): " + latitude + "\n";
		temp = temp + "Time(" + time.size() + "): " + time + "\n";
		temp = temp + "Date(" + date.size() + "): " + date + "\n";
		return temp;
	}
}
