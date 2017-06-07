package sane.JXSane.api;

public final class SaneDeviceParam {

    private final int frameFormat;
    private final boolean lastFrame;
    private final int bytesPerLine;
    private final int width;
    private final int height;
    private final int depth;

    public SaneDeviceParam(int frameFormat, boolean lastFrame, int bytesPerLine, int width, int height, int depth) {
        this.frameFormat = frameFormat;
        this.lastFrame = lastFrame;
        this.bytesPerLine = bytesPerLine;
        this.width = width;
        this.height = height;
        this.depth = depth;
    }

    /**
     * @return the frameFormat
     */
    public final int getFrameFormat() {
        return frameFormat;
    }

    /**
     * @return the lastFrame
     */
    public final boolean isLastFrame() {
        return lastFrame;
    }

    /**
     * @return the bytesPerLine
     */
    public final int getBytesPerLine() {
        return bytesPerLine;
    }

    /**
     * @return the width
     */
    public final int getWidth() {
        return width;
    }

    /**
     * @return the height
     */
    public final int getHeight() {
        return height;
    }

    /**
     * @return the depth
     */
    public final int getDepth() {
        return depth;
    }
}
