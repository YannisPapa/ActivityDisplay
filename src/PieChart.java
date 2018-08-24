import java.util.HashMap;
import java.util.Map;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;

public class PieChart{

	public ChartPanel chartPanel;
	
	public PieChart(String chartTitle, JPanel panel, String userId) {
		PieDataset dataset = createDataset(userId);
		JFreeChart chart = createChart(dataset, chartTitle);
		
		chartPanel = new ChartPanel(chart);
		chartPanel.setBounds(0, 0, 1750, 915);
		
		panel.add(chartPanel);
		panel.setVisible(true);
	}

	private PieDataset createDataset(String userId) {
		DefaultPieDataset result = new DefaultPieDataset();
		
		UserInfo user = ActivityDisplay.usersData.get(userId);
		Map<String, Integer> totalAct = new HashMap<String, Integer>();
		
		for(String act: user.action) {
			if(act.equals("null")) {
				continue;
			}
			if(totalAct.containsKey(act)) {
				totalAct.put(act, totalAct.get(act)+1);
			} else {
				totalAct.put(act, 1);
			}
		}
		
		for (Map.Entry<String, Integer> entry : totalAct.entrySet()) {
			result.setValue(entry.getKey(), entry.getValue());
		}
		
		return result;
	}

	private JFreeChart createChart(PieDataset dataset, String title) {

		JFreeChart chart = ChartFactory.createPieChart(title, dataset, true, true, false);

		PiePlot plot = (PiePlot) chart.getPlot();
		plot.setStartAngle(290);
		plot.setDirection(Rotation.CLOCKWISE);
		plot.setForegroundAlpha(0.5f);
		return chart;

	}
}