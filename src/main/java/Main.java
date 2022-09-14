import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.machinezoo.sourceafis.FingerprintImage;
import com.machinezoo.sourceafis.FingerprintImageOptions;
import com.machinezoo.sourceafis.FingerprintMatcher;
import com.machinezoo.sourceafis.FingerprintTemplate;

public class Main {

    public static void main(String[] args) throws IOException {
        FingerprintImageOptions options = new FingerprintImageOptions().dpi(96);
        byte[] probeEncoded = Files.readAllBytes(Paths.get("/Users/voverm/Downloads/SOCOFing/Real/3__M_Left_little_finger.BMP"));
        byte[] candidateEncoded = Files.readAllBytes(Paths.get("/Users/voverm/Downloads/SOCOFing/Altered/Altered-Medium/3__M_Left_little_finger_Obl.BMP"));

        FingerprintImage probeDecoded = new FingerprintImage(probeEncoded, options);
        FingerprintImage candidateDecoded = new FingerprintImage(candidateEncoded, options);

        FingerprintTemplate probeTemplate = new FingerprintTemplate(probeDecoded);
        FingerprintTemplate candidateTemplate = new FingerprintTemplate(candidateDecoded);

        FingerprintMatcher matcher = new FingerprintMatcher(probeTemplate);
        double similarity = matcher.match(candidateTemplate);

        double threshold = 40;
        boolean matches = similarity >= threshold;

        System.out.println(matches);

    }
}

