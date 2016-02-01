package puddleProblem;

import java.util.ArrayList;
import java.util.Random;

public class MainPuddle {

	static ArrayList<Volume> processedVolumes;
	static int[] heights;

	public static void main(String[] args) {

		heights = new int[10];
		Random r = new Random();
		// Initializing the heights array
		for (int i = 0; i < heights.length; i++) {
			heights[i] = r.nextInt(10);
			System.out.print(heights[i] + " ");
		}
		// Identifying and processing the volumes between peaks
		ProcessVolumes();

		// Finding and displaying the maximum volume found
		System.out.println("Maximum volume is " + CalcMaxVolume());

	}

	static void ProcessVolumes() {
		// Initializing data
		processedVolumes = new ArrayList<Volume>();
		Volume volume;
		int startTempIndex = 0;
		int endTempIndex = 0;
		int volumeTemp = 0;
		/*
		 * Passing through the array of heights left to right calculating the
		 * volumes for each gap between two ascending peaks
		 */
		for (int i = 1; i < heights.length; i++) {
			if (heights[i] >= heights[startTempIndex]) {
				endTempIndex = i;
				if (volumeTemp > 0) {
					volume = new Volume(startTempIndex, endTempIndex, volumeTemp);
					processedVolumes.add(volume);
				}
				startTempIndex = i;
				volumeTemp = 0;
			} else {
				volumeTemp += heights[startTempIndex] - heights[i];
			}
		}
		startTempIndex = heights.length - 1;
		volumeTemp = 0;
		/*
		 * Passing through the array of heights right to left calculating the
		 * volumes for each gap between two ascending peaks -> covering the
		 * descending peaks neglected at the first passing
		 */
		for (int i = heights.length - 1; i >= 0; i--) {
			if (heights[i] >= heights[startTempIndex]) {
				endTempIndex = i;
				if (volumeTemp > 0) {
					volume = new Volume(startTempIndex, endTempIndex, volumeTemp);
					processedVolumes.add(volume);
				}
				startTempIndex = i;
				volumeTemp = 0;
			} else {
				volumeTemp += heights[startTempIndex] - heights[i];
			}
		}

		System.out.println();
	}

	static int CalcMaxVolume() {
		// Calculating the maximum volume from the array of Volumes
		Volume maxVolume = processedVolumes.get(0);
		for (int i = 0; i < processedVolumes.size(); i++) {
			if (maxVolume.getValue() < processedVolumes.get(i).getValue()) {
				maxVolume = processedVolumes.get(i);
			}
		}
		return maxVolume.getValue();
	}
}
