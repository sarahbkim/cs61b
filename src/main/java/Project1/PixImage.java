package main.java.Project1;

/* PixImage.java */

import jdk.nashorn.internal.runtime.arrays.ArrayIndex;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.lang.reflect.Array;

/**
 *  The PixImage class represents an image, which is a rectangular grid of
 *  color pixels.  Each pixel has red, green, and blue intensities in the range
 *  0...255.  Descriptions of the methods you must implement appear below.
 *  They include a constructor of the form
 *
 *      public PixImage(int width, int height);
 *
 *  that creates a black (zero intensity) image of the specified width and
 *  height.  Pixels are numbered in the range (0...width - 1, 0...height - 1).
 *
 *  All methods in this class must be implemented to complete Part I.
 *  See the README file accompanying this project for additional details.
 */

public class PixImage {

    /**
     *  Define any variables associated with a PixImage object here.  These
     *  variables MUST be private.
     */
    private int width;
    private int height;
    private int[][][] pixArray;



    /**
     * PixImage() constructs an empty PixImage with a specified width and height.
     * Every pixel has red, green, and blue intensities of zero (solid black).
     *
     * @param width the width of the image.
     * @param height the height of the image.
     */
    public PixImage(int width, int height) {
        // Your solution here.
        this.width = width;
        this.height = height;

        // need to create an array of width and height...
        pixArray = new int[height][width][3];
    }

    /**
     * getWidth() returns the width of the image.
     *
     * @return the width of the image.
     */
    public int getWidth() {
        // Replace the following line with your solution.
        return this.width;
    }

    /**
     * getHeight() returns the height of the image.
     *
     * @return the height of the image.
     */
    public int getHeight() {
        // Replace the following line with your solution.
        return this.height;
    }

    /**
     * getRed() returns the red intensity of the pixel at coordinate (x, y).
     *
     * @param x the x-coordinate of the pixel.
     * @param y the y-coordinate of the pixel.
     * @return the red intensity of the pixel at coordinate (x, y).
     */
    public short getRed(int x, int y) {
        // Replace the following line with your solution.
        return (short)this.pixArray[x][y][0];
    }

    /**
     * getGreen() returns the green intensity of the pixel at coordinate (x, y).
     *
     * @param x the x-coordinate of the pixel.
     * @param y the y-coordinate of the pixel.
     * @return the green intensity of the pixel at coordinate (x, y).
     */
    public short getGreen(int x, int y) {
        // Replace the following line with your solution.
        return (short)this.pixArray[x][y][1];
    }

    /**
     * getBlue() returns the blue intensity of the pixel at coordinate (x, y).
     *
     * @param x the x-coordinate of the pixel.
     * @param y the y-coordinate of the pixel.
     * @return the blue intensity of the pixel at coordinate (x, y).
     */
    public short getBlue(int x, int y) {
        // Replace the following line with your solution.
        return (short)this.pixArray[x][y][2];
    }

    /**
     * setPixel() sets the pixel at coordinate (x, y) to specified red, green,
     * and blue intensities.
     *
     * If any of the three color intensities is NOT in the range 0...255, then
     * this method does NOT change any of the pixel intensities.
     *
     * @param x the x-coordinate of the pixel.
     * @param y the y-coordinate of the pixel.
     * @param red the new red intensity for the pixel at coordinate (x, y).
     * @param green the new green intensity for the pixel at coordinate (x, y).
     * @param blue the new blue intensity for the pixel at coordinate (x, y).
     */
    public void setPixel(int x, int y, short red, short green, short blue) {
        // method does nothing if colors are not in range 0, 255
        if(red < 0 | red > 255 | green < 0 | green > 255 | blue < 0 | blue > 255) {
            return;
        }

        int[] newPixel = this.pixArray[x][y];
        newPixel[0] = red;
        newPixel[1] = green;
        newPixel[2] = blue;

        this.pixArray[x][y] = newPixel;
    }

    /**
     * toString() returns a String representation of this PixImage.
     *
     * This method isn't required, but it should be very useful to you when
     * you're debugging your code.  It's up to you how you represent a PixImage
     * as a String.
     *
     * @return a String representation of this PixImage.
     */
    public String toString() {
        // Replace the following line with your solution.
        String s;
        s = "[ \n";
        for(int i=0;i<pixArray.length;i++) {
            int[][] arr = pixArray[i];
            s += "[ ";
            for(int j=0; j<arr.length;j++) {
                int arr_y[] = arr[j];
                s += "[ ";
                for(int k=0;k<arr_y.length;k++) {
                    s += arr_y[k] + " ";
                }
                s += " ]";
            }
            s += " ]\n";
        }
        s += " ]";
        return s;
    }

    /**
     * boxBlur() returns a blurred version of "this" PixImage.
     *
     * If numIterations == 1, each pixel in the output PixImage is assigned
     * a value equal to the average of its neighboring pixels in "this" PixImage,
     * INCLUDING the pixel itself.
     *
     * A pixel not on the image boundary has nine neighbors--the pixel itself and
     * the eight pixels surrounding it.  A pixel on the boundary has six
     * neighbors if it is not a corner pixel; only four neighbors if it is
     * a corner pixel.  The average of the neighbors is the sum of all the
     * neighbor pixel values (including the pixel itself) divided by the number
     * of neighbors, with non-integer quotients rounded toward zero (as Java does
     * naturally when you divide two integers).
     *
     * Each color (red, green, blue) is blurred separately.  The red input should
     * have NO effect on the green or blue outputs, etc.
     *
     * The parameter numIterations specifies a number of repeated iterations of
     * box blurring to perform.  If numIterations is zero or negative, "this"
     * PixImage is returned (not a copy).  If numIterations is positive, the
     * return value is a newly constructed PixImage.
     *
     * IMPORTANT:  DO NOT CHANGE "this" PixImage!!!  All blurring/changes should
     * appear in the new, output PixImage only.
     *
     * @param numIterations the number of iterations of box blurring.
     * @return a blurred version of "this" PixImage.
     */
    public PixImage boxBlur(int numIterations) {
        // Replace the following line with your solution.
        if(numIterations<=0) {
            return this;
        }

        PixImage blurred = new PixImage(this.height, this.width);
        blurred.pixArray = this.pixArray; // start with a copy of original pixArray

        while(numIterations>0) {
            int[][][] blurred_arr = new int[this.height][this.width][3];
            // iterate of each row of pixelArrs
            for(int i=0;i<blurred.pixArray.length;i++) {
                // iterate each pixel arr
                for(int j=0;j<blurred.pixArray[i].length;j++) {
                    int[] pixel = blurred.pixArray[i][j];
                    int neighborCount = 1;
                    int avgR = (int)pixel[0];
                    int avgG = (int)pixel[1];
                    int avgB = (int)pixel[2];

                    if(i - 1 >= 0) {
                        int[] t = blurred.pixArray[i-1][j];
                        avgR += (int)t[0];
                        avgG += (int)t[1];
                        avgB += (int)t[2];
                        neighborCount++;
                        if(j+1<blurred.pixArray[i][j].length) {
                            int[] rtdiag = blurred.pixArray[i-1][j+1];
                            avgR += (int)rtdiag[0];
                            avgG += (int)rtdiag[1];
                            avgB += (int)rtdiag[2];
                            neighborCount++;
                        }
                        if(j-1>=0) {
                            int[] ltdiag = blurred.pixArray[i-1][j-1];
                            avgR += (int)ltdiag[0];
                            avgG += (int)ltdiag[1];
                            avgB += (int)ltdiag[2];
                            neighborCount++;
                        }
                    }
                    if(i+1<blurred.pixArray.length) {
                        int[] b = blurred.pixArray[i+1][j];
                        avgR += (int)b[0];
                        avgG += (int)b[1];
                        avgB += (int)b[2];
                        neighborCount++;

                        if(j+1<blurred.pixArray[i][j].length) {
                            int[] rbdiag = blurred.pixArray[i+1][j+1];
                            avgR += (int)rbdiag[0];
                            avgG += (int)rbdiag[1];
                            avgB += (int)rbdiag[2];
                            neighborCount++;
                        }
                        if(j-1>=0) {
                            int[] lbdiag = blurred.pixArray[i+1][j-1];
                            avgR += (int)lbdiag[0];
                            avgG += (int)lbdiag [1];
                            avgB += (int)lbdiag [2];
                            neighborCount++;
                        }
                    }
                    if(j+1<blurred.pixArray.length) {
                        int[] r = blurred.pixArray[i][j+1];
                        avgR += (int)r[0];
                        avgG += (int)r[1];
                        avgB += (int)r[2];
                        neighborCount++;
                    }
                    if(j-1>=0) {
                        int[] l = blurred.pixArray[i][j-1];
                        avgR += (int)l[0];
                        avgG += (int)l[1];
                        avgB += (int)l[2];
                        neighborCount++;
                    }

                    // these calculations are correct!
                    avgR = avgR/neighborCount;
                    avgG = avgG/neighborCount;
                    avgB = avgB/neighborCount;

                    // need to figure out this part ...
                    int[] blurredPixel = {avgR, avgG, avgB};
                    blurred_arr[i][j] = blurredPixel;
                }

            }

            blurred.pixArray = blurred_arr;
            numIterations--;
        }
        return blurred;

    }

    /**
     * mag2gray() maps an energy (squared vector magnitude) in the range
     * 0...24,969,600 to a grayscale intensity in the range 0...255.  The map
     * is logarithmic, but shifted so that values of 5,080 and below map to zero.
     *
     * DO NOT CHANGE THIS METHOD.  If you do, you will not be able to get the
     * correct images and pass the autograder.
     *
     * @param mag the energy (squared vector magnitude) of the pixel whose
     * intensity we want to compute.
     * @return the intensity of the output pixel.
     */
    private static short mag2gray(long mag) {
        short intensity = (short) (30.0 * Math.log(1.0 + (double) mag) - 256.0);
        // Make sure the returned intensity is in the range 0...255, regardless of
        // the input value.
        if (intensity < 0) {
            intensity = 0;
        } else if (intensity > 255) {
            intensity = 255;
        }
        return intensity;
    }


    /** mirror() takes a position (x, y) for pixArray and creates a new array of pixels with mirror images
     * @param x int position
     * @param y int position
     * @return a new pixArray for that position with top, left, bottom, right pixels filled
     */
    private int[] mirror(int x, int y) {
        int[] mirrorImage = this.pixArray[x][y];
        return mirrorImage;
    }

    /**
     * neighboringPixels takes a position of a pixel and returns a matrix including itself,
     * top, left, right, bottom, and diagonal pixel neighbors
     * @param x
     * @param y
     * @return
     */
    private int[][][] neighboringPixels(int x, int y) {
        int[][][] neighborMatrix = new int[3][3][3];
        neighborMatrix[1][1] = this.pixArray[x][y];

        for(int i=0;i<this.pixArray.length;i++){
            for(int j=0;j<this.pixArray[i].length-1;j++){
                try {
                    // get right
                    neighborMatrix[1][2] = pixArray[x][y+1];
                } catch (ArrayIndexOutOfBoundsException e) {
                    neighborMatrix[1][2] = mirror(x, y);
                }
                try {
                    // get left
                    neighborMatrix[0][1] = pixArray[x][y-1];
                } catch (ArrayIndexOutOfBoundsException e) {
                    neighborMatrix[0][1] = mirror(x, y);
                }
                try {
                    // get top
                    neighborMatrix[1][0] = pixArray[x-1][y];
                } catch (ArrayIndexOutOfBoundsException e) {
                    neighborMatrix[1][0] = mirror(x, y);

                }
                try {
                    // get bottom
                    neighborMatrix[2][1] = pixArray[x+1][y];
                } catch (ArrayIndexOutOfBoundsException e) {
                    neighborMatrix[2][1] = mirror(x, y);
                }
                try {
                    // get right-top
                    neighborMatrix[2][0] = pixArray[x-1][y+1];

                } catch (ArrayIndexOutOfBoundsException e) {
                    neighborMatrix[2][0] = neighborMatrix[1][2];
                }
                try {
                    // get right-bottom
                    neighborMatrix[2][2] = pixArray[x+1][y+1];
                } catch (ArrayIndexOutOfBoundsException e) {
                    neighborMatrix[2][2] = neighborMatrix[1][2];
                }
                try {
                    // get left-top
                    neighborMatrix[0][0] = pixArray[x-1][y-1];
                } catch (ArrayIndexOutOfBoundsException e) {
                    neighborMatrix[0][0] = neighborMatrix[0][1];
                }
                try {
                    // get left-bottom
                    neighborMatrix[0][2] = pixArray[x+1][y-1];
                } catch (ArrayIndexOutOfBoundsException e) {
                    neighborMatrix[0][2] = neighborMatrix[2][1];
                }

            }
        }
        return neighborMatrix;
    }

    /** gradient_vector()
     * Calculate gx or gy of a pixel and returns a pixel (r, g, b)
     */
    private int[] gradient_vector(int x, int y, String xOry) {
        // initialize vars
        int[] gPix = new int[3];
        int r, g, b;
        r = g = b = 0;
        int[][][] arr;
        int[][][] gxArr = {
                {{1}, {0}, {-1}},
                {{2}, {0}, {-2}},
                {{1}, {0}, {-1}}
        };
        int[][][] gyArr = {
                {{1}, {2}, {1}},
                {{0}, {0}, {0}},
                {{-1}, {-2}, {-1}}
        };

        if(xOry.equals("x")) {
            arr = gxArr;
        } else {
            arr = gyArr;
        }

        int[][][] m = neighboringPixels(x, y);

        for(int i=0;i<m.length-1;i++){
            for(int j=0;j<m[i].length-1;j++){
                int[] pixel = m[i][j];
                r += pixel[0] * arr[i][j][0];
                g += pixel[1] * arr[i][j][0];
                b += pixel[2] * arr[i][j][0];
            }
            gPix[0] = r;
            gPix[1] = g;
            gPix[2] = b;
        }
        return gPix;
    }

    /**
     *
     * @param gx an integer array, result of calling gradient_vector with 'x'
     * @param gy an integer array, result of calling gradient_vector with 'y'
     * @return
     */
    private long pixelEnergy(int[] gx, int[] gy) {
        int e = 0;
        e += Math.pow((double)gx[0], (double)2) + Math.pow((double)gy[0], (double)2) +
                Math.pow((double)gx[1],(double)2) + Math.pow((double)gy[1], (double)2) +
                Math.pow((double)gx[2], (double)2) + Math.pow((double)gy[2], (double)2);
        return (long)e;
    }

    /**
     * sobelEdges() applies the Sobel operator, identifying edges in "this"
     * image.  The Sobel operator computes a magnitude that represents how
     * strong the edge is.  We compute separate gradients for the red, blue, and
     * green components at each pixel, then sum the squares of the three
     * gradients at each pixel.  We convert the squared magnitude at each pixel
     * into a grayscale pixel intensity in the range 0...255 with the logarithmic
     * mapping encoded in mag2gray().  The output is a grayscale PixImage whose
     * pixel intensities reflect the strength of the edges.
     *
     * See http://en.wikipedia.org/wiki/Sobel_operator#Formulation for details.
     *
     * @return a grayscale PixImage representing the edges of the input image.
     * Whiter pixels represent stronger edges.
     */
    public PixImage sobelEdges() {
        // Replace the following line with your solution.
        PixImage sobel = new PixImage(this.height, this.width);
        sobel.pixArray = new int[this.height][this.width][3];

        for(int i=0;i<sobel.pixArray.length-1;i++){
            for(int j=0;j<sobel.pixArray[i].length-1;j++){
                // calculate gradient vectors
                int[] gx = gradient_vector(i, j, "x");
                int[] gy = gradient_vector(i, j, "y");

                // calculate energy
                short e = mag2gray(pixelEnergy(gx, gy));

                // set sobel's pixel array to energy
                sobel.pixArray[i][j][0] = sobel.pixArray[i][j][1] = sobel.pixArray[i][j][2] = e;
            }
        }
        return sobel;
    }

    /**
     * TEST CODE:  YOU DO NOT NEED TO FILL IN ANY METHODS BELOW THIS POINT.
     * You are welcome to add tests, though.  Methods below this point will not
     * be tested.  This is not the autograder, which will be provided separately.
     */


    /**
     * doTest() checks whether the condition is true and prints the given error
     * message if it is not.
     *
     * @param b the condition to check.
     * @param msg the error message to print if the condition is false.
     */
    private static void doTest(boolean b, String msg) {
        if (b) {
            System.out.println("Good.");
        } else {
            System.err.println(msg);
        }
    }

    /**
     * array2PixImage() converts a 2D array of grayscale intensities to
     * a grayscale PixImage.
     *
     * @param pixels a 2D array of grayscale intensities in the range 0...255.
     * @return a new PixImage whose red, green, and blue values are equal to
     * the input grayscale intensities.
     */
    private static PixImage array2PixImage(int[][] pixels) {
        int width = pixels.length;
        int height = pixels[0].length;
        PixImage image = new PixImage(width, height);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setPixel(x, y, (short) pixels[x][y], (short) pixels[x][y],
                        (short) pixels[x][y]);
            }
        }

        return image;
    }

    /**
     * equals() checks whether two images are the same, i.e. have the same
     * dimensions and pixels.
     *
     * @param image a PixImage to compare with "this" PixImage.
     * @return true if the specified PixImage is identical to "this" PixImage.
     */
    public boolean equals(PixImage image) {
        int width = getWidth();
        int height = getHeight();

        if (image == null ||
                width != image.getWidth() || height != image.getHeight()) {
            return false;
        }

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (! (getRed(x, y) == image.getRed(x, y) &&
                        getGreen(x, y) == image.getGreen(x, y) &&
                        getBlue(x, y) == image.getBlue(x, y))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * main() runs a series of tests to ensure that the convolutions (box blur
     * and Sobel) are correct.
     */
    public static void main(String[] args) {
        // Be forwarned that when you write arrays directly in Java as below,
        // each "row" of text is a column of your image--the numbers get
        // transposed.
        PixImage image1 = array2PixImage(new int[][] { { 0, 10, 240 },
                { 30, 120, 250 },
                { 80, 250, 255 } });
        System.out.println("Testing getWidth/getHeight on a 3x3 image.  " +
                "Input image:");
        System.out.print(image1);
        doTest(image1.getWidth() == 3 && image1.getHeight() == 3,
                "Incorrect image width and height.");

//        System.out.println("Testing setPixels");
//        image1.setPixel(0, 2, (short) 10, (short) 30, (short) 225);
//        doTest(image1.getBlue(0, 2) == (short)225 && image1.getRed(0, 2) == (short)10, "Incorrect setPixel value");

        System.out.println("Testing blurring on a 3x3 image.");
        doTest(image1.boxBlur(1).equals(
                        array2PixImage(new int[][] { { 40, 108, 155 },
                                { 81, 137, 187 },
                                { 120, 164, 218 } })),
                "Incorrect box blur (1 rep):\n" + image1.boxBlur(1));
        doTest(image1.boxBlur(2).equals(
                        array2PixImage(new int[][] { { 91, 118, 146 },
                                { 108, 134, 161 },
                                { 125, 151, 176 } })),
                "Incorrect box blur (2 rep):\n" + image1.boxBlur(2));
        doTest(image1.boxBlur(2).equals(image1.boxBlur(1).boxBlur(1)),
                "Incorrect box blur (1 rep + 1 rep):\n" +
                        image1.boxBlur(2) + image1.boxBlur(1).boxBlur(1));

        doTest(image1.sobelEdges().equals(
                        array2PixImage(new int[][] { { 104, 189, 180 },
                                { 160, 193, 157 },
                                { 166, 178, 96 } })),
                "Incorrect Sobel:\n" + image1.sobelEdges());


//        PixImage image2 = array2PixImage(new int[][] { { 0, 100, 100 },
//                { 0, 0, 100 } });
//        System.out.println("Testing getWidth/getHeight on a 2x3 image.  " +
//                "Input image:");
//        System.out.print(image2);
//        doTest(image2.getWidth() == 2 && image2.getHeight() == 3,
//                "Incorrect image width and height.");
//
//        System.out.println("Testing blurring on a 2x3 image.");
//        doTest(image2.boxBlur(1).equals(
//                        array2PixImage(new int[][] { { 25, 50, 75 },
//                                { 25, 50, 75 } })),
//                "Incorrect box blur (1 rep):\n" + image2.boxBlur(1));
//
//        System.out.println("Testing edge detection on a 2x3 image.");
//        doTest(image2.sobelEdges().equals(
//                        array2PixImage(new int[][] { { 122, 143, 74 },
//                                { 74, 143, 122 } })),
//                "Incorrect Sobel:\n" + image2.sobelEdges());
    }
}
