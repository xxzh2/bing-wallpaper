import java.io.File;
import java.io.IOException;

import org.junit.Ignore;
import org.junit.Test;

public class TempFileTest {

	
	@Test
	@Ignore
	public void test() throws IOException {
		File f = File.createTempFile("test-", ".tmp", new File("D:\\Think"));
		f.createNewFile();
		f.deleteOnExit();
	}
}
