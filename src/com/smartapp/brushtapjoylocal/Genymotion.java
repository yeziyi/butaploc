package com.smartapp.brushtapjoylocal;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.Random;

public class Genymotion {

	private static Random sRandom = new Random();

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

	public static String getRandom(int count) {
		String ret = "";
		for (int i = 0; i < count; i++) {
			ret = ret + (Math.abs(sRandom.nextInt()) % 10);
		}
		return ret;
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

	public static void main(String[] args) {
		{
			String filePath = "C:\\Users\\Administrator\\AppData\\Local\\Genymobile\\Genymotion\\deployed\\Google Galaxy Nexus - 4.3 - API 18 - 720x1280\\Google Galaxy Nexus - 4.3 - API 18 - 720x1280.vbox";
			String content = read(filePath);
			String tag = "name=\"genymotion_device_id\" value=\"";
			int index1 = content.indexOf(tag, 0) + tag.length();
			int index2 = content.indexOf("\"", index1);
			String imei = content.substring(index1, index2);
			content = content.replaceAll(imei, getRandom(15));
			saveByteToFile(content.getBytes(), filePath);
			System.out.println(imei);

			try {
				Thread.sleep(1000);
				Runtime runtime = Runtime.getRuntime();
				String[] commandArgs = { "D:\\Genymotion\\player", "--vm-name",
						"Google Galaxy Nexus - 4.3 - API 18 - 720x1280" };
				runtime.exec(commandArgs);
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				Thread.sleep((long) (4 * 60 * 1000));
				Process process = Runtime.getRuntime().exec("tasklist");
				String text = InputStreamTOString(process.getInputStream());
				process.destroy();
				BufferedReader reader = new BufferedReader(new StringReader(
						text));
				String line = "";
				while ((line = reader.readLine()) != null) {
					try {
						if (line.contains("player.exe")) {
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
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

		try {
			Thread.sleep(5000);
		} catch (Exception e) {
			e.printStackTrace();
		}

		main(args);
	}

}
