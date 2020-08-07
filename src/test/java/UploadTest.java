import java.io.File;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UploadTest {
	static String URL_STR = "http://localhost:8080/fms/upload/filesUpload";

	public static void upload(String... localFile) {
		PostMethod filePost = new PostMethod(URL_STR);
		HttpClient client = new HttpClient();
		Part[] parts = null;
		try {
			if (localFile != null) {
				parts = new Part[localFile.length];
				for (int i = 0; i < localFile.length; i++) {
					parts[i] = new FilePart("files", new File(localFile[i]));
				}
			}

			// 通过以下方法可以模拟页面参数提交
			// filePost.setParameter("userName", userName);
			// filePost.setParameter("passwd", passwd);10

			// Part[] parts = { new FilePart("files", file) };
			filePost.setRequestEntity(new MultipartRequestEntity(parts, filePost.getParams()));

			client.getHttpConnectionManager().getParams().setConnectionTimeout(5000);

			int status = client.executeMethod(filePost);
			if (status == HttpStatus.SC_OK) {
				log.info("上传成功");
			} else {
				log.info("上传失败");
			}
		} catch (Exception ex) {
			log.error("{}", ex);
		} finally {
			filePost.releaseConnection();
		}
	}

	@Test
	public void main() {
		upload("download.file", "test.zip");
	}
}
