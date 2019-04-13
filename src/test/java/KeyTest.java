import org.junit.Test;

import com.ginkgo.bing.wallpaper.util.Keygen;

public class KeyTest {
	@Test
	public void test() {
		Keygen.keyGen("admin", "123456");
		Keygen.unpack("57d476fdb656E8B19148");
	}
}
