public interface AStarSearchable {

    public void setOpened(Node node);

    public Node getOpened();

    public void setClosed(Node node);

    public boolean openListIsEmpty();

    public boolean isOutOfMap(Point point);

    public boolean isEmptyRoad(Point point);

    public void calculateDistances(Node node);

    public boolean isEnd(Point point);

}
