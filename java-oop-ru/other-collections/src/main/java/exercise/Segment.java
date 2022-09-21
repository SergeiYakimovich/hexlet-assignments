package exercise;

// BEGIN
public class Segment {
    private Point beginPoint;
    private Point endPoint;

    public Segment(Point point1, Point point2) {
        this.beginPoint = point1;
        this.endPoint = point2;
    }
    
    public void setBeginPoint(Point point) {
        this.beginPoint = point;
    }

    public void setEndPoint(Point point) {
        this.endPoint = point;
    }

    public Point getBeginPoint() {
        return beginPoint;
    }

    public Point getEndPoint() {
        return endPoint;
    }

    public Point getMidPoint() {
        int midX = (this.beginPoint.getX() + this.endPoint.getX()) / 2;
        int midY = (this.beginPoint.getY() + this.endPoint.getY()) / 2;
        return new Point(midX, midY);
    }

}
// END
