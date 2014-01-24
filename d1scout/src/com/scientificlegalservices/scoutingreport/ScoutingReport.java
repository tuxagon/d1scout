package com.scientificlegalservices.scoutingreport;

public final class ScoutingReport {
	private int predicted_max_bench_press;
	private int predicted_vertical_jump;
	private int predicted_forty_yard_dash;
	
	ScoutingReport(float height, float weight, float bench, float vertical, float forty) {
		this.predicted_max_bench_press = calculateMaxBench(height,weight);
		this.predicted_vertical_jump = calculateVerticalJump(height,weight);
		this.predicted_forty_yard_dash = calculateFortyYardDash(height,weight);
	}

	private int calculateFortyYardDash(float height, float weight) {
		return (int) ((height * -0.002) + (weight * 0.007) + 4.754);
	}

	private int calculateVerticalJump(float height, float weight) {
		return (int) ((height * 0.336) + (weight * -0.080) + 27.144);
	}

	private int calculateMaxBench(float height, float weight) {
		return (int) ((height * -7.764) + (weight * 1.432) + 642.447);
	}
	
	public int getPredictedBenchPress() {
		return this.predicted_max_bench_press;
	}
	
	public int getPredictedVerticalJump() {
		return this.predicted_vertical_jump;
	}
	
	public int getPredicted40YardDash() {
		return this.predicted_forty_yard_dash;
	}
}
