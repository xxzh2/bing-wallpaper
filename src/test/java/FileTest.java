import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Date;

import org.junit.Test;

public class FileTest {

	String filePath = new File(System.getProperty("user.home"), "test").getPath();

	@Test
	public void getFileName() {
		String name = Long.toString(new Date().getTime(), 24);
		System.out.println("" + name.toLowerCase());
	}

	public void test() {
		System.out.println("d955825-3614400527129679119.tmp2".matches(".*\\.tmp$"));
	}

	public void testSha1() throws IOException {

		File f = new File(filePath);
		try {
			File dist = new File(f.getParent(), "dist.file");
			Files.copy(f.toPath(), dist.toPath());
		} catch (OutOfMemoryError e) {
			e.printStackTrace();
		}

		System.out.println(f.delete());
	}

	public void testPath() throws IOException {

		File f = new File("ok");
		System.out.println(f.getAbsolutePath());
		System.out.println(f.getPath());
		System.out.println(f.getCanonicalPath());

		System.out.println(f.toURI());
	}

}
