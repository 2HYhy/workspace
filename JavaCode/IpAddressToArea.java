public class IpAddressToAreaUtil {
	public static void main(String[] args) {
		String ip = "123.139.94.139";
		JSONObject result = getAreaByIpAddress(ip);
		System.out.println(result);
	}

	private static JSONObject getAreaByIpAddress(String ipAddress) {
		String url = "http://ip.taobao.com/service/getIpInfo.php?ip=";
		HttpURLConnection connection;
		URL urlStr;
		try {
			urlStr = new URL(url + ipAddress);
			System.out.println("requestUrl = " + urlStr);
			connection = (HttpURLConnection) urlStr.openConnection();
			// 往对端写完数据对端服务器返回数据
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
			// 以BufferedReader流来读取
			StringBuilder buffer = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
			reader.close();
			System.out.println("请求阿里API返回结果为 " + buffer.toString());
			JSONObject jsonObject = JSONObject.parseObject(buffer.toString());
			int code = (int) jsonObject.get("code");
			if (code == 0) {
				System.out.println("request return success!");
				JSONObject json = jsonObject.getJSONObject("data");
				JSONObject obj = new JSONObject();
				obj.put("country", json.get("country"));
				obj.put("province", json.get("region"));
				obj.put("city", json.get("city"));
				return obj;
			} else {
				System.out.println("request return fail!");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}