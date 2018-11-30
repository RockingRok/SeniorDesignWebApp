package webapp;

import java.util.LinkedList;
import java.util.List;

import org.json.JSONObject;

public class VideoPredictions {
	private List<String> videoPredictions;
	
	private VideoPredictions() {}
	
	public VideoPredictions(JSONObject videoLabels, JSONObject videoProbs) throws Exception {
		this.videoPredictions = new LinkedList<>();
		for(int j = 0; j < 5; j++) {
			this.videoPredictions.add(videoLabels.getString(Integer.toString(j)) + ": " + videoProbs.getDouble(Integer.toString(j)));
		}
	}
	
	public List<String> getPredictions() {
		return videoPredictions;
	}
}
