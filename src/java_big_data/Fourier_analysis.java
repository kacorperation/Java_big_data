package java_big_data;

import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.transform.DftNormalization;
import org.apache.commons.math3.transform.FastFourierTransformer;
import org.apache.commons.math3.transform.TransformType;

public class Fourier_analysis {
	/**
	 * Finds the fft frequency list
	 * @param sampling_frequency - {int} how often per time unit is a sample taken
	 * @param num_entries - {int} number of entries for which fourier analysis is being made
	 * @return {float[]} The array of fft frequencies
	 **/
	public static float[] find_fft_frequencies(int sampling_frequency, int num_entries){
		
		//fft_freq = i*sampling_frequency/num_entries, where i is the ith element
		float [] fft_freq = new float[num_entries];
		for (int i=0; i < num_entries ; i++) {
			fft_freq[i] = (float)(sampling_frequency * i / num_entries + 1);
		 }
		return fft_freq;
	}
	/**
	 * Applies fft on given data, then returns the fft mag and freq
	 * @param sampling_frequency - {int} how often per time unit is a sample taken
	 * @param data - {double} an array with size [num_entries] whose entries are the 
	 * 									  number of occurences per sample
	 * @return {float[num_entries_2][2]}: A matrix of fft_mag and fft_freq, where fft_freq for ith element
	 * 									  is at return[i][0] and fft_mag for ith element is at 
	 * 								      return[i][1]
	 */
	public static float [][] fft(int sampling_frequency, int[] data) {
		
		
		
		
		//stretch num_entries to the least greater power of two, as fft only works on sets whose 
		//number of entries is a power of 2
		int num_entries_2 = nextPowerOf2(data.length);
		double[] temp_array = new double[num_entries_2];
		for (int i=0; i < num_entries_2; i++) {
			if (i<data.length) {
				temp_array[i] = data[i];
			}else {
				temp_array[i] = 0;
			}
		}
		
		FastFourierTransformer fft = new FastFourierTransformer(DftNormalization.STANDARD);
		Complex[] input = fft.transform(temp_array, TransformType.FORWARD);
		
		float [][] result = new float [num_entries_2][2];
		float [] fft_freq = find_fft_frequencies(sampling_frequency, num_entries_2);
		for (int i=0; i < num_entries_2 ; i++) {
			result[i][0] = fft_freq[i];
			//fft_mag[i] = 2*ABS(fft[i])/num_entries_2. Done by .getReal() and .getImaginary()
			result[i][1] = (float) (Math.sqrt(input[i].getReal() * input[i].getReal() + 
					input[i].getImaginary() * input[i].getImaginary()) * 2/num_entries_2);
		}
		return result;
	}
	/**
	 * Finds the local maxima within peak_interval range for the given input elements
	 * @param peak_interval	- {int} Within how many data points should the maximum element be 
	 * 								  considered to be a peak. ie setting this to 5 will make sure the 
	 * 								  peak is the largest element in +-5 points away from it 
	 * @param data - {float[]} data to find the peak in 
	 * @return {float[input.length]}: An array of Trues and falses where output[i] = True implies 
	 * 								  the ith element of the data is a local maxima  
	 */
	public static boolean[] peak_finder(int peak_interval, float[] data) {
		
		double mean = 0.0;
		for (int i=0; i < data.length; i++) {
			mean += data[i];
			if (i==data.length - 1) {
				mean = mean/i;
			}
		}
		
		double stdev = (float) 0.0;
		for (int i=0; i < data.length; i++) {
			stdev += (data[i]-mean)*(data[i]-mean);
		}
		stdev = Math.sqrt(stdev / (data.length - 1));
		
		int num_data = data.length;
		boolean[] result = new boolean[num_data];
		float max = (float) 0.0;
		int max_index = -1;
		for (int i=0; i < num_data; i++) {
			max = 0;
			max_index = -1;
			// there are less than peak_interval elements to go back to
			if (i < peak_interval) {
				for (int j = 0; j < i + peak_interval; j++) {
					if (data[j] > max) {
						max = data[j];
						max_index = j;
					}
				}
			//there are less than peak_interval elements to go forward to
			}else if(num_data - 1 < peak_interval + i) {
				for (int j = num_data - 1; j >= num_data - i; j--) {
					if (data[j] > max) {
						max = data[j];
						max_index = j;
					}
				}
			}else {
				for (int j = i - peak_interval; j <= i + peak_interval; j++) {
					if (data[j] > max) {
						max = data[j];
						max_index = j;
					}
				}
			}
			//if ith element is not a max, put result[i] = false. Otherwise, result[i] = true
			if (max_index == i && max > mean + 2 * stdev) {
				result[i] = true;
			}else {
				result[i] = false;
			}
		}
		return result;
	}
	/**
	 * 		
	 * @param num {int} the number in question
	 * @return the closest greater or equal power of two
	 */
	static int nextPowerOf2(int num)
	{
	    return num == 1 ? 1 : Integer.highestOneBit(num - 1) * 2;
	}
}
