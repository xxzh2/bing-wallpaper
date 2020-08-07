import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Test;

import lombok.extern.log4j.Log4j;

@Log4j
public class DownloadTest {
	static String URL_STR = "http://xp.apache.io:8080/fms/upload/filesUpload";

	public static void download(String url, String destFileName) throws ClientProtocolException, IOException {
		// 生成一个httpclient对象
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet(url);
		HttpResponse response = httpclient.execute(httpget);
		HttpEntity entity = response.getEntity();
		InputStream in = entity.getContent();
		File file = new File(destFileName);
		try {
			FileOutputStream fout = new FileOutputStream(file);
			int l = -1;
			byte[] tmp = new byte[1024];
			while ((l = in.read(tmp)) != -1) {
				fout.write(tmp, 0, l);
				// 注意这里如果用OutputStream.write(buff)的话，图片会失真，大家可以试试
			}
			fout.flush();
			fout.close();
		} finally {
			// 关闭低层流。
			in.close();
		}
		httpclient.close();
	}

	@Test
	public void main() {
		try {
			download("http://xp.apache.io:8080/fms/download/ss?f=default.conf", "download.file");
		} catch (ClientProtocolException e) {
			log.error(e);
		} catch (IOException e) {
			log.error(e);
		}
	}
}
