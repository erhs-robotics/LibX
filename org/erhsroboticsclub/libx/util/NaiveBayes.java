import java.util.HashMap;
import java.util.Map;


public class NaiveBayes {
	
	public static class Gaus {
		private double mu, sigma2;
		
		public Gaus(double mu, double sigma2) {
			this.mu = mu;
			this.sigma2 = sigma2;			
		}
		
		public double eval(double x) {
			double k = 1.0 / Math.sqrt(2 * Math.PI * sigma2);
			return k * Math.exp(-Math.pow(x - mu, 2) / (2 * sigma2));
		}
	}
	
	public static class Label {
		int id;
		Map<Integer, Gaus> features;
		double prior;
		
		public Label(int id, Map<Integer, Gaus> features, double prior) {
			this.id = id;
			this.features = features;
			this.prior = prior;
		}
	}
	
	Map<Integer, Label> labels = new HashMap<>();
	
	public void addLabel(Label label) {
		labels.put(label.id, label);
	}
	
	private double posterior(Map<Integer, Double> z, int id) {
		Label label = labels.get(id);
		double p = label.prior;		
		for(int i : label.features.keySet()) {
			if(z.containsKey(i)) {
				Gaus gaus = label.features.get(i);
				p *= gaus.eval(z.get(i));
			}
		}
		return p;
	}
	
	public int classify(Map<Integer, Double> z) {
		int id = -1;
		double maxp = 0;
		for(int i : labels.keySet()) {
			double p = posterior(z, i);
			if(p > maxp) {
				maxp = p;
				id = i;
			}
		}
		
		return id;
	}	
}