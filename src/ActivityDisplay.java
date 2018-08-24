import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import org.jfree.ui.RefineryUtilities;

//Yannis Papadopoulos project
public class ActivityDisplay {

	protected static HashMap<String, UserInfo> usersData;

	public static void readData(String fileName) {
		usersData = new HashMap<String, UserInfo>();
		Scanner scan = null;

		try {
			scan = new Scanner(new File(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		while (scan.hasNextLine()) {

			String line = scan.nextLine();
			Scanner scan2 = new Scanner(line);

			String idScan = scan2.next();

			if (idScan.equals("userId")) {
				scan2.close();
				line = scan.nextLine();
				scan2 = new Scanner(line);
				idScan = scan2.next();
			}

			String lName = scan2.next();
			String fName = scan2.next();

			if (usersData.containsKey(idScan)) {
				usersData.get(idScan).addData(line);
			} else {
				UserInfo tempUser = new UserInfo(lName, fName);
				tempUser.addData(line);
				usersData.put(idScan, tempUser);
			}

			scan2.close();
		}
		scan.close();
	}

	public static void displayUsers() {
		System.out.println("Displaying Users:\n");
		for (Map.Entry<String, UserInfo> entry : usersData.entrySet()) {
			System.out.println(entry.getKey() + ", " + entry.getValue().toString());
		}
	}

	public static void main(String[] args) {

		String path = Paths.get("").toAbsolutePath().toString();
		//String fileName = path + "\\userData.txt";
		String fileName = path + "\\userData2.txt";

		readData(fileName);
		//displayUsers();
		
		GUI frm = new GUI("Button Demo");
		RefineryUtilities.centerFrameOnScreen(frm);
	}

}
