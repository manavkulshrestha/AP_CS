import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.text.*;
import java.util.*;
import java.util.List; // resolves problem with java.awt.List and java.util.List

/**
 * A class that represents a picture.  This class inherits from 
 * SimplePicture and allows the student to add functionality to
 * the Picture class.  
 * 
 * @author Barbara Ericson ericson@cc.gatech.edu
 */
public class Picture extends SimplePicture {
  ///////////////////// constructors //////////////////////////////////

  /**
   * Constructor that takes no arguments
   */
  public Picture() {
    /* not needed but use it to show students the implicit call to super()
     * child constructors always call a parent constructor 
     */
    super();
  }

  /**
   * Constructor that takes a file name and creates the picture
   *
   * @param fileName the name of the file to create the picture from
   */
  public Picture(String fileName) {
    // let the parent class handle this fileName
    super(fileName);
  }

  /**
   * Constructor that takes the width and height
   *
   * @param height the height of the desired picture
   * @param width  the width of the desired picture
   */
  public Picture(int height, int width) {
    // let the parent class handle this width and height
    super(width, height);
  }

  /**
   * Constructor that takes a picture and creates a
   * copy of that picture
   *
   * @param copyPicture the picture to copy
   */
  public Picture(Picture copyPicture) {
    // let the parent class do the copy
    super(copyPicture);
  }

  /**
   * Constructor that takes a buffered image
   *
   * @param image the buffered image to use
   */
  public Picture(BufferedImage image) {
    super(image);
  }

  ////////////////////// methods ///////////////////////////////////////

  /**
   * Method to return a string with information about this picture.
   *
   * @return a string with information about the picture such as fileName,
   * height and width.
   */
  public String toString() {
    String output = "Picture, filename " + getFileName() +
            " height " + getHeight()
            + " width " + getWidth();
    return output;

  }

  /**
   * Method to set the blue to 0
   */
  public void zeroBlue() {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels) {
      for (Pixel pixelObj : rowArray) {
        pixelObj.setBlue(0);
      }
    }
  }

  /**
   * Method that mirrors the picture around a
   * vertical mirror in the center of the picture
   * from left to right
   */
  public void mirrorVertical() {
    Pixel[][] pixels = this.getPixels2D();
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int width = pixels[0].length;
    for (int row = 0; row < pixels.length; row++) {
      for (int col = 0; col < width / 2; col++) {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][width - 1 - col];
        rightPixel.setColor(leftPixel.getColor());
      }
    }
  }

  /**
   * Method that mirrors the picture around a
   * vertical mirror in the center of the picture
   * from right to left
   */
  public void mirrorVerticalRightToLeft() {
    Pixel[][] pixels = this.getPixels2D();
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int width = pixels[0].length;
    for (int row = 0; row < pixels.length; row++) {
      for (int col = 0; col < width / 2; col++) {
        rightPixel = pixels[row][width - 1 - col];
        leftPixel = pixels[row][col];
        leftPixel.setColor(rightPixel.getColor());
      }
    }
  }

  /**
   * Method that mirrors the picture around a
   * horizontal mirror in the center of the picture
   * from top to bottom
   */
  public void mirrorHorizontal() {
    Pixel[][] pixels = this.getPixels2D();
    Pixel topPixel = null;
    Pixel bottomPixel = null;
    int height = pixels.length;
    for (int row = 0; row < height / 2; row++) {
      for (int col = 0; col < pixels[0].length; col++) {
        topPixel = pixels[row][col];
        bottomPixel = pixels[height - 1 - row][col];
        bottomPixel.setColor(topPixel.getColor());
      }
    }
  }

  /**
   * Method that mirrors the picture around a
   * horizontal mirror in the center of the picture
   * from bottom to top
   */
  public void mirrorHorizontalBotToTop() {
    Pixel[][] pixels = this.getPixels2D();
    Pixel topPixel = null;
    Pixel bottomPixel = null;
    int height = pixels.length;
    for (int row = 0; row < height / 2; row++) {
      for (int col = 0; col < pixels[0].length; col++) {
        bottomPixel = pixels[height - 1 - row][col];
        topPixel = pixels[row][col];
        topPixel.setColor(bottomPixel.getColor());
      }
    }
  }

  /**
   * Method that mirrors the picture around a
   * horizontal mirror in the center of the picture
   * from bottom to top
   */
  public void mirrorDiagonal() {
    Pixel[][] pixels = this.getPixels2D();
    Pixel source = null;
    Pixel mirror = null;
    int width = pixels[0].length;
    for (int row = 0; row < pixels.length; row++) {
      for (int col = 0; col < pixels[0].length; col++) {
        if (col < pixels.length) {
          source = pixels[row][col];
          mirror = pixels[col][row];
          source.setColor(mirror.getColor());
        }
      }
    }
  }

  /**
   * Mirror just part of a picture of a temple
   */
  public void mirrorTemple() {
    int mirrorPoint = 276;
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int count = 0;
    Pixel[][] pixels = this.getPixels2D();

    // loop through the rows
    for (int row = 27; row < 97; row++) {
      // loop from 13 to just before the mirror point
      for (int col = 13; col < mirrorPoint; col++) {
        count++;
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][mirrorPoint - col + mirrorPoint];
        rightPixel.setColor(leftPixel.getColor());
      }
    }

    System.out.printf("Count: %d\n", count);
  }

  public void mirrorArms() {
    int mirrorPoint = 190;
    Pixel bottomPixel = null;
    Pixel topPixel = null;
    Pixel[][] pixels = this.getPixels2D();

    for (int row = 157; row < 190; row++) {
      for (int col = 105; col < 170; col++) {
        topPixel = pixels[row][col];
        bottomPixel = pixels[mirrorPoint + (mirrorPoint - row)][col];
        bottomPixel.setColor(topPixel.getColor());
      }
    }

    for (int row = 170; row < 200; row++) {
      for (int col = 239; col < 295; col++) {
        topPixel = pixels[row][col];
        bottomPixel = pixels[mirrorPoint + (mirrorPoint - row)][col];
        bottomPixel.setColor(topPixel.getColor());
      }
    }
  }

  public void mirrorGull() {
    int mirrorPoint = 345;
    Pixel rightPixel = null;
    Pixel leftPixel = null;
    Pixel[][] pixels = this.getPixels2D();

    for (int row = 235; row < 325; row++) {
      for (int col = 238; col < mirrorPoint; col++) {
        rightPixel = pixels[row][col];
        leftPixel = pixels[row][mirrorPoint - col + mirrorPoint / 3];
        leftPixel.setColor(rightPixel.getColor());
      }
    }
  }

  /**
   * copy from the passed fromPic to the
   * specified startRow and startCol in the
   * current picture
   *
   * @param fromPic  the picture to copy from
   * @param startRow the start row to copy to
   * @param startCol the start col to copy to
   */
  public void copy(Picture fromPic, int startRow, int startCol) {
    Pixel fromPixel = null;
    Pixel toPixel = null;
    Pixel[][] toPixels = this.getPixels2D();
    Pixel[][] fromPixels = fromPic.getPixels2D();
    for (int fromRow = 0, toRow = startRow; fromRow < fromPixels.length && toRow < toPixels.length; fromRow++, toRow++) {
      for (int fromCol = 0, toCol = startCol; fromCol < fromPixels[0].length && toCol < toPixels[0].length; fromCol++, toCol++) {
        fromPixel = fromPixels[fromRow][fromCol];
        toPixel = toPixels[toRow][toCol];
        toPixel.setColor(fromPixel.getColor());
      }
    }
  }

  public void copy2(Picture fromPic, int positionStartRow, int positionStartCol, int selectionStartRow, int selectionStartCol, int selectionEndRow, int selectionEndCol) {
    Pixel fromPixel = null;
    Pixel toPixel = null;
    Pixel[][] toPixels = this.getPixels2D();
    Pixel[][] fromPixels = fromPic.getPixels2D();

    //stops if starting positions do not allow for all of the selection to be copied over
    for (int fromRow = selectionStartRow, toRow = positionStartRow; toRow < toPixels.length && fromRow < selectionEndRow; fromRow++, toRow++) {
      for (int fromCol = selectionStartCol, toCol = positionStartCol; toCol < toPixels[0].length && fromCol < selectionEndCol; fromCol++, toCol++) {
        fromPixel = fromPixels[fromRow][fromCol];
        toPixel = toPixels[toRow][toCol];
        toPixel.setColor(fromPixel.getColor());
      }
    }
  }

  /**
   * Method to create a collage of several pictures
   */
  public void createCollage() {
    Picture flower1 = new Picture("flower1.jpg");
    Picture flower2 = new Picture("flower2.jpg");
    this.copy(flower1, 0, 0);
    this.copy(flower2, 100, 0);
    this.copy(flower1, 200, 0);
    Picture flowerNoBlue = new Picture(flower2);
    flowerNoBlue.zeroBlue();
    this.copy(flowerNoBlue, 300, 0);
    this.copy(flower1, 400, 0);
    this.copy(flower2, 500, 0);
    this.mirrorVertical();
    this.write("collage.jpg");
  }

  public void myCollage() {
    Picture butterfly = new Picture("butterfly1.jpg");
    butterfly = butterfly.scale(.45, .45);

    Picture negativeButterfly = new Picture(butterfly);
    negativeButterfly.negate();

    Picture grayscaleButterfly = new Picture(butterfly);
    grayscaleButterfly.grayscale();

    Picture edgeButterfly = new Picture(butterfly);
    edgeButterfly.edgeDetection(20);

    this.copy(negativeButterfly, 0, 0);
    this.copy(grayscaleButterfly, 200, 0);
    this.copy(edgeButterfly, 400, 0);

    this.mirrorVertical();
    this.write("myCollage.jpg");
  }

  /**
   * Method to show large changes in color
   *
   * @param edgeDist the distance for finding edges
   */
  public void edgeDetection(int edgeDist) {
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    Pixel topPixel = null;
    Pixel bottomPixel = null;

    Pixel[][] pixels = this.getPixels2D();

    Color rightColor = null;
    Color bottomColor = null;
    for (int row = 0; row < pixels.length - 1; row++) {
      for (int col = 0; col < pixels[0].length - 1; col++) {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][col + 1];
        rightColor = rightPixel.getColor();

        topPixel = pixels[row][col];
        bottomPixel = pixels[row + 1][col];
        bottomColor = bottomPixel.getColor();

        if (leftPixel.colorDistance(rightColor) > edgeDist || topPixel.colorDistance(bottomColor) > edgeDist)
          leftPixel.setColor(Color.BLACK);
        else
          leftPixel.setColor(Color.WHITE);
      }
    }
  }

  /**
   * Method to set the blue to 0
   */
  public void keepOnlyBlue() {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels) {
      for (Pixel pixelObj : rowArray) {
        pixelObj.setGreen(0);
        pixelObj.setRed(0);
      }
    }
  }

  public void negate() {
    for (Pixel[] rowArray : this.getPixels2D()) {
      for (Pixel pixelObj : rowArray) {
        pixelObj.setRed(255 - pixelObj.getRed());
        pixelObj.setGreen(255 - pixelObj.getGreen());
        pixelObj.setBlue(255 - pixelObj.getBlue());
      }
    }
  }

  public void grayscale() {
    for (Pixel[] rowArray : this.getPixels2D()) {
      for (Pixel pixelObj : rowArray) {
        int gray = (pixelObj.getRed() + pixelObj.getGreen() + pixelObj.getBlue()) / 3;

        pixelObj.setRed(gray);
        pixelObj.setGreen(gray);
        pixelObj.setBlue(gray);
      }
    }
  }

//  public static void convolute(Pixel[][] inputData, int[][] kernel, int kernelDivisor) {
//    Pixel[][] output = new Pixel[inputData.length][inputData[0].length];
//    for (int i = inputWidth - 1; i >= 0; i--) {
//      for (int j = inputHeight - 1; j >= 0; j--) {
//        double newValue = 0.0;
//        for (int kw = kernelWidth - 1; kw >= 0; kw--)
//          for (int kh = kernelHeight - 1; kh >= 0; kh--)
//            newValue += kernel[kw][kh] * inputData[bound(i + kw - kernelWidthRadius][inputWidth)];
//                    bound(j + kh - kernelHeightRadius, inputHeight));
//        outputData.set(i, j, (int)Math.round(newValue/kernelDivisor));
//      }
//    }

    //return
//}

  public void fixUnderwater() {
    Pixel[][] pixels = this.getPixels2D();
    int rMean = 0, gMean = 0, bMean = 0, n;

    for (Pixel[] rowArray : pixels) {
      for (Pixel pixelObj : rowArray) {
        rMean += pixelObj.getRed();
        gMean += pixelObj.getGreen();
        bMean += pixelObj.getBlue();
      }
    }

    n = pixels.length * pixels[0].length;
    rMean /= n;
    gMean /= n;
    bMean /= n;

    System.out.printf("Red Mean: %d\nGreen Mean: %d\nBlue Mean: %d\n", rMean, gMean, bMean);

    for (Pixel[] rowArray : pixels) {
      for (Pixel pixelObj : rowArray) {
        int r = pixelObj.getRed();
        int b = pixelObj.getBlue();

        int rThresh = 25;
        int bThresh = 25;

        if (Math.abs(rMean - r) > rThresh) {
          pixelObj.setRed(r*9);
        } else {
          pixelObj.setRed(r*5);
        }

        if (Math.abs(bMean - r) > bThresh && Math.abs(rMean - r) > 25) {
          pixelObj.setBlue((int)(b*1.2));
        } else {
          pixelObj.setBlue(b);
        }
      }
    }

    for (int i = 207; i < 233; i++) {
      for (int j = 471; j < 548; j++) {

        int red2 = pixels[i][j].getRed();
        int blue2 = pixels[i][j].getBlue();

        if (red2 < 130) {
          pixels[i][j].setRed(90);
          pixels[i][j].setBlue(blue2+40);
        }
      }
    }

    for (int i = 233; i < 276; i++) {
      for (int j = 514; j < 548; j++) {

        int red2 = pixels[i][j].getRed();
        int blue2 = pixels[i][j].getBlue();

        if (red2 < 130) {
          pixels[i][j].setRed(90);
          pixels[i][j].setBlue(blue2+40);
        }

      }
    }
  }


//  public void avSelection(Pixel[][] pixels, fromRow, fromCol) {
//    int rMean = 0, gMean = 0, bMean = 0, n;
//
//    for(Pixel[] rowArray : pixels) {
//      for (Pixel pixelObj : rowArray) {
//        rMean += pixelObj.getRed();
//        gMean += pixelObj.getGreen();
//        bMean += pixelObj.getBlue();
//      }
//    }
//
//    n = pixels.length *pixels[0].length;
//    rMean /=n;
//    gMean /=n;
//    bMean /=n;
//  }

  public void edgeDetection2(int edgeDist) {
    Pixel leftPixel = null, middlePixel = null, rightPixel = null;
    Pixel[][] pixels = this.getPixels2D();

    for (int row = 1; row < pixels.length - 1; row++) {
      for (int col = 1; col < pixels[0].length - 1; col++) {
        leftPixel = pixels[row][col - 1];
        middlePixel = pixels[row][col];
        rightPixel = pixels[row][col + 1];

        leftPixel.setRed((leftPixel.getRed() + middlePixel.getRed()) / 2);
        leftPixel.setGreen((leftPixel.getGreen() + middlePixel.getGreen()) / 2);
        leftPixel.setBlue((leftPixel.getBlue() + middlePixel.getBlue()) / 2);

        rightPixel.setRed((rightPixel.getRed() + middlePixel.getRed()) / 2);
        rightPixel.setGreen((rightPixel.getGreen() + middlePixel.getGreen()) / 2);
        rightPixel.setBlue((rightPixel.getBlue() + middlePixel.getBlue()) / 2);

        if (leftPixel.colorDistance(rightPixel.getColor()) > edgeDist)
          leftPixel.setColor(Color.BLACK);
        else
          leftPixel.setColor(Color.WHITE);
      }
    }
  }


  /* Main method for testing - each class in Java can have a main 
   * method 
   */
  public static void main(String[] args) 
  {
    Picture beach = new Picture("butterfly1.jpg");
    beach.explore();
    beach.zeroBlue();
    beach.explore();

    //I still see a beach, but there's supposedly no blue in any of the pixels.
    //Since the human brain corrects for the green filter like appearance while acknowledging its existence,
    //one can still sorta see the blue.
  }
  
} // this } is the end of class Picture, put all new methods before this
