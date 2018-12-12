import com.github.kilianB.hashAlgorithms.HashingAlgorithm;
import com.github.kilianB.matcher.Hash;
import com.github.kilianB.matcher.ImageMatcher;
import com.github.kilianB.matcher.SingleImageMatcher;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.math.BigInteger;
import java.util.Map;

public class Test {
    public static void main(String[] args) throws Exception {
        BufferedImage img1 = ImageIO.read(new File(args[0]));
        BufferedImage img2 = ImageIO.read(new File(args[1]));

        SingleImageMatcher matcher = SingleImageMatcher.createDefaultMatcher();

        Map<HashingAlgorithm, ImageMatcher.AlgoSettings> algorithms = matcher.getAlgorithms();
        for (Map.Entry<HashingAlgorithm, ImageMatcher.AlgoSettings> entry : algorithms.entrySet()) {
            Hash hash = entry.getKey().hash(img1);
            Hash replayed = new Hash(new BigInteger(hash.getHashValue().toByteArray()), hash.getBitResolution(), hash.getAlgorithmId());
            System.out.println(hash.equals(replayed));

        }

        if(matcher.checkSimilarity(img1, img2)){
            System.out.println("Look similar!");
        }
    }
}
