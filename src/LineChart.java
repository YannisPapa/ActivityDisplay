import org.jfree.chart.ChartPanel;import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class LineChart {
	
	public ChartPanel chartPanel;
	
	public LineChart(String chartTitle, JPanel panel, String userId) {
		JFreeChart chart = null;
		if(GUI.speedButton.isSelected()) {
			chart = ChartFactory.createLineChart(chartTitle, "Time of Day", "Speed(m/s)", createDataset(userId), PlotOrientation.VERTICAL, true, true, false);
		} 
		else if(GUI.distanceButton.isSelected()){
			chart = ChartFactory.createLineChart(chartTitle, "Time of Day", "Distance(m)", createDataset(userId), PlotOrientation.VERTICAL, true, true, false);
		} 
		else {
			chart = ChartFactory.createLineChart(chartTitle, "Time of Day", "Total Distance(m)", createDataset(userId), PlotOrientation.VERTICAL, true, true, false);
		}
		
		chartPanel = new ChartPanel(chart);
		chartPanel.setBounds(0, 0, 1750, 915);

		panel.add(chartPanel);
		panel.setVisible(true);	
	}

	private DefaultCategoryDataset createDataset(String userId) {
		DefaultCategoryDataset result = new DefaultCategoryDataset();
		
		UserInfo user = ActivityDisplay.usersData.get(userId);
		
		if(GUI.speedButton.isSelected()) {
			for(int i = 0; i < user.speed.size(); i++) {
				if(user.date.get(i).equals(GUI.dayLine.getText())) {
					result.addValue(user.speed.get(i), "Speed/Time", user.time.get(i));
				}
			}
		}
		else if(GUI.distanceButton.isSelected()){
			for(int i = 0; i < user.distance.size(); i++) {
				if(user.date.get(i).equals(GUI.dayLine.getText())) {
					result.addValue(user.distance.get(i), "Distance/Time", user.time.get(i));
				}
			}
		} 
		else {
			Double sum = 0.00;
			for(int i = 0; i < user.distance.size(); i++) {
				if(user.date.get(i).equals(GUI.dayLine.getText())) {
					sum = sum + user.distance.get(i);
					result.addValue(sum, "TotalDistance/Time", user.time.get(i));
				}
			}
		}
		
		return result;
	}
}