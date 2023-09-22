package util.video;

import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class FrameExtractor {
    public static void main(String[] args) {
        getVideoPic(new File("b3ad2d0c64bf552c3ebd061289d534.mp4"), "");
    }

    /**
     * getVideoPic
     *
     * @param video
     * @param picPath
     */
    public static void getVideoPic(File video, String picPath) {
        //1.根据一个视频文件，创建一个按照视频中每一帧进行抓取图片的抓取对象
        FFmpegFrameGrabber ff = new FFmpegFrameGrabber(video);
        try {
            ff.start();
            System.out.println("height=" + ff.getImageHeight());
            System.out.println("width=" + ff.getImageWidth());
            System.out.println("frame=" + ff.getFrameRate());
            System.out.println("length=" + ff.getLengthInFrames());
            //抓每一帧图片
            //2.先知道这个视频一共有多少帧
            int length = ff.getLengthInFrames();
            //3.读取视频中每一帧图片
            Frame frame = null;
            int frameCounter = 0;
            //将获取的帧，存储为图片
            Java2DFrameConverter converter = new Java2DFrameConverter();//创建一个帧-->图片的转换器
            while ((frame = ff.grabImage()) != null) {
                BufferedImage image = converter.getBufferedImage(frame);
                if (image != null) {
                    String img = "D:\\github\\JUtil\\video\\" + picPath + frameCounter + ".png";
                    File picFile = new File(img);
                    ImageIO.write(image, "png", picFile);
                    frameCounter++;
                }
            }
            ff.stop();
            System.out.println("length=" + length);
            System.out.println("frameCounter=" + frameCounter);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}