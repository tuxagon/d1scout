package com.scientificlegalservices.scoutingreport;

public final class ScoutingReportData {
	
	public static enum ExpectedHeight {
		WR(72.17f), 
		HB(70.2f), 
		OT(77.86f),
		OG(76.11f),
		ILB(73.16f),
		OLB(73.97f),
		C(75.67f),
		TE(76.63f),
		FB(72.67f),
		DT(74.04f),
		DE(77.24f),
		CB(70.81f),
		S(72.91f);
		
		private float height;
		
		ExpectedHeight(float h) {
			this.height = h;
		}
		
		public float getHeight() {
			return this.height;
		}
	}
	
	public static enum ExpectedWeight {
		WR(203.71f), 
		HB(216.87f), 
		OT(316.89f),
		OG(319.94f),
		ILB(244.36f),
		OLB(245.96f),
		C(316.32f),
		TE(256.52f),
		FB(243.99f),
		DT(310.49f),
		DE(275.51f),
		CB(193.99f),
		S(215.84f);
		
		private float weight;
		
		ExpectedWeight(float w) {
			this.weight = w;
		}
		
		public float getWeight() {
			return this.weight;
		}
	}
	
	public static enum Percentage {
		NFL_EXTREMELY_RARE(1.0f),
		D1_FBS_NCAA_RARE(0.95f),
		D1_FCS_NCAA_OUTSTANDING(0.9f),
		D2_NCAA_SOLID(0.85f),
		D3_NCAA_GOOD(0.8f),
		NAIA_ADEQUATE(0.75f),
		NJCAA_BORDERLINE(0.7f),
		NAIA_NJCAA_WALK_ON(0.65f),
		NOT_LEGITIMATE(0.6f);
		
		private float percent;
		
		Percentage(float p) {
			this.percent = p;
		}
		
		public float getPercent() {
			return this.percent;
		}
		
		public int convertToInt() {
			return (int) (100f * this.percent);
		}
	}
}
