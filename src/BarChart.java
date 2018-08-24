import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class BarChart{
	
	public ChartPanel chartPanel;
	
	private class DayActivity{
		public String day;
		public String activity;
		public Double value;
		public int count;
		
		public DayActivity(String day ,String act, Double val) {
			this.day = day;
			this.activity = act;
			this.value = val;
			this.count = 1;
		}
		
		public void update(Double val) {
			value = value + val;
			count++;
		}
		
		public String toString() {
			return activity + ", " + value + ", " + count;
		}
	}

	public BarChart(String chartTitle, JPanel panel, String userId) {
		JFreeChart chart = null;
		if(GUI.speedButton.isSelected()) {
			chart = ChartFactory.createBarChart(chartTitle, "Day", "Average Speed(m/s)", createDataset(userId), PlotOrientation.VERTICAL, true, true, false);
		} 
		else if(GUI.distanceButton.isSelected()){
			chart = ChartFactory.createBarChart(chartTitle, "Day", "Average Distance(m)", createDataset(userId), PlotOrientation.VERTICAL, true, true, false);
		} 
		else {
			chart = ChartFactory.createBarChart(chartTitle, "Day", "Total Distance(m)", createDataset(userId), PlotOrientation.VERTICAL, true, true, false);
		}

		chartPanel = new ChartPanel(chart);
		chartPanel.setBounds(0, 0, 1750, 915);
		
		panel.add(chartPanel);
		panel.setVisible(true);
	}

	private CategoryDataset createDataset(String userId) {
		final DefaultCategoryDataset result = new DefaultCategoryDataset();
		List<DayActivity> dateActive = new ArrayList<DayActivity>();

		UserInfo user = ActivityDisplay.usersData.get(userId);
		
		if(GUI.speedButton.isSelected()) {
			for(int i = 0; i < user.date.size(); i++) {
				boolean found = false;
				int position = 0;
				if(user.action.get(i).equals("null")) {
					continue;
				}
				for(int j = 0; j < dateActive.size(); j++) {
					if(dateActive.get(j).day.equals(user.date.get(i)) && dateActive.get(j).activity.equals(user.action.get(i))) {
						found = true;
						position = j;
					}
				}
				if(!found) {
					DayActivity temp = new DayActivity(user.date.get(i), user.action.get(i), user.speed.get(i));
					dateActive.add(temp);
				} else {
					dateActive.get(position).update(user.speed.get(i));;
				}
			}
			
			for (DayActivity entry : dateActive) {
				result.addValue(entry.value/entry.count, entry.activity, entry.day);
			}
		}
		else if(GUI.distanceButton.isSelected()){
			for(int i = 0; i < user.date.size(); i++) {
				boolean found = false;
				int position = 0;
				if(user.action.get(i).equals("null")) {
					continue;
				}
				for(int j = 0; j < dateActive.size(); j++) {
					if(dateActive.get(j).day.equals(user.date.get(i)) && dateActive.get(j).activity.equals(user.action.get(i))) {
						found = true;
						position = j;
					}
				}
				if(!found) {
					DayActivity temp = new DayActivity(user.date.get(i), user.action.get(i), user.distance.get(i));
					dateActive.add(temp);
				} else {
					dateActive.get(position).update(user.distance.get(i));;
				}
			}
			
			for (DayActivity entry : dateActive) {
				result.addValue(entry.value/entry.count, entry.activity, entry.day);
			}
		} 
		else {
			for(int i = 0; i < user.date.size(); i++) {
				boolean found = false;
				int position = 0;
				if(user.action.get(i).equals("null")) {
					continue;
				}
				for(int j = 0; j < dateActive.size(); j++) {
					if(dateActive.get(j).day.equals(user.date.get(i)) && dateActive.get(j).activity.equals(user.action.get(i))) {
						found = true;
						position = j;
					}
				}
				if(!found) {
					DayActivity temp = new DayActivity(user.date.get(i), user.action.get(i), user.distance.get(i));
					dateActive.add(temp);
				} else {
					dateActive.get(position).update(user.distance.get(i));;
				}
			}
			
			for (DayActivity entry : dateActive) {
				result.addValue(entry.value, entry.activity, entry.day);
			}
		} 

		return result;
	}

}