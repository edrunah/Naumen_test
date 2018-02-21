public interface AStarSearchable {

    public void setInOpenList(Node node);

    public Node getFromOpenList();

    public void setClosed(Node node);

    public void calculateDistances(Node node);

}
