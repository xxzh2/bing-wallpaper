import java.io.File;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.util.Collection;

import javax.activation.MimetypesFileTypeMap;

import org.junit.Test;

import eu.medsea.mimeutil.MimeUtil;
import eu.medsea.mimeutil.detector.MagicMimeMimeDetector;
import net.sf.jmimemagic.Magic;
import net.sf.jmimemagic.MagicException;
import net.sf.jmimemagic.MagicMatch;
import net.sf.jmimemagic.MagicMatchNotFoundException;
import net.sf.jmimemagic.MagicParseException;

public class MimeTest {

	String f1 = "D:\\aika.zip";
	String f2 = "D:\\key.txt";

	String f3 = "D:\\a.rtf";

	public void test4() {
		MimeUtil.registerMimeDetector(MagicMimeMimeDetector.class.getName());
		File f = new File(f3); // 文件必须真实存在
		System.out.println(f.getAbsolutePath() + " : " + f.exists());
		Collection<?> types = MimeUtil.getMimeTypes(f); // 这里返回的不是String，是Collection
		System.err.println(types);
	}

	public void test3() {
		try {
			MagicMatch match = Magic.getMagicMatch(new File(f1), false);
			// 注意：这里输入的是文件(必须真 实存在），不是String
			String type = match.getMimeType();
			System.out.println(type);
		} catch (MagicParseException | MagicMatchNotFoundException | MagicException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test1() {
		String type = new MimetypesFileTypeMap().getContentType("a");// name:"aa.txt"
		System.err.println(type);
	}

	public void test() {
		FileNameMap fileNameMap = URLConnection.getFileNameMap();
		String type = fileNameMap.getContentTypeFor(f2);// name:"aa.txt"
		System.out.println(type);

		type = fileNameMap.getContentTypeFor("pp500_0651.txt");// name:"aa.txt"
		System.out.println(type);
	}

}
