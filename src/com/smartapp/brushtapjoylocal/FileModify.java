package com.smartapp.brushtapjoylocal;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.util.Random;

/**
 * 修改文件
 */
public class FileModify {

	private static Random sRandom = new Random();

	public String readFileByBytes(String fileName) {
		InputStream in = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			// 一次读多个字节
			byte[] tempbytes = new byte[2048];
			int byteread = 0;
			in = new FileInputStream(fileName);
			// 读入多个字节到字节数组中，byteread为一次读入的字节数
			while ((byteread = in.read(tempbytes)) != -1) {
				baos.write(tempbytes, 0, byteread);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e1) {
				}
			}
		}
		return baos.toString();
	}

	public static String read(String filePath) {
		FileReader fr = null;
		int length = 0;
		StringBuffer buf = new StringBuffer();
		char[] chars = new char[2048];
		try {
			// 根据文件路径创建缓冲输入流
			fr = new FileReader(filePath);
			while ((length = fr.read(chars)) != -1) {
				buf.append(chars, 0, length);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭流
			if (fr != null) {
				try {
					fr.close();
				} catch (IOException e) {
					fr = null;
				}
			}
		}
		return buf.toString();
	}

	public static File createNewFile(String path, boolean append) {
		File newFile = new File(path);
		if (!append) {
			if (newFile.exists()) {
				newFile.delete();
			}
		}
		if (!newFile.exists()) {
			try {
				File parent = newFile.getParentFile();
				if (parent != null && !parent.exists()) {
					parent.mkdirs();
				}
				newFile.createNewFile();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return newFile;
	}

	/**
	 * 读取文件数据
	 */
	public static byte[] getByteFromFile(final String filePathName) {
		byte[] bs = null;
		try {
			File newFile = new File(filePathName);
			FileInputStream fileInputStream = new FileInputStream(newFile);
			DataInputStream dataInputStream = new DataInputStream(
					fileInputStream);
			BufferedInputStream inPutStream = new BufferedInputStream(
					dataInputStream);
			bs = new byte[(int) newFile.length()];
			inPutStream.read(bs);
			fileInputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bs;
	}

	/**
	 * 保存数据到指定文件
	 */
	public static boolean saveByteToFile(final byte[] byteData,
			final String filePathName) {
		boolean result = false;
		try {
			File newFile = createNewFile(filePathName, false);
			FileOutputStream fileOutputStream = new FileOutputStream(newFile);
			fileOutputStream.write(byteData);
			fileOutputStream.flush();
			fileOutputStream.close();
			result = true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 将内容回写到文件中
	 * 
	 * @param filePath
	 * @param content
	 */
	public static void write(String filePath, String content) {
		BufferedWriter bw = null;

		try {
			// 根据文件路径创建缓冲输出流
			bw = new BufferedWriter(new FileWriter(filePath));
			// 将内容写入文件中
			bw.write(content);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭流
			if (bw != null) {
				try {
					bw.close();
				} catch (IOException e) {
					bw = null;
				}
			}
		}
	}

	public static byte[] getRandom(int count) {
		String ret = "";
		for (int i = 0; i < count; i++) {
			ret = ret + (Math.abs(sRandom.nextInt()) % 10);
		}
		return ret.getBytes();
	}

	public static int getRandomIndex(int length) {
		return (Math.abs(sRandom.nextInt()) % length);
	}

	public static int search(byte[] array, int startindex, int endindex, byte b) {
		for (int i = startindex; i < endindex; i++) {
			if (array[i] == b) {
				return i;
			}
		}
		return 0;
	}

	private static void brush(String name) {
		{
			{
				String filePath = "C:\\Users\\Administrator\\Desktop\\eclipse\\android-sdk-windows\\tools\\emulator-arm.exe";
				byte[] bytes = getByteFromFile(filePath);
				byte[] x = "CGSN".getBytes();
				boolean find = false;
				int startIndex = 0;
				int index = 0;
				while (!find) {
					index = search(bytes, startIndex, bytes.length, x[0]);
					if (bytes[index + 1] == x[1] && bytes[index + 2] == x[2]
							&& bytes[index + 3] == x[3]
							&& bytes[index + 4] == 0) {
						find = true;
					} else {
						startIndex = index + 1;
					}
				}
				byte[] imei = getRandom(15);
				for (int i = 0; i < imei.length; i++) {
					bytes[index + 5 + i] = imei[i];
				}
				saveByteToFile(bytes, filePath);
				File file = new File(filePath);
				file.setExecutable(true);
				file.setReadable(true);
				file.setWritable(true);
			}
			{
				String filePath = "C:\\Users\\Administrator\\Desktop\\eclipse\\android-sdk-windows\\tools\\emulator-arm.exe";
				byte[] bytes = getByteFromFile(filePath);
				byte[] x = "CIMI".getBytes();
				boolean find = false;
				int startIndex = 0;
				int index = 0;
				while (!find) {
					index = search(bytes, startIndex, bytes.length, x[0]);
					if (bytes[index + 1] == x[1] && bytes[index + 2] == x[2]
							&& bytes[index + 3] == x[3]
							&& bytes[index + 4] == 0) {
						find = true;
					} else {
						startIndex = index + 1;
					}
				}
				byte[] imsi = getRandom(8);
				for (int i = 0; i < imsi.length; i++) {
					bytes[index + 12 + i] = imsi[i];
				}
				saveByteToFile(bytes, filePath);
				File file = new File(filePath);
				file.setExecutable(true);
				file.setReadable(true);
				file.setWritable(true);
			}
			{
				// release
				String[] releases = { "4.0.1", "4.0.3", "4.0.3", "4.0.3",
						"4.0.3", "4.1.2", "4.1.2", "4.2.2", "4.3.0", "4.4.2",
						"4.4.2", "4.4.2", "2.3.3", "2.3.6" };
				byte[] release = (releases[getRandomIndex(releases.length)])
						.getBytes();
				String filePath = "C:\\Users\\Administrator\\Desktop\\eclipse\\android-sdk-windows\\system-images\\android-16\\default\\armeabi-v7a\\system.img";
				byte[] bytes = getByteFromFile(filePath);
				byte[] x = "ro.build.version.release=".getBytes();
				boolean find = false;
				int startIndex = 0;
				int index = 0;
				while (!find) {
					index = search(bytes, startIndex, bytes.length, x[0]);
					boolean same = true;
					for (int i = 1; i < 25; i++) {
						if (bytes[index + i] != x[i]) {
							same = false;
						}
					}
					if (same) {
						find = true;
					} else {
						startIndex = index + 1;
					}
				}
				for (int i = 0; i < release.length; i++) {
					bytes[index + 25 + i] = release[i];
				}
				saveByteToFile(bytes, filePath);
				File file = new File(filePath);
				file.setExecutable(true);
				file.setReadable(true);
				file.setWritable(true);
			}
			{
				// TODO 要先手动修改model值
				// model
				String[] models = { "GT-N7100", "GT-N7100", "GT-N7000",
						"GT-N7000", "GT-I9100", "GT-I9300", "GT-I9300",
						"GT-I9300", "GT-I9500", "GT-I9500", "GT-I9000",
						"GT-I9030", "GT-I9030", "GT-I9235", "GT-I9060",
						"GT-I9082", "GT-I9152", "GT-I9003" };
				byte[] model = (models[getRandomIndex(models.length)])
						.getBytes();
				String filePath = "C:\\Users\\Administrator\\Desktop\\eclipse\\android-sdk-windows\\system-images\\android-16\\default\\armeabi-v7a\\system.img";
				byte[] bytes = getByteFromFile(filePath);
				byte[] x = "ro.product.model=".getBytes();
				boolean find = false;
				int startIndex = 0;
				int index = 0;
				while (!find) {
					index = search(bytes, startIndex, bytes.length, x[0]);
					boolean same = true;
					for (int i = 1; i < 17; i++) {
						if (bytes[index + i] != x[i]) {
							same = false;
						}
					}
					if (same) {
						find = true;
					} else {
						startIndex = index + 1;
					}
				}
				for (int i = 0; i < model.length; i++) {
					bytes[index + 17 + i] = model[i];
				}
				saveByteToFile(bytes, filePath);
				File file = new File(filePath);
				file.setExecutable(true);
				file.setReadable(true);
				file.setWritable(true);
			}
			{
				// TODO 修改system.img 这个名字gsm.operator.alpha
				// ro.product.manufacturer ro.product.brand
			}
			{
				try {
					Thread.sleep(1000);
					Runtime runtime = Runtime.getRuntime();
					String[] commandArgs = {
							"C:\\Users\\Administrator\\Desktop\\eclipse\\android-sdk-windows\\tools\\emulator",
							"-avd", name };
					runtime.exec(commandArgs);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static String InputStreamTOString(InputStream in) throws Exception {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] data = new byte[2048];
		int count = -1;
		while ((count = in.read(data, 0, 2048)) != -1) {
			outStream.write(data, 0, count);
		}

		data = null;
		return new String(outStream.toByteArray(), "UTF-8");
	}

	/**
	 * 主方法
	 * 
	 * @throws UnsupportedEncodingException
	 */
	public static void main(String[] args) {
		int time = 0;
		while (true) {
			try {
				brush("1");
				System.out.println((++time) + "");
				try {
					if (time % 20 == 0) {
						File file = new File("C:\\Users\\Administrator\\AppData\\Local\\Temp\\AndroidEmulator");
						if (file.exists() && file.isDirectory()) {
							File[] files = file.listFiles();
							if (files != null) {
								for (File ff : files) {
									ff.setWritable(true);
									ff.setReadable(true);
									ff.delete();
									System.err.println("清理垃圾");
								}
							}
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				Thread.sleep((long) (4 * 60 * 1000));
				Process process = Runtime.getRuntime().exec("tasklist");
				String text = InputStreamTOString(process.getInputStream());
				process.destroy();
				BufferedReader reader = new BufferedReader(new StringReader(
						text));
				String line = "";
				while ((line = reader.readLine()) != null) {
					try {
						if (line.contains("emulator-arm")) {
							line = line.trim();
							String[] array = line.split("\\s+");
							process = Runtime.getRuntime().exec(
									"taskkill /F /PID " + array[1].trim()
											+ "\n");
							process.waitFor();
							process.destroy();
						}
					} catch (Exception e) {
					}
				}
				Thread.sleep(5 * 1000);
			} catch (Exception e) {
				e.printStackTrace();
				return;
			}
		}
	}
}